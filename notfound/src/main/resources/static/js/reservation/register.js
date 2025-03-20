// 📌 변수 초기화
let selectedDate = null;
let selectedTimeSlots = [];
let currentDate = new Date();
const officeCode = document.getElementById("officeCode").value;
const officePrice = parseInt(document.getElementById("officePrice").value);

// KST (한국 시간) 기준으로 날짜 설정
const koreaTimeOffset = 9 * 60 * 60 * 1000;
const now = new Date(Date.now() + koreaTimeOffset);
const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());

// 📅 달력 렌더링
document.addEventListener("DOMContentLoaded", function () {
    renderCalendar();
});

// 📅 달력 생성
function renderCalendar() {
    const calendar = document.getElementById("calendar");
    const currentMonthSpan = document.getElementById("currentMonth");

    currentMonthSpan.textContent = `${currentDate.getFullYear()}년 ${currentDate.getMonth() + 1}월`;

    const firstDay = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
    const lastDay = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);

    let calendarHtml = '<table><tr><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr><tr>';

    for (let i = 0; i < firstDay.getDay(); i++) {
        calendarHtml += "<td></td>";
    }

    for (let day = 1; day <= lastDay.getDate(); day++) {
        const date = new Date(currentDate.getFullYear(), currentDate.getMonth(), day);
        const dateString = date.toISOString().split("T")[0];

        const isDisabled = date < today;

        calendarHtml += `
            <td>
                <button type="button" 
                        onclick="selectDate(this)" 
                        data-date="${dateString}"
                        ${isDisabled ? "disabled" : ""}>
                    ${day}
                </button>
            </td>
        `;

        if (date.getDay() === 6) {
            calendarHtml += "</tr><tr>";
        }
    }

    calendarHtml += "</tr></table>";
    calendar.innerHTML = calendarHtml;
}

// 🔄 다음 달 이동
function nextMonth() {
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar();
}

// 🔄 이전 달 이동
function prevMonth() {
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar();
}

// 📅 날짜 선택
function selectDate(button) {
    selectedDate = adjustToKST(button.dataset.date);
    document.querySelectorAll("#calendar button").forEach((btn) => btn.classList.remove("selected"));
    button.classList.add("selected");

    console.log("✅ 선택한 날짜 (KST 기준):", selectedDate); // 🔹 디버깅 로그
    fetchBookedTimeSlots();
}

// ✅ KST 변환 함수 (UTC → KST 조정)
function adjustToKST(dateString) {
    let [year, month, day] = dateString.split("-").map(Number);

    // ✅ UTC 기준 날짜를 직접 KST로 변환
    let date = new Date(Date.UTC(year, month - 1, day, 0, 0, 0));  // UTC+0 기준으로 00:00:00
    date.setHours(date.getHours() + 9); // 🔹 KST 변환 (UTC+9)

    // 🔥 날짜를 YYYY-MM-DD 형식으로 변환하여 반환
    return date.toISOString().split("T")[0];
}

// 📌 예약된 시간 불러오기
function fetchBookedTimeSlots() {
    fetch("/api/reservations/available-times", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ officeCode, date: selectedDate }),
    })
        .then((response) => response.json())
        .then((data) => {
            if (!data || !Array.isArray(data.bookedTimes)) {
                console.error("❌ 예약된 시간 데이터가 올바르지 않습니다:", data);
                return;
            }
            renderTimeSlots(data.bookedTimes);
        })
        .catch((error) => console.error("❌ 예약된 시간 조회 실패:", error));
}

// 🕒 시간대 렌더링 (예약된 시간 비활성화)
function renderTimeSlots(bookedTimes) {
    const timeGrid = document.getElementById("timeGrid");
    let timeHtml = "";

    for (let hour = 0; hour < 24; hour += 2) {
        const timeSlot = `${hour.toString().padStart(2, "0")}:00`;

        // ✅ 예약된 시간인지 체크하여 버튼 비활성화
        const isBooked = bookedTimes.includes(timeSlot);

        timeHtml += `
            <button type="button" 
                    class="time-slot ${isBooked ? "booked" : ""}" 
                    data-time="${timeSlot}"
                    onclick="toggleTimeSlot(this)"
                    ${isBooked ? "disabled" : ""}>
                ${timeSlot}
            </button>
        `;
    }

    timeGrid.innerHTML = timeHtml;
}

// ✅ 버튼 클릭 시 선택 / 취소 (토글 기능)
function toggleTimeSlot(button) {
    if (button.disabled) return; // 🔥 이미 예약된 시간은 클릭 방지

    const time = button.dataset.time;
    const existingIndex = selectedTimeSlots.findIndex((slot) => slot.startTime === time);

    if (existingIndex !== -1) {
        // 🛑 선택한 시간 취소
        selectedTimeSlots.splice(existingIndex, 1);
        button.classList.remove("selected");
        button.disabled = false;
        updateReservationSummary();
        return;
    }

    let endHour = parseInt(time.split(":")[0]) + 1;
    let endMinute = 59;

    if (endHour >= 24) {
        endHour = 23;
        endMinute = 59;
    }

    const newSlot = {
        startTime: time,
        endTime: `${endHour.toString().padStart(2, "0")}:${endMinute.toString().padStart(2, "0")}`,
    };

    if (selectedTimeSlots.length === 0 || isSequential(newSlot, selectedTimeSlots)) {
        selectedTimeSlots.push(newSlot);
        selectedTimeSlots.sort((a, b) => getTotalMinutes(a.startTime) - getTotalMinutes(b.startTime));
        button.classList.add("selected");
    } else {
        alert("연속된 시간만 선택할 수 있습니다.");
        return;
    }

    updateReservationSummary();
}

// ✅ UI 업데이트: 예약 정보 업데이트
function updateReservationSummary() {
    const summary = document.querySelector(".reservation-summary");

    if (selectedDate && selectedTimeSlots.length > 0) {
        document.getElementById("selectedDate").textContent = selectedDate;
        document.getElementById("selectedTimes").textContent = selectedTimeSlots
            .map((slot) => slot.startTime)
            .join(", ");
        document.getElementById("totalPrice").textContent = (selectedTimeSlots.length * officePrice).toLocaleString();

        summary.style.display = "block";
    } else {
        summary.style.display = "none";
    }
}

// ✅ 연속된 시간인지 확인하는 함수
function isSequential(newSlot, slots) {
    if (slots.length === 0) return true;

    for (let slot of slots) {
        const startTimeMinutes = getTotalMinutes(slot.startTime);
        const endTimeMinutes = getTotalMinutes(slot.endTime);
        const newStartTimeMinutes = getTotalMinutes(newSlot.startTime);
        const newEndTimeMinutes = getTotalMinutes(newSlot.endTime);

        if (Math.abs(newStartTimeMinutes - endTimeMinutes) <= 5 || Math.abs(newEndTimeMinutes - startTimeMinutes) <= 5) {
            return true;
        }
    }
    return false;
}

// 🔢 시:분을 분 단위로 변환하는 함수
function getTotalMinutes(time) {
    const [hour, minute] = time.split(":").map(Number);
    return hour * 60 + minute;
}

// 📝 예약 정보 업데이트
function updateReservationSummary() {
    const summary = document.querySelector(".reservation-summary");

    if (selectedDate && selectedTimeSlots.length > 0) {
        document.getElementById("selectedDate").textContent = selectedDate;
        document.getElementById("selectedTimes").textContent = selectedTimeSlots.map((slot) => slot.startTime).join(", ");
        document.getElementById("totalPrice").textContent = (selectedTimeSlots.length * officePrice).toLocaleString();

        summary.style.display = "block";
    } else {
        summary.style.display = "none";
    }
}

// 📌 예약 제출 (예약 후 UI 업데이트 추가)
function submitReservation() {
    if (selectedTimeSlots.length === 0) {
        alert("❌ 예약 시간을 선택해주세요.");
        return;
    }

    const confirmReservation = confirm(
        `📅 날짜: ${selectedDate}\n🕒 시간: ${selectedTimeSlots[0].startTime} ~ ${selectedTimeSlots[selectedTimeSlots.length - 1].endTime}\n💰 총 금액: ${document.getElementById("totalPrice").textContent} 원\n\n예약을 진행하시겠습니까?`
    );

    if (!confirmReservation) {
        return;
    }

    // 🔹 서버로 보낼 배열 생성
    const reservations = selectedTimeSlots.map(slot => ({
        officeCode: officeCode,
        reservationDate: selectedDate,
        startTime: slot.startTime,
        endTime: slot.endTime,
        totalPrice: officePrice
    }));

    // 🔹 서버로 예약 데이터 전송 (배열 형태)
    fetch("/api/reservations/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ reservations }), // ✅ 배열 형태로 전송
    })
        .then((response) => response.json())
        .then((result) => {
            if (result.success) {
                alert("✅ 예약이 완료되었습니다.");

                // 🔹 선택한 모든 시간대를 비활성화
                selectedTimeSlots.forEach((slot) => {
                    document.querySelectorAll(`[data-time="${slot.startTime}"]`).forEach((btn) => {
                        btn.classList.add("booked");
                        btn.disabled = true;
                    });
                });

                selectedTimeSlots = [];
                updateReservationSummary();

                setTimeout(() => {
                    window.location.href = "/reservation/search/all";
                }, 1000);
            } else {
                alert(result.message || "❌ 예약 처리 중 오류가 발생했습니다.");
            }
        })
        .catch((error) => alert(`❌ 서버 오류 발생: ${error.message}`));
}
