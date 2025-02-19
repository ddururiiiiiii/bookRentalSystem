package seulgi.bookRentalSystem.domain.book;

import java.util.List;

public interface BookService {

   //책 ID 생성
   String makingBookId();

   //책 등록
   void addBook(Book book);

   //책 수정
   void editBook(String bookId, EditForm editForm);

   //책 (논리)삭제
   void deleteBook (String bookId);

   //책ID 로 조회
   Book findByBookId(String bookId);

   //책 대여 기록 조회
   List<BookRental> findRentalHistoryByBookId(String bookId);

   //모든 책 조회
   List<Book> allBookList(int page, int size);

   //모든 책 조회 건수
   int countBooks();

   //모든 책 조회 (검색 조건)
   List<Book> searchBooks(String category, String keyword, boolean onlyAvailable, int page, int size);

   //모든 책 조회 (검색 조건) 건수
   int countSearchBooks(String category, String keyword, boolean onlyAvailable);

    //책 상태 조회
    List<BookStateCode> allBookStateCodeList();

    //대여 상태 조회
    List<BookStateCode> allRentalStateCodeList();

    //소유자 ID로 책 조회
    List<Book> findByAuthorId(String authorId, int page, int size);

    //소유자 ID로 책 조회 건수
    int countFindByAuthorId(String authorId);

    //대여한 사람 ID로 책 조회
    List<BookRental> findByBookRentalId(String bookRentalId, int page, int size);

    //대여 한 사람 ID로 책 조회 건수
    int countFindByBookRentalId(String bookRentalId);

    //소유자 ID로 책 조회 (검색 조건)
    List<Book> searchBooksByAuthorId(String authorId, String category, String keyword, boolean onlyAvailable, int page, int size);

    //소유자 ID로 책 조회 (검색 조건) 건수
    int countSearchBooksByAuthorId(String authorId, String category, String keyword, boolean onlyAvailable);

    //대여한 사람 ID로 책 조회 (검색 조건)
    List<BookRental> searchBooksByBookRentalId(String bookRentalId, String category, String keyword, int page, int size);

    //대여한 사람 ID로 책 조회 (검색 조건) 건수
    int countSearchBooksByBookRentalId(String bookRentalId, String category, String keyword);

    //책 ID로 가장 마지막 대여 조회
    String findRentalIdByBookId(String bookId);

    //책 빌리기
    void insertRental (BookRental bookRental);

    //책 반납 하기
    void updateBookState (Book book);

    //책 상태 변경
    void returnBook (BookRental bookRental);



}
