package seulgi.bookRentalSystem.domain.member;

import org.apache.ibatis.annotations.Param;
import seulgi.bookRentalSystem.domain.book.Book;
import seulgi.bookRentalSystem.domain.book.BookRental;

import java.util.List;

public interface MemberService {

    List<Member> allMemberList(int page, int size);

    int countMembers();

    void join(Member member);

    Member findById(String id);

    void update(String memberId, UpdateForm updateForm);

    String idCheck(String memberId);

}
