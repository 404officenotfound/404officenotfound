// 검색 필드 토글
function toggleSearchFields() {
    const searchType = document.getElementById('searchType').value;

    // 모든 검색 필드 숨기기
    document.getElementById('reservationCodeSearch').style.display = 'none';
    document.getElementById('memberCodeSearch').style.display = 'none';
    document.getElementById('reservationDateSearch').style.display = 'none';
    document.getElementById('reservationPeriodSearch').style.display = 'none';

    // 선택된 검색 타입에 따라 해당 필드 보이기
    switch(searchType) {
        case 'reservationCode':
            document.getElementById('reservationCodeSearch').style.display = 'block';
            break;
        case 'memberCode':
            document.getElementById('memberCodeSearch').style.display = 'block';
            break;
        case 'reservationDate':
            document.getElementById('reservationDateSearch').style.display = 'block';
            break;
        case 'reservationPeriod':
            document.getElementById('reservationPeriodSearch').style.display = 'block';
            break;
    }
}

// 전체 체크박스 토글
function toggleAllCheckboxes() {
    const checkboxes = document.getElementsByClassName('reservation-checkbox');
    const selectAllCheckbox = document.getElementById('selectAll');

    for(let checkbox of checkboxes) {
        if(!checkbox.disabled) {
            checkbox.checked = selectAllCheckbox.checked;
        }
    }
}

// 검색 폼 유효성 검사
function validateForm() {
    const searchType = document.getElementById('searchType').value;

    switch(searchType) {
        case 'reservationCode':
            const reservationCode = document.querySelector('input[name="reservationCode"]').value;
            if(!reservationCode) {
                alert('예약번호를 입력해주세요.');
                return false;
            }
            if(isNaN(reservationCode)) {
                alert('예약번호는 숫자만 입력 가능합니다.');
                return false;
            }
            break;

        case 'memberCode':
            const memberCode = document.querySelector('input[name="memberCode"]').value;
            if(!memberCode) {
                alert('회원번호를 입력해주세요.');
                return false;
            }
            if(isNaN(memberCode)) {
                alert('회원번호는 숫자만 입력 가능합니다.');
                return false;
            }
            break;

        case 'reservationDate':
            const reservationDate = document.querySelector('input[name="reservationDate"]').value;
            if(!reservationDate) {
                alert('예약일자를 선택해주세요.');
                return false;
            }
            break;

        case 'reservationPeriod':
            const startDate = document.querySelector('input[name="startDatetime"]').value;
            const endDate = document.querySelector('input[name="endDatetime"]').value;
            if(!startDate || !endDate) {
                alert('시작일과 종료일을 모두 선택해주세요.');
                return false;
            }
            if(startDate > endDate) {
                alert('종료일은 시작일보다 늦어야 합니다.');
                return false;
            }
            break;
    }

    return true;
}

// 예약 취소 처리
function handleCancel() {
    const checkboxes = document.getElementsByClassName('reservation-checkbox');
    const selectedCodes = [];
    let hasCanceledSelection = false;

    // 체크된 항목들 중 예약 취소 상태가 아닌 것만 선택
    for(let checkbox of checkboxes) {
        if(checkbox.checked) {
            const row = checkbox.closest('tr');
            const status = row.querySelector('td:nth-last-child(1)').textContent.trim();

            if(status !== '예약취소') {
                selectedCodes.push(checkbox.value);
            } else {
                hasCanceledSelection = true;
            }
        }
    }

    // 이미 취소된 예약이 선택되었다면 경고
    if(hasCanceledSelection) {
        alert('이미 취소된 예약은 취소할 수 없습니다.');
        return false;
    }

    // 선택된 항목이 없다면 경고
    if(selectedCodes.length === 0) {
        alert('취소할 예약을 선택해주세요.');
        return false;
    }

    return confirm('선택한 예약을 취소하시겠습니까?');
}

// 예약 삭제 처리
function handleDelete() {
    const checkboxes = document.getElementsByClassName('reservation-checkbox');
    const selectedCodes = [];
    let hasNonCanceledSelection = false;

    // 체크된 항목들 중 예약 취소 상태인 것만 선택
    for(let checkbox of checkboxes) {
        if(checkbox.checked) {
            const row = checkbox.closest('tr');
            const status = row.querySelector('td:nth-last-child(1)').textContent.trim();

            if(status === '예약취소') {
                selectedCodes.push(checkbox.value);
            } else {
                hasNonCanceledSelection = true;
            }
        }
    }

    // 예약 취소 상태가 아닌 항목이 선택되었다면 경고
    if(hasNonCanceledSelection) {
        alert('취소되지 않은 예약은 삭제할 수 없습니다.');
        return false;
    }

    // 선택된 항목이 없다면 경고
    if(selectedCodes.length === 0) {
        alert('삭제할 예약을 선택해주세요.');
        return false;
    }

    return confirm('선택한 취소된 예약을 삭제하시겠습니까?');
} 