
function addBookBtn() {
    let loginId = document.getElementById("loginId").textContent;
    if (loginId === ''){
        alert("로그인이 필요한 서비스입니다.");
        window.location.href = "/login";
    } else {
        window.location.href = "/book/addBook";
    }
}
document.addEventListener('DOMContentLoaded', function (){
    document.getElementById("addBookBtn").addEventListener('click', addBookBtn);
})