let currentPage = 1;
const pageSize = 10;
let selectedBook = null; // 선택된 책 저장

function searchBooks(page = 1) {

    currentPage = page; // 현재 페이지 저장

    const targetMap = {
        "책제목": "title",
        "작가명": "person",
        "출판사": "publisher"
    };

    const targetSelect = document.querySelector("#searchSelect").value;
    const query = document.querySelector('#searchBookKeyword').value;
    const target = targetMap[targetSelect] || "title";

    const REST_API_KEY = '6e7a8310955be85d225d8c3839069e8f';

    fetch(`https://dapi.kakao.com/v3/search/book?target=${target}&query=${encodeURIComponent(query)}&page=${page}&size=${pageSize}`, {
        headers: {
            'Authorization': `KakaoAK ${REST_API_KEY}`
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            displayBooks(data.documents);
            updatePagination(data.meta);
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

function displayBooks(books) {
    const tableBody = document.querySelector("tbody");
    tableBody.innerHTML = ""; // 기존 데이터 초기화
    const totalCnt = books.length;
    document.querySelector("#totalCnt").textContent = `총 ${totalCnt}건`;

    books.forEach((book, index) => {
        const globalIndex = (currentPage - 1) * pageSize + index + 1; // 전체 인덱스 계산
        const row = document.createElement("tr");
        row.innerHTML = `
            <th scope="row">${globalIndex}</th>
            <td>
                <img src="${book.thumbnail || 'https://via.placeholder.com/50'}" alt="책 이미지" style="width: 50px; height: auto; margin-right: 10px;">
            </td>
            <td>${book.title}</td>
            <td>${book.authors.join(", ")}</td>
            <td>${book.publisher}</td>
            <td style="display: none;">${book.isbn}</td>
        `;

        row.addEventListener("click", function () {
            document.querySelectorAll("tbody tr").forEach(tr => tr.style.backgroundColor = ""); // 기존 선택 해제
            row.style.backgroundColor = "#007BFF"; // 선택된 행 색상 변경 (파란색)
            selectedBook = {
                title: book.title,
                authors: book.authors.join(", "),
                publisher: book.publisher,
                isbn: book.isbn,
                thumbnail: book.thumbnail || 'https://via.placeholder.com/150'
            };
        });

        tableBody.appendChild(row);
    });
}

// 📌 선택된 책 정보를 부모 창으로 전달 후 팝업 닫기
function sendSelectedBookToParent() {
    if (selectedBook) {
        window.opener.document.getElementById("bookName").value = selectedBook.title;
        window.opener.document.getElementById("bookWriter").value = selectedBook.authors;
        window.opener.document.getElementById("publisher").value = selectedBook.publisher;
        window.opener.document.getElementById("bookIsbn").value = selectedBook.isbn;
        window.opener.document.getElementById("bookThumbnail").value = selectedBook.thumbnail;

        const imagePreview = window.opener.document.getElementById("imagePreview");
        imagePreview.src = selectedBook.thumbnail; // 선택한 책 이미지 적용
        imagePreview.style.display = "block"; // 선택 후 이미지 표시
        window.close();
    }
}

function updatePagination(meta) {
    const pagination = document.querySelector(".pagination");
    pagination.innerHTML = "";

    // 이전 버튼
    const prevItem = document.createElement("li");
    prevItem.classList.add("page-item");
    prevItem.innerHTML = `<a class="page-link" href="#">Previous</a>`;
    prevItem.onclick = () => {
        if (currentPage > 1) searchBooks(currentPage - 1);
    };
    if (currentPage === 1) prevItem.classList.add("disabled");
    pagination.appendChild(prevItem);

    // 페이지 번호
    for (let i = 1; i <= Math.min(meta.pageable_count / pageSize, 5); i++) {
        const pageItem = document.createElement("li");
        pageItem.classList.add("page-item");
        if (i === currentPage) pageItem.classList.add("active");
        pageItem.innerHTML = `<a class="page-link" href="#">${i}</a>`;
        pageItem.onclick = () => searchBooks(i);
        pagination.appendChild(pageItem);
    }

    // 다음 버튼
    const nextItem = document.createElement("li");
    nextItem.classList.add("page-item");
    nextItem.innerHTML = `<a class="page-link" href="#">Next</a>`;
    nextItem.onclick = () => {
        if (currentPage < meta.pageable_count / pageSize) searchBooks(currentPage + 1);
    };
    if (currentPage >= meta.pageable_count / pageSize) nextItem.classList.add("disabled");
    pagination.appendChild(nextItem);
}


document.addEventListener('DOMContentLoaded', function (){
    document.getElementById("searchBookBtn").addEventListener("click", function (event) {
        searchBooks();
    });

    document.querySelector("input").addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            searchBooks();
        }
    });

    document.getElementById("selectBookBtn").addEventListener("click", function () {
        if (selectedBook) {
            sendSelectedBookToParent();
        } else {
            alert("선택된 책이 없습니다.");
        }
    });
})