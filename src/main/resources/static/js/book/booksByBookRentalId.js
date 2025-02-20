document.addEventListener('DOMContentLoaded', function () {
    document.getElementById("searchBookBtn").addEventListener("click", searchBooks);
    document.getElementById("searchKeyword").addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            searchBooks();
        }
    });
    // 📌 체크박스를 클릭하면 자동으로 검색 실행
    document.getElementById("onlyRental").addEventListener("change", searchBooks);

    // 📌 bookRentalId를 hidden input에서 가져오기 (올바른 방식)
    const bookRentalIdElement = document.getElementById("bookRentalId");
    const bookRentalId = bookRentalIdElement ? bookRentalIdElement.value.trim() : null; // 📌 `.value` 추가

    if (!bookRentalId) {
        console.error("bookRentalId가 정의되지 않았습니다.");
        return;
    }

    // 📌 검색 후에도 입력 값 유지
    const params = new URLSearchParams(window.location.search);
    if (params.has("category")) {
        document.getElementById("searchCategory").value = params.get("category");
    }
    if (params.has("keyword")) {
        document.getElementById("searchKeyword").value = params.get("keyword");
    }
});

function searchBooks() {
    // 📌 다시 bookRentalId 가져오기 (올바른 방식)
    const bookRentalId = document.getElementById("bookRentalId").value.trim();

    let category = document.getElementById("searchCategory").value;
    let keyword = document.getElementById("searchKeyword").value.trim();
    let onlyRental = document.getElementById("onlyRental").checked;

    if (!bookRentalId) {
        console.error("bookRentalId가 정의되지 않았습니다.");
        return;
    }

    let url = `/book/${bookRentalId}/booksByBookRentalId?category=${category}&keyword=${encodeURIComponent(keyword)}&onlyRental=${onlyRental}`;
    window.location.href = url;
}