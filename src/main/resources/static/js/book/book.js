
function rentalBtn(){
    let bookId = document.getElementById("bookId").value;
    let loginId = document.getElementById("headerLoginId").textContent;

    if (!loginId || loginId.trim() === '') {  // 📌 로그인 여부 체크 수정
        alert("로그인이 필요한 서비스입니다.");
        window.location.href = "/login?redirectURL=/book/" + bookId;
        return;
    }

    if (confirm("책을 대여 하시겠습니까?")){
        fetch("/book/" + bookId + "/rental", {
            method : "POST"
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/book/" + bookId;
                    alert("대여가 완료되었습니다.");
                } else {
                    console.error("대여 실패");
                }
            })
            .catch(error => {
                console.error("Error : ", error);
            });
    }
}

function returnBtn(){
    let bookId = document.getElementById("bookId").value;

    if (confirm("책을 반납 하시겠습니까?")){
        fetch("/book/" + bookId + "/return", {
            method : "POST"
        })
            .then(response => {
                if (response.ok){
                    window.location.href = "/book/" + bookId;
                    alert("반납이 완료되었습니다.");
                } else {
                    console.log("반납 실패");
                }
            })
            .catch(error => {console.error("Error : ", error)})
    }
}

function deleteBtn(){
    let bookId = document.getElementById("bookId").value;
    let bookStateCode = document.getElementById("bookStateCode").value;

    if(bookStateCode == '대여불가능'){
        alert("대여중 일 때는 삭제가 불가능합니다.");
        return;
    }

    if (confirm("책을 삭제하시겠습니까?")){
        fetch("/book/" + bookId + "/deleteBook", {
            method : "POST"
        })
            .then(response => {
                if (response.ok){
                    alert("삭제가 완료되었습니다.");
                    window.location.href = "/book";
                } else {
                    console.log("삭제 실패");
                }
            })
            .catch(error => {console.error("Error : ", error)})
    }
}
document.addEventListener('DOMContentLoaded', function (){
    let rentalButton = document.getElementById("rentalBtn");
    let returnButton = document.getElementById("returnBtn");
    let deleteButton = document.getElementById("deleteBtn");

    if (rentalButton){
        document.getElementById("rentalBtn").addEventListener('click', rentalBtn);
    }

    if (returnButton) {
        document.getElementById("returnBtn").addEventListener('click', returnBtn);
    }

    if (deleteButton) {
        document.getElementById("deleteBtn").addEventListener('click', deleteBtn);
    }

    // 책 이미지 표시 여부 설정
    const bookImage = document.getElementById("bookImage");
    // 이미지 URL이 잘못된 경우 숨김
    if (!bookImage.src || bookImage.src.includes("/book/BOOK")) {
        bookImage.style.display = "none";
    } else {
        bookImage.style.display = "block";
    }

})