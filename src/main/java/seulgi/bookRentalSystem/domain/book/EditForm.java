package seulgi.bookRentalSystem.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EditForm {

    private String bookName;
    private String bookWriter;
    private String isbn;
    private String publisher;
    private String thumbnailImg;
    private String bookStateCode;
}
