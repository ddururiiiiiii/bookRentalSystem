package seulgi.bookRentalSystem.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter @Getter
@RequiredArgsConstructor
public class UpdateForm {

    private String memberName;
    private String password;
}
