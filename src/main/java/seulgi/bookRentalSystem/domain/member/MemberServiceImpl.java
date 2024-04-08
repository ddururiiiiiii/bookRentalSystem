package seulgi.bookRentalSystem.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seulgi.bookRentalSystem.domain.book.Book;
import seulgi.bookRentalSystem.domain.book.BookRental;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public List<Member> allMemberList(int page, int size) {
        int offset = (page - 1) * size;
        return memberMapper.allMemberList(offset, size);
    }

    @Override
    public int countMembers() {
        return memberMapper.countMembers();
    }

    @Override
    public void join(Member member) {
        memberMapper.join(member);
    }

    @Override
    public Member findById(String id) {
        return memberMapper.findById(id);
    }

    @Override
    public void update(String memberId, UpdateForm updateParam) {
        Member findMember = findById(memberId);
        findMember.setMemberName(updateParam.getMemberName());
        findMember.setPassword(updateParam.getPassword());
        memberMapper.update(memberId, findMember);
    }

    @Override
    public String idCheck(String memberId) {
        return memberMapper.idCheck(memberId);
    }


}
