package seulgi.bookRentalSystem.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class Member {

    private String memberId;
    private String memberName;
    private String password;


}
