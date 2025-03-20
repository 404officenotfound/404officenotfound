// 🔥 검색 필드 토글 함수
function toggleSearchFields() {
    // 모든 검색 필드를 숨김
    document.getElementById('paymentCodeSearch').style.display = 'none';
    document.getElementById('paymentDateSearch').style.display = 'none';
    document.getElementById('paymentPeriodSearch').style.display = 'none';

    // 선택된 검색 유형의 필드만 표시
    const searchType = document.getElementById('searchType').value;
    document.getElementById(searchType + 'Search').style.display = 'block';
}

// 🔥 폼 유효성 검사
function validateForm() {
    const searchType = document.getElementById('searchType').value;

    if (searchType === 'paymentCode') {
        const input = document.querySelector('input[name="paymentCode"]').value.trim();
        if (input === "") {
            alert("⚠ 결제번호를 입력하세요!");
            return false;
        }
        if (!/^\d+$/.test(input)) {
            alert("⚠ 결제번호는 숫자로 입력해야 합니다.");
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
        alert("시작 날짜는 종료 날짜보다 클 수 없습니다.");
        document.getElementById("startDateInput").value = "";
        document.getElementById("endDateInput").value = "";
    }
}

// 🔥 페이지 로드 시 초기 상태 설정
window.onload = function() {
    toggleSearchFields();
};
