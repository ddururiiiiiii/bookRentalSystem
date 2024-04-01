package seulgi.bookRentalSystem.domain.book;

import java.util.List;

public interface Bookservice {

    public List<Book> allBookList();

    void addBook(Book book);

    public Book findByBookId(String bookId);

    public void editBook(String bookId, EditForm editForm);

    public String makingBookId();

    public List<BookStateCode> allBookStateCodeList();

    public List<BookStateCode> allRentalStateCodeList();

    public List<Book> findByAuthorId(String authorId);

    public List<BookRental> findByBookRentalId(String bookRentalId);

    String findRentalIdByBookId(String bookId);


    void insertRental (BookRental bookRental);

    void updateBookState (Book book);


}
