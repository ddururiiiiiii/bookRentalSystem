package seulgi.bookRentalSystem.domain.book;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> allBookList();

    void addBook(@Param("book") Book book);

    Book findByBookId(@Param("bookId") String bookId);

    void editBook(@Param("bookId") String bookId, @Param("book") Book book);

    String makingBookId();

    List<BookStateCode> allBookStateCodeList();

    List<BookStateCode> allRentalStateCodeList();

    List<Book> findByAuthorId(@Param("authorId") String authorId);

    List<BookRental> findByBookRentalId(@Param("bookRentalId") String bookRentalId);

    String findRentalIdByBookId(@Param("bookId") String bookId);

    void insertRental(@Param("bookRental") BookRental bookRental);

    void updateBookState(@Param("book") Book book);

    void returnBook(@Param("bookRental") BookRental bookRental);

    void deleteBook(@Param("bookId") String bookId);

}
