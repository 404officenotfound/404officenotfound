var IMP = window.IMP;
IMP.init("imp31685584");

let member = {};

document.addEventListener("DOMContentLoaded", function() {
    member = {
        memberCode: document.getElementById("memberCode") ? document.getElementById("memberCode").value : null,
        memberEmail: document.getElementById("memberEmail") ? document.getElementById("memberEmail").value : null,
        memberName: document.getElementById("memberName") ? document.getElementById("memberName").value : null,
        memberPhone: document.getElementById("memberPhone") ? document.getElementById("memberPhone").value : null
    };
    console.log("회원 정보:", member);
});

function processSelectedPayments() {
    const selectedReservations = [];
    let totalAmount = 0;

    // 선택된 예약 정보 수집
    document.querySelectorAll('.reservationCheckbox:checked').forEach(checkbox => {
        selectedReservations.push({
            reservationCode: checkbox.value,
            price: parseInt(checkbox.getAttribute('data-price'))
        });
        totalAmount += parseInt(checkbox.getAttribute('data-price'));
    });

    if (selectedReservations.length === 0) {
        alert('결제할 예약을 선택해주세요.');
        return;
    }

    // 결제 요청
    IMP.request_pay({
        pg: 'html5_inicis',
        pay_method: '카드',
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: '오피스 예약 결제',
        currency: 'KRW',
        amount: totalAmount,
        m_redirect_url: "https://mywebsite.com/payment/complete",
        buyer_email: member.memberEmail, // 회원 이메일
        buyer_name: member.memberName,   // 회원 이름
        buyer_tel: member.memberPhone    // 회원 전화번호

    }, function(rsp) {
        if (rsp.success) {
            // 결제 성공 시 서버에 결제 정보 저장
            savePaymentInfo({
                impUid: rsp.imp_uid,
                merchantUid: rsp.merchant_uid,
                paymentMethod: rsp.pay_method,
                paymentAmount: rsp.paid_amount,
                memberCode: member.memberCode,
                reservations: selectedReservations
            });
        } else {
            alert('결제에 실패하였습니다. ' + rsp.error_msg);
        }
    });
}

function savePaymentInfo(paymentData) {
    console.log("📌 [프론트엔드] 보내는 결제 데이터:", paymentData);

    let requestBody = {
        impUid: paymentData.impUid,
        merchantUid: paymentData.merchantUid,
        paymentMethod: paymentData.paymentMethod,
        paymentAmount: isNaN(paymentData.paymentAmount) ? 0 : paymentData.paymentAmount,
        memberCode: paymentData.memberCode || 0,
        reservations: paymentData.reservations.map(res => ({
            reservationCode: res.reservationCode,
            price: res.price
        }))
    };

    console.log("📌 [전송할 JSON]:", requestBody);

    fetch(window.location.origin + '/payment/process', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestBody)
    })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                alert('결제가 완료되었습니다.');
                location.reload();
            } else {
                alert('결제 처리 중 오류가 발생했습니다.');
            }
        })
        .catch(error => {
            alert('결제 요청 중 오류가 발생했습니다: ' + error.message);
            console.error('Error:', error);
        });
}

// 체크박스 상태에 따른 버튼 표시 로직
function updateButtonVisibility() {
    const checkedBoxes = document.querySelectorAll('.reservationCheckbox:checked');
    const paymentButton = document.getElementById('paymentButton');
    const cancelPaymentButton = document.getElementById('cancelPaymentButton');

    if (checkedBoxes.length === 0) {
        paymentButton.style.display = 'none';
        cancelPaymentButton.style.display = 'none';
        return;
    }

    // 체크된 항목들의 상태 확인
    let hasPaymentRequired = false;
    let hasCancelRequired = false;

    checkedBoxes.forEach(checkbox => {
        const status = checkbox.getAttribute('data-status');
        if (status === '예약완료' || status === '예약대기' || status === '결제대기') {
            hasPaymentRequired = true;
        }
        if (status === '결제완료') {
            hasCancelRequired = true;
        }
    });

    // 버튼 표시 여부 결정
    paymentButton.style.display = hasPaymentRequired ? 'inline-block' : 'none';
    cancelPaymentButton.style.display = hasCancelRequired ? 'inline-block' : 'none';

    // 결제와 취소가 동시에 선택된 경우 경고
    if (hasPaymentRequired && hasCancelRequired) {
        alert('결제가 필요한 예약과 결제 취소가 필요한 예약을 동시에 선택할 수 없습니다.');
        checkedBoxes.forEach(checkbox => checkbox.checked = false);
        updateButtonVisibility();
    }
}

// 페이지 로드 시 초기 버튼 상태 설정
document.addEventListener('DOMContentLoaded', function() {
    updateButtonVisibility();
});

// 전체 선택 체크박스에 이벤트 리스너 추가
document.getElementById('selectAll').addEventListener('change', function() {
    updateButtonVisibility();
});

function cancelSelectedPayments() {
    const checkedBoxes = document.querySelectorAll('.reservationCheckbox:checked');
    const paymentCodes = [];

    checkedBoxes.forEach(checkbox => {
        if (checkbox.getAttribute('data-status') === '결제완료') {
            const paymentCode = checkbox.getAttribute('data-payment-code');
            if (paymentCode) {
                paymentCodes.push(paymentCode);
            }
        }
    });

    if (paymentCodes.length === 0) {
        alert('취소할 결제를 선택해주세요.');
        return;
    }

    if (!confirm('선택한 결제를 취소하시겠습니까?')) {
        return;
    }

    // 결제 취소 요청
    paymentCodes.forEach(paymentCode => {
        fetch(`/payment/cancel/${paymentCode}`, {
            method: 'POST'
        })
            .then(response => response.json())
            .then(result => {
                if (result.success) {
                    alert('결제가 취소되었습니다.');
                    location.reload();
                } else {
                    alert(result.message || '결제 취소에 실패했습니다.');
                }
            })
            .catch(error => {
                alert('결제 취소 처리 중 오류가 발생했습니다.');
                console.error('Error:', error);
            });
    });
}