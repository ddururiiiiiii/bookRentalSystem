document.addEventListener('DOMContentLoaded', function () {
    document.getElementById("searchBookBtn").addEventListener("click", searchBooks);
    document.getElementById("searchKeyword").addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            searchBooks();
        }
    });

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
    let category = document.getElementById("searchCategory").value;
    let keyword = document.getElementById("searchKeyword").value.trim();

    let url = `/book/${bookRentalId}/booksByBookRentalId?category=${category}&keyword=${encodeURIComponent(keyword)}`;
    window.location.href = url;
}
