package seulgi.bookRentalSystem.domain.login;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seulgi.bookRentalSystem.domain.member.Member;
import seulgi.bookRentalSystem.domain.member.MemberMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberMapper memberMapper;

    /**
     * 로그인 (null 이면 로그인 실패)
     *
     * @param loginId
     * @param password
     * @return
     */
    public Member login (String loginId, String password){
        return memberMapper.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
