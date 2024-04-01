package seulgi.bookRentalSystem.domain.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    List<Member> allMemberList();

    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    void join(@Param("member") Member member);

    Member findById(@Param("memberId") String memberId);

    void update(@Param("memberId") String memberId, @Param("member") Member member);

}
