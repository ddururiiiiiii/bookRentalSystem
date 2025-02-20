package seulgi.bookRentalSystem.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    //책 ID 생성
    @Override
    public String makingBookId() {
        return bookMapper.makingBookId();
    }

    //책 등록
    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    //책 수정
    @Override
    public void editBook(String bookId, EditForm editForm) {
        Book findBook = findByBookId(bookId);
        findBook.setBookName(editForm.getBookName());
        findBook.setBookWriter(editForm.getBookWriter());
        findBook.setBookStateCode(editForm.getBookStateCode());
        bookMapper.editBook(bookId, findBook);
    }

    //책 (논리)삭제
    @Override
    public void deleteBook(String bookId) {
        bookMapper.deleteBook(bookId);
    }

    //책ID 로 조회
    @Override
    public Book findByBookId(String bookId) {
        return bookMapper.findByBookId(bookId);
    }

    //책 대여 기록 조회
    @Override
    public List<BookRental> findRentalHistoryByBookId(String bookId) {
        return bookMapper.findRentalHistoryByBookId(bookId);
    }

    //모든 책 조회
    @Override
    public List<Book> allBookList(int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.allBookList(offset, size);
    }

    //모든 책 조회 건수
    @Override
    public int countBooks() {
        return bookMapper.countBooks();
    }

    //모든 책 조회 (검색 조건)
    @Override
    public List<Book> searchBooks(String category, String keyword, boolean onlyAvailable, int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.searchBooks(category, keyword, onlyAvailable, offset, size);
    }

    //모든 책 조회 (검색 조건) 건수
    @Override
    public int countSearchBooks(String category, String keyword, boolean onlyAvailable) {
        return bookMapper.countSearchBooks(category, keyword, onlyAvailable);
    }

    //책 상태 조회
    @Override
    public List<BookStateCode> allBookStateCodeList() {
        return bookMapper.allBookStateCodeList();
    }

    //대여 상태 조회
    @Override
    public List<BookStateCode> allRentalStateCodeList() {
        return bookMapper.allRentalStateCodeList();
    }

    //소유자 ID로 책 조회
    @Override
    public List<Book> findByAuthorId(String authorId, int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.findByAuthorId(authorId, offset, size);
    }

    //소유자 ID로 책 조회 건수
    @Override
    public int countFindByAuthorId(String authorId) {
        return bookMapper.countFindByAuthorId(authorId);
    }

    //대여한 사람 ID로 책 조회
    @Override
    public List<BookRental> findByBookRentalId(String bookRentalId, int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.findByBookRentalId(bookRentalId, offset, size);
    }

    //대여 한 사람 ID로 책 조회 건수
    @Override
    public int countFindByBookRentalId(String bookRentalId) {
        return bookMapper.countFindByBookRentalId(bookRentalId);
    }

    //소유자 ID로 책 조회 (검색 조건)
    @Override
    public List<Book> searchBooksByAuthorId(String authorId, String category, String keyword, boolean onlyAvailable, int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.searchBooksByAuthorId(authorId, category, keyword, onlyAvailable, offset, size);
    }

    //소유자 ID로 책 조회 (검색 조건) 건수
    @Override
    public int countSearchBooksByAuthorId(String authorId, String category, String keyword, boolean onlyAvailable) {
        return bookMapper.countSearchBooksByAuthorId(authorId, category, keyword, onlyAvailable);
    }

    //대여한 사람 ID로 책 조회 (검색 조건)
    @Override
    public List<BookRental> searchBooksByBookRentalId(String bookRentalId, String category, String keyword, boolean onlyRental, int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.searchBooksByBookRentalId(bookRentalId, category, keyword, onlyRental, offset, size);
    }

    //대여한 사람 ID로 책 조회 (검색 조건) 건수
    @Override
    public int countSearchBooksByBookRentalId(String bookRentalId, String category, String keyword, boolean onlyRental) {
        return bookMapper.countSearchBooksByBookRentalId(bookRentalId, category, keyword, onlyRental);
    }

    //책 ID로 가장 마지막 대여 조회
    @Override
    public String findRentalIdByBookId(String bookId) {
        return bookMapper.findRentalIdByBookId(bookId);
    }

    //책 빌리기
    @Override
    public void insertRental(BookRental bookRental) {
        bookMapper.insertRental(bookRental);
    }

    //책 반납 하기
    @Override
    public void returnBook(BookRental bookRental) {
        bookMapper.returnBook(bookRental);
    }

    //책 상태 변경
    @Override
    public void updateBookState(Book book) {
        bookMapper.updateBookState(book);
    }


}
