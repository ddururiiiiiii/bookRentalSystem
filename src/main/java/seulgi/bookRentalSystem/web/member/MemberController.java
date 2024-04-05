package seulgi.bookRentalSystem.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seulgi.bookRentalSystem.domain.member.Member;
import seulgi.bookRentalSystem.domain.member.MemberServiceImpl;
import seulgi.bookRentalSystem.domain.member.UpdateForm;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    /**
     * 회원가입 폼
     * @param model
     * @return
     */
    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("member", new Member());
        return "member/joinForm";
    }

    /**
     * 회원가입
     * @param member
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/join")
    public String join(@ModelAttribute Member member, RedirectAttributes redirectAttributes){
       memberService.join(member);
       redirectAttributes.addAttribute("memberId", member.getMemberId());
       redirectAttributes.addAttribute("status", true);
       return "redirect:/member/{memberId}";
    }

    /**
     * 회원 단건 조회
     * @param memberId
     * @param model
     * @return
     */
    @GetMapping("/{memberId}")
    public String member(@PathVariable String memberId, Model model){
        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);
        return "member/member";
    }

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

    /**
     * 회원 정보 수정 폼
     * @param memberId
     * @param model
     * @return
     */
    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable String memberId, Model model){
        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);
        return "member/editForm";
    }
    /**
     * 회원 정보 수정
     * @param memberId
     * @param form
     * @return
     */
    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable String memberId
            , @ModelAttribute("member") UpdateForm form) {
        memberService.update(memberId, form);
        return "redirect:/member/{memberId}";
    }
}
