package seulgi.bookRentalSystem.domain.book;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class Book {

    private String bookId;
    private String bookName;
    private String bookWriter;
    private String authorId;
    private String authorName;
    private String bookRentalId;
    private String bookStateCode;
    private String bookStateCodeName;
    private String createDate;

}
