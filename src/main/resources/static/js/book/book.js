
const loginId = document.getElementById("loginId").textContent;
function addBookBtn() {
    if (loginId === ''){
        alert("로그인이 필요한 서비스입니다.");
        window.location.href = "/login";
    } else {
        window.location.href = "/book/addBook";
    }
}

function rentalBtn(){
    let bookId = document.getElementById("bookId")
    if (loginId === ''){
        alert("로그인이 필요한 서비스입니다.");
        window.location.href = "/login";
    } else {
        window.location.href = "/book/" + bookId + "/rental";
    }
}
document.addEventListener('DOMContentLoaded', function (){
    document.getElementById("addBookBtn").addEventListener('click', function (){addBookBtn();});
})