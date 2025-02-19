

function searchBookPopupOpen(){
    let url = "/book/searchBookPopupOpen";
    let windowName = "도서검색 API";
    let windowFeatures = "width=1200,height=550";
    window.open(url, windowName, windowFeatures);
}
function addBookBtn(){
    let bookName = document.getElementById("bookName").value;
    let bookWriter = document.getElementById("bookWriter").value;
    let bookIsbn = document.getElementById("bookIsbn").value;
    let publisher = document.getElementById("publisher").value;

    if (bookName === "" || bookWriter === "" || publisher === "" || bookIsbn === ""){
        alert("검색을 통해 도서를 선택해주세요.");
        return;
    }
    document.getElementById("joinForm").submit();
}

function handleSaveClick(event) {
    event.preventDefault(); // 기본 동작 방지
    addBookBtn();
}
document.addEventListener('DOMContentLoaded', function (){
    const saveBtn = document.getElementById("saveBtn");

    document.getElementById("searchBookPopupOpen").addEventListener("click", function (event) {
        event.preventDefault();  // 기본 동작 방지
        searchBookPopupOpen();
    });
    // 기존 이벤트가 존재하면 제거하고 다시 추가
    saveBtn.removeEventListener("click", handleSaveClick);
    saveBtn.addEventListener("click", handleSaveClick);

})