

function showButton(btn){
    btn.style.display = '';
}
function hideButton(btn){
    btn.style.display = 'none';
}
function goLogin(){
    alert("로그인이 필요한 서비스입니다.");
    window.location.href = "/login";
}

function nvl(val, exp){
    if (val === '' || val === undefined || val === null){
        return exp;
    }
}

function menuClick(){
    let menuItems = document.querySelectorAll('.nav-link');

    menuItems.forEach(function(item) {
        item.addEventListener('click', function (event) {
            let menuItemName = event.target.getAttribute('name');
            let loginId = document.getElementById('headerLoginId').textContent;

            switch (menuItemName) {
                case 'allBookList':
                    window.location.href = "/book";
                    break;
                case 'myBooks':
                    if (loginId === '') {
                        goLogin();
                        return;
                    } else {
                        window.location.href = "/book/" + loginId + "/booksByAuthorId";
                        break;
                    }
                case 'rentalBooks':
                    if (loginId === '') {
                        goLogin();
                        return;
                    } else {
                        window.location.href = "/book/" + loginId + "/booksByBookRentalId";
                        break;
                    }
                case 'myInfo':
                    if (loginId === '') {
                        goLogin();
                        return;
                    } else {
                        window.location.href = "/member/" + loginId;
                        break;
                    }
            }
        });
    });
}


function getLoginInfo() {
    let loginButton = document.getElementById('loginBtn');
    let logoutButton = document.getElementById('logoutBtn');
    let joinButton = document.getElementById('joinBtn');

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
                showButton(loginButton);
                hideButton(logoutButton);
            } else {
                document.getElementById("loginInfo").style.display = '';
                document.getElementById("headerLoginId").textContent = data.loginId;
                document.getElementById("headerLoginName").textContent = data.loginName;
                showButton(logoutButton);
                hideButton(loginButton);
                hideButton(joinButton);
            }
            console.log(data);
        })
        .catch(error => {
            console.error('에러:', error);
        });
}

document.addEventListener('DOMContentLoaded', function () {
    getLoginInfo();
    menuClick();
});