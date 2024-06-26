package seulgi.bookRentalSystem.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
@RequiredArgsConstructor
public class Member {

    private String memberId;
    private String memberName;
    private String password;
    private String joinDate;
}
