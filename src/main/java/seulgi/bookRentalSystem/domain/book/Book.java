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
    private String isbn;
    private String publisher;
    private String thumbnailImg;
    private String bookRentalId;
    private String bookStateCode;
    private String bookStateCodeName;
    private String createDate;

    public Book(String bookId, String bookName, String bookWriter
            , String authorId, String isbn, String publisher
            , String thumbnailImg, String bookStateCode,  String createDate) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.authorId = authorId;
        this.isbn = isbn;
        this.publisher = publisher;
        this.thumbnailImg = thumbnailImg;
        this.bookStateCode = bookStateCode;
        this.createDate = createDate;
    }
}
