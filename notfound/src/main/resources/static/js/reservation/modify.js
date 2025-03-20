document.addEventListener('DOMContentLoaded', function() {
    initializeForm();
    loadInitialData();
});

function initializeForm() {
    // 달력 초기화
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        minDate: 0
    });

    // 시간 선택 초기화
    $('.timepicker').timepicker({
        timeFormat: 'HH:mm',
        interval: 60,
        minTime: '9',
        maxTime: '18:00',
        startTime: '09:00',
        dynamic: false,
        dropdown: true,
        scrollbar: true
    });

    // 지점 변경 시 사무실 목록 업데이트
    $('#modifyBranch').on('change', loadOffices);

    // 폼 제출 이벤트
    $('#modifyForm').on('submit', handleSubmit);
}

async function loadInitialData() {
    // 지점 목록 로드
    await loadBranches();

    // 기존 예약 데이터로 폼 초기화
    const reservationId = document.getElementById('reservationId').value;
    const response = await fetch(`/api/reservations/${reservationId}`);
    const data = await response.json();

    $('#modifyBranch').val(data.branch);
    await loadOffices();
    $('#modifyOffice').val(data.office);
    $('#modifyDate').val(data.date);
    $('#modifyTime').val(data.time);
}

async function handleSubmit(e) {
    e.preventDefault();

    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData);

    // 새로운 가격 계산
    const priceResponse = await fetch('/api/calculate-price', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    const newPrice = await priceResponse.json();

    // 가격 차이 확인
    const originalPrice = document.getElementById('originalPrice').value;
    const priceDifference = newPrice - originalPrice;

    if (priceDifference > 0) {
        if (!confirm(`추가금액 ${priceDifference}원이 발생합니다. 결제를 진행하시겠습니까?`)) {
            return;
        }

        // 추가 결제 처리
        const paymentResult = await processAdditionalPayment(priceDifference);
        if (!paymentResult.success) {
            alert('결제 처리에 실패했습니다.');
            return;
        }
    }

    // 예약 수정 처리
    const response = await fetch('/api/modify-reservation', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            ...data,
            status: isAdmin ? '예약대기' : '예약완료'
        })
    });

    if (response.ok) {
        alert('예약이 수정되었습니다.');
        window.location.href = '/reservations/search';
    } else {
        alert('예약 수정에 실패했습니다.');
    }
}

async function processAdditionalPayment(amount) {
    // 외부 결제 API 호출
    return await fetch('/api/payment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            amount: amount,
            type: 'additional'
        })
    }).then(res => res.json());
} 