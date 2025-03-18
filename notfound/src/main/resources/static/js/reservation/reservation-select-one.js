// select search 기능

// 🔥 검색 필드 토글 함수
function toggleSearchFields() {
    // 모든 검색 필드를 숨김
    document.getElementById('reservationCodeSearch').style.display = 'none';
    document.getElementById('reservationDateSearch').style.display = 'none';
    document.getElementById('reservationPeriodSearch').style.display = 'none';

    // 선택된 검색 유형의 필드만 표시
    const searchType = document.getElementById('searchType').value;
    document.getElementById(searchType + 'Search').style.display = 'block';
}

// 🔥 폼 유효성 검사
function validateForm() {
    const searchType = document.getElementById('searchType').value;

    if (searchType === 'reservationCode') {
        const input = document.querySelector('input[name="reservationCode"]').value.trim();
        if (input === "") {
            alert("⚠ 예약번호를 입력하세요!");
            return false;
        }
        if (!/^\d+$/.test(input)) {
            alert("⚠ 예약번호는 숫자로 입력해야 합니다.");
            return false;
        }
    }
    return true;
}

// 🔥 날짜 유효성 검사
function validateDates() {
    let startDate = document.getElementById("startDateInput").value;
    let endDate = document.getElementById("endDateInput").value;

    if (startDate && endDate && startDate > endDate) {
        alert("⚠ 시작 날짜는 종료 날짜보다 클 수 없습니다.");
        document.getElementById("startDateInput").value = "";
        document.getElementById("endDateInput").value = "";
    }
}

// 🔥 페이지 로드 시 초기 상태 설정
window.onload = function() {
    toggleSearchFields();
};

// 🔥 전체 선택 기능 (취소된 예약 제외)
function toggleAllCheckboxes(selectAllCheckbox) {
    let checkboxes = document.querySelectorAll('.reservationCheckbox:not(.disabled-checkbox)');
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
    });
}

// 🔥 개별 체크 시 취소된 예약 방지
document.addEventListener("DOMContentLoaded", function () {
    let checkboxes = document.querySelectorAll(".reservationCheckbox");
    let selectAllCheckbox = document.getElementById("selectAll");

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener("click", function (event) {
            if (this.classList.contains("disabled-checkbox")) {
                alert("이미 취소된 예약은 취소할 수 없습니다.");
                event.preventDefault();
                this.checked = false; // 체크 해제
            }

            // 전체 체크박스 업데이트
            let allChecked = document.querySelectorAll(".reservationCheckbox:not(.disabled-checkbox):checked").length ===
                document.querySelectorAll(".reservationCheckbox:not(.disabled-checkbox)").length;
            selectAllCheckbox.checked = allChecked;
        });
    });
});

// 🔥 선택한 예약 취소 확인창
function confirmCancelBatch(event) {
    let selectedCheckboxes = document.querySelectorAll('.reservationCheckbox:checked');

    if (selectedCheckboxes.length === 0) {
        alert("취소할 예약을 선택해주세요.");
        event.preventDefault(); // 폼 제출 막기
        return false;
    }

    // 🔥 이미 취소된 예약 선택 여부 확인
    let hasCancelled = Array.from(selectedCheckboxes).some(checkbox => checkbox.classList.contains("disabled-checkbox"));
    if (hasCancelled) {
        alert("이미 취소된 예약은 취소할 수 없습니다.");
        event.preventDefault();
        return false;
    }

    let confirmResult = confirm(`선택한 ${selectedCheckboxes.length}건의 예약을 취소하시겠습니까?`);
    if (!confirmResult) {
        event.preventDefault(); // 취소 선택 시 폼 제출 막기
        return false;
    }
    document.addEventListener("DOMContentLoaded", function () {
        const payButton = document.getElementById("payButton");

        payButton.addEventListener("click", function () {
            // 선택된 체크박스 가져오기
            const selectedReservations = document.querySelectorAll(".reservationCheckbox:checked");

            if (selectedReservations.length === 0) {
                alert("결제할 예약을 선택해주세요.");
                return;
            }

            // 선택한 예약 정보를 JSON으로 변환
            const reservationData = Array.from(selectedReservations).map(reservation => ({
                reservationCode: reservation.value
            }));

            console.log("결제 요청 데이터:", reservationData);

            // AJAX 요청으로 결제 정보 전송
            fetch("/payment/process", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ reservations: reservationData })
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert("결제가 완료되었습니다!");
                        window.location.reload();  // 페이지 새로고침
                    } else {
                        alert("결제 실패: " + data.message);
                    }
                })
                .catch(error => {
                    console.error("결제 요청 오류:", error);
                    alert("결제 요청 중 오류가 발생했습니다.");
                });
        });
    });

}
