package seulgi.bookRentalSystem.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookRental {

    private String bookId;
    private String bookName;
    private String bookRentalId;
    private String bookRentalName;
    private String bookStateCode;
    private String bookStateCodeName;
    private String rentalDate;
    private String returnDate;

}
