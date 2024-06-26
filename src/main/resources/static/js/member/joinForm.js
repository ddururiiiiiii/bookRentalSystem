function idValidate(){
    let idInput = document.getElementById('memberId');
    let idError = document.getElementById('idError');

    if (idInput.value.trim() === '') {
        idInput.classList.add('is-invalid');
        idError.style.display = '';
        idError.textContent = 'ID는 필수입니다.';
    } else {
        idInput.classList.remove('is-invalid');
        idError.style.display = 'none';
    }

    let idRegExp = /^[a-zA-Z0-9!_.-]{4,12}$/;
    if( !idRegExp.test(idInput.value) ) {
        idInput.classList.add('is-invalid');
        idError.style.display = '';
        idError.textContent = '4~12글자, 영어나 숫자만 가능합니다. (입력불가특수문자 : @\#$%&=/)';
    } else {
        idInput.classList.remove('is-invalid');
        idError.style.display = 'none';
    }
}

function nameValidate(){
    let memberNameInput = document.getElementById('memberName');
    let memberNameError = document.getElementById('memberNameError');

    if (memberNameInput.value.trim() === '') {
        memberNameInput.classList.add('is-invalid');
        memberNameError.style.display = '';
        memberNameError.textContent = '닉네임은 필수입니다.';
    } else {
        memberNameInput.classList.remove('is-invalid');
        memberNameError.textContent = 'none';
    }

    let nicknameRegExp = /^[a-zA-Z0-9가-힣]{1,20}$/;
    if( !nicknameRegExp.test(memberNameInput.value) ) {
        memberNameInput.classList.add('is-invalid');
        memberNameError.style.display = '';
        memberNameError.textContent = '1~20글자, 한글, 영어, 숫자만 가능합니다.';
    } else {
        memberNameInput.classList.remove('is-invalid');
        memberNameError.style.display = 'none';
    }
}

function passwordValidate() {
    let passwordInput = document.getElementById('password');
    let passwordError = document.getElementById('passwordError');
    if (passwordInput.value.trim() === '') {
        passwordInput.classList.add('is-invalid');
        passwordError.textContent = '비밀번호는 필수 입니다.';
    } else {
        passwordInput.classList.remove('is-invalid');
        passwordError.textContent = '';
    }

    let passwordRegExp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+{};:,<.>]).{8,}$/;
    if (!passwordRegExp.test(passwordInput.value)) {
        passwordInput.classList.add('is-invalid');
        passwordError.style.display = '';
        passwordError.textContent = '최소 8자 이상 영문, 한글, 숫자, 특수문자가 포함되어야 합니다.';
    } else {
        passwordInput.classList.remove('is-invalid');
        passwordError.style.display = 'none';
    }
}

function checkPasswordValidate(){
    let passwordInput = document.getElementById('password');
    let checkPasswordInput = document.getElementById('checkPassword');

    let checkPasswordSuccess = document.getElementById('checkPasswordSuccess');
    let checkPasswordError = document.getElementById('checkPasswordError');

    if ( passwordInput.value.trim() !== '' ) {
        if (passwordInput.value === checkPasswordInput.value) {
            checkPasswordSuccess.style.display = '';
            checkPasswordError.style.display = 'none';
            checkPasswordInput.classList.remove('is-invalid');
            checkPasswordInput.classList.add('is-valid');
            checkPasswordSuccess.textContent = '비밀번호가 일치합니다.';
        } else {
            checkPasswordSuccess.style.display = 'none';
            checkPasswordError.style.display = '';
            checkPasswordInput.classList.add('is-invalid');
            checkPasswordInput.classList.remove('is-valid');
            checkPasswordError.textContent = '비밀번호가 일치하지 않습니다.';
        }
    }
}

function idCheck() {
    let idInput = document.getElementById('memberId');
    let idError = document.getElementById('idError');
    let idNotError = document.getElementById('idNotError');

    fetch("/" + idInput.value + "/idCheck", {
        method : "POST"
    })
        .then(response => {
            if(response.ok){
                console.log("아이디 사용 가능");
                idInput.classList.remove('is-invalid');
                idInput.classList.add('is-valid');
                idError.style.display = 'none';
                idNotError.textContent = '사용 가능한 아이디 입니다.';
                idNotError.style.display = '';
            } else {
                console.log("이미 사용 중인 아이디 입니다.");
                idInput.classList.remove('is-valid');
                idInput.classList.add('is-invalid');
                idError.style.display = '';
                idError.textContent = '이미 사용 중인 아이디 입니다.';
                idError.style.display = '';
                idNotError.style.display = 'none';
            }
        })
        .catch(error => {
            console.error("error", error);
        })
}

document.addEventListener('DOMContentLoaded', function () {

    document.getElementById('saveBtn').addEventListener('click', function(event) {
        event.preventDefault();

        idValidate();
        idCheck();
        nameValidate();
        passwordValidate();
        checkPasswordValidate();

        if (   document.getElementById('memberId').classList.contains('is-valid')
            && !document.getElementById('memberName').classList.contains('is-invalid')
            && !document.getElementById('password').classList.contains('is-invalid')
            && document.getElementById('checkPassword').classList.contains('is-valid') ) {
            document.getElementById('joinForm').submit();
        }
    });

    document.getElementById('memberId').addEventListener('keyup', function() { idValidate(); });
    document.getElementById('memberName').addEventListener('keyup', function() { nameValidate(); });
    document.getElementById('password').addEventListener('keyup', function() { passwordValidate(); });
    document.getElementById('checkPassword').addEventListener('keyup', function() { checkPasswordValidate(); });


    document.getElementById('idCheck').addEventListener('click', idCheck);
});
