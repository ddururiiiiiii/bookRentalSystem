package seulgi.bookRentalSystem.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements  Bookservice {

    private final BookMapper bookMapper;

    @Override
    public List<Book> allBookList(int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.allBookList(offset, size);
    }

    @Override
    public int countBooks() {
        return bookMapper.countBooks();
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public Book findByBookId(String bookId) {
        return bookMapper.findByBookId(bookId);
    }

    @Override
    public void editBook(String bookId, EditForm editForm) {
        Book findBook = findByBookId(bookId);
        findBook.setBookName(editForm.getBookName());
        findBook.setBookWriter(editForm.getBookWriter());
        findBook.setBookStateCode(editForm.getBookStateCode());
        bookMapper.editBook(bookId, findBook);
    }

    @Override
    public String makingBookId() {
        return bookMapper.makingBookId();
    }

    @Override
    public List<BookStateCode> allBookStateCodeList() {
        return bookMapper.allBookStateCodeList();
    }

    @Override
    public List<BookStateCode> allRentalStateCodeList() {
        return bookMapper.allRentalStateCodeList();
    }

    @Override
    public List<Book> findByAuthorId(String authorId, int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.findByAuthorId(authorId, offset, size);
    }

    @Override
    public int countFindByAuthorId(String authorId) {
        return bookMapper.countFindByAuthorId(authorId);
    }

    @Override
    public List<BookRental> findByBookRentalId(String bookRentalId, int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.findByBookRentalId(bookRentalId, offset, size);
    }

    @Override
    public int countFindByBookRentalId(String bookRentalId) {
        return bookMapper.countFindByBookRentalId(bookRentalId);
    }

    @Override
    public String findRentalIdByBookId(String bookId) {
        return bookMapper.findRentalIdByBookId(bookId);
    }

    @Override
    public void insertRental(BookRental bookRental) {
        bookMapper.insertRental(bookRental);
    }

    @Override
    public void updateBookState(Book book) {
        bookMapper.updateBookState(book);
    }

    @Override
    public void returnBook(BookRental bookRental) {
        bookMapper.returnBook(bookRental);
    }

    @Override
    public void deleteBook(String bookId) {
        bookMapper.deleteBook(bookId);
    }


}
