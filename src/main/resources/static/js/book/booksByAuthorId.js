document.addEventListener('DOMContentLoaded', function () {
    document.getElementById("searchBookBtn").addEventListener("click", searchBooks);
    document.getElementById("searchKeyword").addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            searchBooks();
        }
    });

    document.getElementById("onlyAvailableBooks").addEventListener("change", searchBooks); // 📌 체크박스 변경 시 자동 검색

    // 📌 검색 후에도 입력 값과 체크박스 유지
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

function searchBooks() {
    // 📌 URL에서 authorId 가져오기
    const urlParts = window.location.pathname.split("/");
    const authorId = urlParts[urlParts.length - 2];

    let category = document.getElementById("searchCategory").value;
    let keyword = document.getElementById("searchKeyword").value.trim();
    let onlyAvailable = document.getElementById("onlyAvailableBooks").checked;

    let url = `/book/${authorId}/booksByAuthorId?category=${category}&keyword=${encodeURIComponent(keyword)}&onlyAvailable=${onlyAvailable}`;
    window.location.href = url;
}
