let selectedDate = null;
let selectedTimeSlots = [];
let currentDate = new Date();
const officeCode = document.getElementById('officeCode').value;
const officePrice = parseInt(document.getElementById('officePrice').value);

// 초기화
document.addEventListener('DOMContentLoaded', function() {
    renderCalendar();
});

// 달력 렌더링
function renderCalendar() {
    const calendar = document.getElementById('calendar');
    const currentMonthSpan = document.getElementById('currentMonth');
    
    // 현재 월 표시
    currentMonthSpan.textContent = `${currentDate.getFullYear()}년 ${currentDate.getMonth() + 1}월`;
    
    // 달력 생성
    const firstDay = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
    const lastDay = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);
    
    let calendarHtml = '<table><tr><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr><tr>';
    
    // 첫 주 빈 칸
    for (let i = 0; i < firstDay.getDay(); i++) {
        calendarHtml += '<td></td>';
    }
    
    // 날짜 채우기
    for (let day = 1; day <= lastDay.getDate(); day++) {
        const date = new Date(currentDate.getFullYear(), currentDate.getMonth(), day);
        const today = new Date();
        const maxDate = new Date();
        maxDate.setMonth(maxDate.getMonth() + 1);
        
        // 오늘 이전이거나 한달 후 이후의 날짜는 비활성화
        const isDisabled = date < today || date > maxDate;
        
        calendarHtml += `
            <td>
                <button type="button" 
                        onclick="selectDate(this)" 
                        data-date="${date.toISOString().split('T')[0]}"
                        ${isDisabled ? 'disabled' : ''}>
                    ${day}
                </button>
            </td>
        `;
        
        if (new Date(currentDate.getFullYear(), currentDate.getMonth(), day).getDay() === 6) {
            calendarHtml += '</tr><tr>';
        }
    }
    
    calendarHtml += '</tr></table>';
    calendar.innerHTML = calendarHtml;
}

// 날짜 선택
function selectDate(button) {
    selectedDate = button.dataset.date;
    document.querySelectorAll('#calendar button').forEach(btn => {
        btn.classList.remove('selected');
    });
    button.classList.add('selected');
    
    // 선택된 날짜의 예약 가능 시간 조회
    fetchAvailableTimeSlots();
}

// 예약 가능 시간 조회
function fetchAvailableTimeSlots() {
    fetch(`/api/reservations/available-times`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            officeCode: officeCode,
            date: selectedDate
        })
    })
    .then(response => response.json())
    .then(availableTimes => {
        renderTimeSlots(availableTimes);
    });
}

// 시간대 렌더링
function renderTimeSlots(availableTimes) {
    const timeGrid = document.getElementById('timeGrid');
    let timeHtml = '';
    
    for (let hour = 0; hour < 24; hour += 2) {
        const timeSlot = `${hour.toString().padStart(2, '0')}:00`;
        const isAvailable = availableTimes.includes(timeSlot);
        
        timeHtml += `
            <button type="button" 
                    class="time-slot" 
                    data-time="${timeSlot}"
                    onclick="toggleTimeSlot(this)"
                    ${!isAvailable ? 'disabled' : ''}>
                ${timeSlot}
            </button>
        `;
    }
    
    timeGrid.innerHTML = timeHtml;
}

// 시간대 토글 선택
function toggleTimeSlot(button) {
    const time = button.dataset.time;
    const index = selectedTimeSlots.indexOf(time);
    
    if (index === -1) {
        // 연속된 시간대만 선택 가능
        if (selectedTimeSlots.length > 0) {
            const lastTime = selectedTimeSlots[selectedTimeSlots.length - 1];
            const lastHour = parseInt(lastTime.split(':')[0]);
            const currentHour = parseInt(time.split(':')[0]);
            
            if (currentHour !== lastHour + 2) {
                alert('연속된 시간대만 선택할 수 있습니다.');
                return;
            }
        }
        selectedTimeSlots.push(time);
        button.classList.add('selected');
    } else {
        // 중간 시간대는 선택 해제 불가
        if (index !== selectedTimeSlots.length - 1) {
            alert('중간 시간대는 선택 해제할 수 없습니다.');
            return;
        }
        selectedTimeSlots.splice(index, 1);
        button.classList.remove('selected');
    }
    
    updateReservationSummary();
}

// 예약 정보 업데이트
function updateReservationSummary() {
    const summary = document.querySelector('.reservation-summary');
    if (selectedDate && selectedTimeSlots.length > 0) {
        document.getElementById('selectedDate').textContent = selectedDate;
        document.getElementById('selectedTimes').textContent = 
            `${selectedTimeSlots[0]} ~ ${selectedTimeSlots[selectedTimeSlots.length - 1]}`;
        
        // 가격 계산
        const totalPrice = selectedTimeSlots.length * officePrice;
        document.getElementById('totalPrice').textContent = totalPrice.toLocaleString();
        
        summary.style.display = 'block';
    } else {
        summary.style.display = 'none';
    }
}

// 예약 제출
function submitReservation() {
    const reservationData = {
        officeCode: officeCode,
        reservationDate: selectedDate,
        startTime: selectedTimeSlots[0],
        endTime: selectedTimeSlots[selectedTimeSlots.length - 1],
        totalPrice: selectedTimeSlots.length * officePrice
    };
    
    fetch('/api/reservations/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(reservationData)
    })
    .then(response => {
        if (!response.ok) {
            if (response.status === 401) {
                alert('로그인이 필요한 서비스입니다.');
                window.location.href = '/auth/login';
                return;
            }
            throw new Error('예약 처리 중 오류가 발생했습니다.');
        }
        return response.json();
    })
    .then(result => {
        if (result.success) {
            alert('예약이 완료되었습니다.');
            window.location.href = '/reservation/list';
        } else {
            alert(result.message || '예약 처리 중 오류가 발생했습니다.');
        }
    })
    .catch(error => {
        alert(error.message);
    });
} 