package seulgi.bookRentalSystem.domain.book;

import java.util.List;

public interface Bookservice {

   List<Book> allBookList(int page, int size);

   int countBooks();

   void addBook(Book book);

   Book findByBookId(String bookId);

    void editBook(String bookId, EditForm editForm);

    String makingBookId();

    List<BookStateCode> allBookStateCodeList();

    List<BookStateCode> allRentalStateCodeList();

    List<Book> findByAuthorId(String authorId, int page, int size);

    int countFindByAuthorId(String authorId);

    List<BookRental> findByBookRentalId(String bookRentalId, int page, int size);

    int countFindByBookRentalId(String bookRentalId);

    String findRentalIdByBookId(String bookId);

    void insertRental (BookRental bookRental);

    void updateBookState (Book book);

    void returnBook (BookRental bookRental);

    void deleteBook (String bookId);

}
