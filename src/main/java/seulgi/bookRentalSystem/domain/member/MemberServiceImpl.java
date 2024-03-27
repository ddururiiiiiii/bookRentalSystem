package seulgi.bookRentalSystem.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    @Override
    public List<Member> allMemberList() {
        return memberMapper.allMemberList();
    }
}
