package seulgi.bookRentalSystem.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    /**
     * 회원 목록 조회
     * @param model
     * @return
     */
    @GetMapping
    public String allMemberList(Model model){
        List<Member> members = memberService.allMemberList();
        model.addAttribute("members", members);
        return "member/allMemberList";
    }
}
