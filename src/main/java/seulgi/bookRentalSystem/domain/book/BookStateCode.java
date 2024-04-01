package seulgi.bookRentalSystem.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookStateCode {

    private String bookStateCode;
    private String bookStateCodeName;
}
