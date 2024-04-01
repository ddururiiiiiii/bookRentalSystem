package seulgi.bookRentalSystem.domain.home;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import seulgi.bookRentalSystem.domain.book.Book;
import seulgi.bookRentalSystem.domain.book.BookServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookServiceImpl bookService;
    @GetMapping("/")
    public String allBookList(Model model, HttpServletRequest request){
        String loginId = (String) request.getSession().getAttribute("loginId");
        List<Book> books = bookService.allBookList();
        model.addAttribute("loginId", loginId);
        model.addAttribute("books", books);
        return "book/allBookList";
    }

}
