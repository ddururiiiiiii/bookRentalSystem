package seulgi.bookRentalSystem.web.book;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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

        Book addBook = new Book();
        addBook.setBookId(newBookId);
        addBook.setBookName(book.getBookName());
        addBook.setBookWriter(book.getBookWriter());
        addBook.setAuthorId(loginId);
        addBook.setBookStateCode(StateCodeConst.ABLE);
        addBook.setCreateDate(formattedTime);
        bookService.addBook(addBook);

        redirectAttributes.addAttribute("bookId", addBook.getBookId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/book/" + addBook.getBookId();
    }

    /**
     * 책 단건 조회
     * @param bookId
     * @param model
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
        model.addAttribute("loginId", loginId);
        model.addAttribute("book", book);
        return "book/book";
    }

    /**
     * 책 전체 조회
     * @param model
     * @return
     */
    @GetMapping
    public String allBookList(Model model, HttpServletRequest request){
        String loginId = (String) request.getSession().getAttribute("loginId");
        List<Book> books = bookService.allBookList();
        model.addAttribute("loginId", loginId);
        model.addAttribute("books", books);
        return "book/allBookList";
    }

    /**
     * 책 정보 수정 폼
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
     * @return
     */
    @GetMapping("/{authorId}/booksByAuthorId")
    public String booksByAuthorId(@PathVariable String authorId, Model model){
        List<Book> books = bookService.findByAuthorId(authorId);
        model.addAttribute("books", books);
        return "book/booksByAuthorId";
    }

    /**
     * 빌린책 목록 조회
     * @param bookRentalId
     * @param model
     * @return
     */
    @GetMapping("/{bookRentalId}/booksByBookRentalId")
    public String booksByBookRentalId(@PathVariable String bookRentalId, Model model){
        List<BookRental> rentalBooks = bookService.findByBookRentalId(bookRentalId);
        List<BookStateCode> bookStateCodes = bookService.allRentalStateCodeList();
        model.addAttribute("bookStateCodes", bookStateCodes);
        model.addAttribute("rentalBooks", rentalBooks);
        return "book/booksByBookRentalId";
    }

    /**
     * 책 대여
     * @param bookId
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/{bookId}/rental")
    @Transactional
    public  String rentalBook (@PathVariable String bookId, Model model, HttpServletRequest request
    ,RedirectAttributes redirectAttributes){
        String loginId = (String) request.getSession().getAttribute("login");
        BookRental bookRental = new BookRental();
        bookRental.setBookId(bookId);
        bookRental.setBookRentalId(loginId);
        bookService.insertRental(bookRental);

        Book book = new Book();
        book.setBookId(bookId);
        book.setBookStateCode("ABLE");
        bookService.updateBookState(book);
        redirectAttributes.addAttribute("status", "대여완료");
        return "redirect:/book/{bookId}";
    }
}