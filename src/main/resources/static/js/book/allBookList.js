
function addBookBtn() {
    let loginId = document.getElementById("loginId").textContent;
    if (loginId === ''){
        alert("로그인이 필요한 서비스입니다.");
        window.location.href = "/login";
    } else {
        window.location.href = "/book/addBook";
    }
}

function searchBooks() {
    let category = document.getElementById("searchCategory").value;
    let keyword = document.getElementById("searchKeyword").value.trim();
    let onlyAvailable = document.getElementById("onlyAvailableBooks").checked;

    let url = `/book?category=${category}&keyword=${encodeURIComponent(keyword)}&onlyAvailable=${onlyAvailable}`;
    window.location.href = url;
}

document.addEventListener('DOMContentLoaded', function (){
    document.getElementById("addBookBtn").addEventListener('click', addBookBtn);

    // 📌 검색 버튼 클릭 시 검색 실행
    document.getElementById("searchBookBtn").addEventListener("click", searchBooks);

    // 📌 Enter 키 입력 시 검색 실행
    document.getElementById("searchKeyword").addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            searchBooks();
        }
    });

    // 📌 대여 가능 책만 보기 체크박스 이벤트
    document.getElementById("onlyAvailableBooks").addEventListener("change", searchBooks);

// 📌 검색 후에도 입력 값과 체크박스 유지 (URL에서 값 읽어오기)
    const params = new URLSearchParams(window.location.search);
    if (params.has("category")) {
        document.getElementById("searchCategory").value = params.get("category");
    }
    if (params.has("keyword")) {
        document.getElementById("searchKeyword").value = params.get("keyword");
    }
    if (params.has("onlyAvailable")) {
        document.getElementById("onlyAvailableBooks").checked = params.get("onlyAvailable") === "true";
    }

});