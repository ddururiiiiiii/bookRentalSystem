package seulgi.bookRentalSystem.web.book;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seulgi.bookRentalSystem.domain.book.*;
import seulgi.bookRentalSystem.domain.member.Member;
import seulgi.bookRentalSystem.domain.member.MemberServiceImpl;
import seulgi.bookRentalSystem.domain.member.UpdateForm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl bookService;

    /**
     * 책 등록 폼
     * @param model
     * @return
     */
    @GetMapping("/addBook")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        return "book/addBookForm";
    }

    /**
     * 책 등록
     * @param book
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book
    , RedirectAttributes redirectAttributes, HttpServletRequest request){

        String loginId = (String)request.getSession().getAttribute("loginId");
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        String newBookId = bookService.makingBookId();

        Book addBook = new Book(newBookId, book.getBookName(), book.getBookWriter()
                                , loginId, book.getIsbn(), book.getPublisher()
                                , book.getThumbnailImg(), StateCodeConst.ABLE, formattedTime);
        bookService.addBook(addBook);

        redirectAttributes.addAttribute("bookId", addBook.getBookId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/book/" + addBook.getBookId();
    }

    /**
     * 책 단건 조회
     * @param bookId
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/{bookId}")
    public String book(@PathVariable String bookId, Model model
    ,HttpServletRequest request){
        String loginId = (String) request.getSession().getAttribute("loginId");
        String rentalId = bookService.findRentalIdByBookId(bookId);
        Book book = bookService.findByBookId(bookId);
        book.setAuthorName(book.getAuthorName());
        book.setBookRentalId(rentalId);
        // 📌 대여 이력 추가
        List<BookRental> rentalHistory = bookService.findRentalHistoryByBookId(bookId);


        model.addAttribute("loginId", loginId);
        model.addAttribute("book", book);
        model.addAttribute("rentalHistory", rentalHistory); // 📌 대여 기록 모델 추가
        return "book/book";
    }

    /**
     * 책 전체 조회
     * @param model
     * @param request
     * @param page
     * @param size
     * @param category
     * @param keyword
     * @param onlyAvailable
     * @return
     */
    @GetMapping
    public String allBookList(Model model, HttpServletRequest request
        , @RequestParam(defaultValue = "1") int page
        , @RequestParam(defaultValue = "10") int size
        , @RequestParam(defaultValue = "all") String category // 📌 검색 기준 추가
        , @RequestParam(defaultValue = "") String keyword// 📌 검색어 추가
        , @RequestParam(defaultValue = "false") boolean onlyAvailable){
        String loginId = (String) request.getSession().getAttribute("loginId");
        List<Book> books = bookService.searchBooks(category, keyword, onlyAvailable, page, size);
        int totalBooks = bookService.countSearchBooks(category, keyword, onlyAvailable);

        int totalPages = (int) Math.ceil((double) totalBooks / size);

        model.addAttribute("loginId", loginId);
        model.addAttribute("books", books);
        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);
        model.addAttribute("onlyAvailable", onlyAvailable);
        return "book/allBookList";
    }

    /**
     * 책 수정 폼
     * @param bookId
     * @param model
     * @return
     */
    @GetMapping("/{bookId}/edit")
    public String editForm(@PathVariable String bookId, Model model){
        Book book = bookService.findByBookId(bookId);
        List<BookStateCode> bookStateCodes = bookService.allBookStateCodeList();
        model.addAttribute("bookStateCodes", bookStateCodes);
        model.addAttribute("book", book);
        return "book/bookEditForm";
    }

    /**
     * 책 정보 수정
     * @param bookId
     * @param form
     * @return
     */
    @PostMapping("/{bookId}/edit")
    public String edit(@PathVariable String bookId, @ModelAttribute("book") EditForm form){
        bookService.editBook(bookId, form);
        return "redirect:/book/{bookId}";
    }

    /**
     * 나의책 목록 조회
     * @param authorId
     * @param model
     * @param page
     * @param size
     * @param category
     * @param keyword
     * @param onlyAvailable
     * @return
     */
    @GetMapping("/{authorId}/booksByAuthorId")
    public String  booksByAuthorId(@PathVariable String authorId, Model model
        , @RequestParam(defaultValue =  "1") int page
        , @RequestParam(defaultValue = "10") int size
        , @RequestParam(defaultValue = "all") String category
        , @RequestParam(defaultValue = "") String keyword
        , @RequestParam(defaultValue = "false") boolean onlyAvailable){

        List<Book> books = bookService.searchBooksByAuthorId(authorId, category, keyword, onlyAvailable, page, size);
        int totalBooksByAuthorId = bookService.countSearchBooksByAuthorId(authorId, category, keyword, onlyAvailable);
        int totalPages = (int) Math.ceil((double) totalBooksByAuthorId / size);

        model.addAttribute("books", books);
        model.addAttribute("totalBooks", totalBooksByAuthorId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);
        model.addAttribute("onlyAvailable", onlyAvailable);

        return "book/booksByAuthorId";
    }

    /**
     * 빌린책 목록 조회
     * @param bookRentalId
     * @param model
     * @param page
     * @param size
     * @param category
     * @param keyword
     * @return
     */
    @GetMapping("/{bookRentalId}/booksByBookRentalId")
    public String booksByBookRentalId(@PathVariable String bookRentalId, Model model
        , @RequestParam(defaultValue = "1") int page
        , @RequestParam(defaultValue = "10") int size
        , @RequestParam(defaultValue = "all") String category
        , @RequestParam(defaultValue = "") String keyword
        , @RequestParam(defaultValue = "false") boolean onlyRental){

        List<BookRental> rentalBooks = bookService.searchBooksByBookRentalId(bookRentalId, category, keyword, onlyRental, page, size);
        int totalBooksByBookRentalId = bookService.countSearchBooksByBookRentalId(bookRentalId, category, keyword, onlyRental);
        int totalPages = (int) Math.ceil((double) totalBooksByBookRentalId / size);

        List<BookStateCode> bookStateCodes = bookService.allRentalStateCodeList();
        model.addAttribute("bookRentalId", bookRentalId);
        model.addAttribute("bookStateCodes", bookStateCodes);
        model.addAttribute("rentalBooks", rentalBooks);
        model.addAttribute("totalBooks", totalBooksByBookRentalId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);
        model.addAttribute("onlyRental", onlyRental);
        return "book/booksByBookRentalId";
    }

    /**
     * 책 대여
     * @param bookId
     * @param request
     * @return
     */
    @PostMapping("/{bookId}/rental")
    public ResponseEntity<String> rentalBook (@PathVariable String bookId, HttpServletRequest request){
        String loginId = (String) request.getSession().getAttribute("loginId") ;
        BookRental bookRental = new BookRental();
        bookRental.setBookId(bookId);
        bookRental.setBookRentalId(loginId);
        bookRental.setBookStateCode("RENTAL");
        bookService.insertRental(bookRental);

        Book book = new Book();
        book.setBookId(bookId);
        book.setBookStateCode("UNABLE");
        bookService.updateBookState(book);
        return ResponseEntity.ok("대여완료");
    }

    /**
     * 책 반납
     * @param bookId
     * @param request
     * @return
     */
    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable String bookId, HttpServletRequest request){

        String loginId = (String) request.getSession().getAttribute("loginId");
        BookRental bookRental = new BookRental();
        bookRental.setBookId(bookId);
        bookRental.setBookRentalId(loginId);
        bookRental.setBookStateCode("RETURN");
        bookService.returnBook(bookRental);

        Book book = new Book();
        book.setBookId(bookId);
        book.setBookStateCode("ABLE");
        bookService.updateBookState(book);
        return ResponseEntity.ok("반납 완료");
    }

    /**
     * 책 삭제 (논리삭제)
     * @param bookId
     * @return
     */
    @PostMapping("/{bookId}/deleteBook")
    public ResponseEntity<String> deleteBook (@PathVariable String bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("삭제 완료");
    }

    /**
     * 도서검색 API 팝업
     * @return
     */
    @GetMapping("/searchBookPopupOpen")
    public String searchBook (){
        return "book/searchBook";
    }

}