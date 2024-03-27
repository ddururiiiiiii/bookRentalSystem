package seulgi.bookRentalSystem.domain.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

//    @Select("SELECT MEMBER_ID AS memberId" +
//            ", MEMBER_NAME AS memberName" +
//            ", PASSWORD" +
//            "FROM MEMBER_TB")
//    List<Member> allMemberList();

    List<Member> allMemberList();
}
