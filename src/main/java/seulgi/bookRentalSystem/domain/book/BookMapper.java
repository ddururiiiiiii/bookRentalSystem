package seulgi.bookRentalSystem.domain.book;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    //책 ID 생성
    String makingBookId();

    //책 등록
    void addBook(@Param("book") Book book);

    //책 수정
    void editBook(@Param("bookId") String bookId, @Param("book") Book book);

    //책 (논리)삭제
    void deleteBook(@Param("bookId") String bookId);

    //책ID 로 조회
    Book findByBookId(@Param("bookId") String bookId);

    //책 대여 기록 조회
    List<BookRental> findRentalHistoryByBookId(@Param("bookId") String bookId);

    //모든 책 조회
    List<Book> allBookList(@Param("offset") int offset, @Param("limit") int limit);

    //모든 책 조회 건수
    int countBooks();

    //모든 책 조회 (검색 조건)
    List<Book> searchBooks(@Param("category") String category,
                           @Param("keyword") String keyword,
                           @Param("onlyAvailable") boolean onlyAvailable,
                           @Param("offset") int offset,
                           @Param("limit") int limit);

    //모든 책 조회 (검색 조건) 건수
    int countSearchBooks(@Param("category") String category,
                         @Param("keyword") String keyword,
                         @Param("onlyAvailable") boolean onlyAvailable);

    //책 상태 조회
    List<BookStateCode> allBookStateCodeList();

    //대여 상태 조회
    List<BookStateCode> allRentalStateCodeList();

    //소유자 ID로 책 조회
    List<Book> findByAuthorId(@Param("authorId") String authorId, @Param("offset") int offset, @Param("limit") int limit);

    //소유자 ID로 책 조회 건수
    int countFindByAuthorId(@Param("authorId") String authorId);

    //대여한 사람 ID로 책 조회
    List<BookRental> findByBookRentalId(@Param("bookRentalId") String bookRentalId, @Param("offset") int offset, @Param("limit") int limit);

    //대여 한 사람 ID로 책 조회 건수
    int countFindByBookRentalId(@Param("bookRentalId") String bookRentalId);

    //소유자 ID로 책 조회 (검색 조건)
    List<Book> searchBooksByAuthorId(@Param("authorId") String authorId,
                                     @Param("category") String category,
                                     @Param("keyword") String keyword,
                                     @Param("onlyAvailable") boolean onlyAvailable,
                                     @Param("offset") int offset,
                                     @Param("limit") int limit);

    //소유자 ID로 책 조회 (검색 조건) 건수
    int countSearchBooksByAuthorId(@Param("authorId") String authorId,
                                   @Param("category") String category,
                                   @Param("keyword") String keyword,
                                   @Param("onlyAvailable") boolean onlyAvailable);

    //대여한 사람 ID로 책 조회 (검색 조건)
    List<BookRental> searchBooksByBookRentalId(
            @Param("bookRentalId") String bookRentalId,
            @Param("category") String category,
            @Param("keyword") String keyword,
            @Param("onlyRental") boolean onlyRental,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    //대여한 사람 ID로 책 조회 (검색 조건) 건수
    int countSearchBooksByBookRentalId(
            @Param("bookRentalId") String bookRentalId,
            @Param("category") String category,
            @Param("keyword") String keyword,
            @Param("onlyRental") boolean onlyRental
    );

    //책 ID로 가장 마지막 대여 조회
    String findRentalIdByBookId(@Param("bookId") String bookId);

    //책 빌리기
    void insertRental(@Param("bookRental") BookRental bookRental);

    //책 반납 하기
    void returnBook(@Param("bookRental") BookRental bookRental);

    //책 상태 변경
    void updateBookState(@Param("book") Book book);

}
