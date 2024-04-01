

function navActive(){
    // 각 탭 버튼 요소를 가져옴
    const tabButtons = document.querySelectorAll('.nav-link');

// 각 탭 버튼에 클릭 이벤트 리스너 추가
    tabButtons.forEach(button => {
        button.addEventListener('click', function () {
            let loginId = document.getElementById("headerLoginId").textContent;
            // 현재 활성화된 탭을 찾아서 active 클래스를 제거
            const activeButton = document.querySelector('.nav-link.active');
            if (activeButton) {
                activeButton.classList.remove('active');
            }
            debugger;
            // 클릭된 버튼에 active 클래스 추가
            this.classList.add('active');
            let url = '';
            if (this.name === 'allBookList'){
                window.location.href = "/book";
            } else if (this.name === 'myBooks') {
                if(loginId === ''){
                    alert("로그인이 필요한 서비스입니다.");
                    window.location.href = "/login";
                } else {
                    window.location.href = "/book/" + loginId +"/booksByAuthorId";
                }
            } else if (this.name === 'rentalBooks') {
                if(loginId === ''){
                    alert("로그인이 필요한 서비스입니다.");
                    window.location.href = "/login";
                } else{
                    window.location.href = "/book/" + loginId +"/booksByBookRentalId";
                }
            } else {
                if(loginId === ''){
                    alert("로그인이 필요한 서비스입니다.");
                    window.location.href = "/login";
                } else {
                    window.location.href = "/member/" + loginId +"/edit";
                }

            }
        });
    });
}

function getLoginInfo() {
    fetch('/findLoginInfo')
        .then(response => {
            if (!response.ok) {
                throw new Error('요청 실패');
            }
            return response.json();
        })
        .then(data => {
            if(data.loginId == null && data.loginName == null){
                document.getElementById("loginInfo").style.display = 'none';
            } else {
                document.getElementById("loginInfo").style.display = '';
                document.getElementById("headerLoginId").textContent = data.loginId;
                document.getElementById("headerLoginName").textContent = data.loginName;
            }
            // 서버로부터 받은 데이터 처리
            console.log(data);
        })
        .catch(error => {
            console.error('에러:', error);
        });
}
document.addEventListener('DOMContentLoaded', function () {
    getLoginInfo();
    navActive();
})