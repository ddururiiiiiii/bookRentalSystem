package seulgi.bookRentalSystem.web.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seulgi.bookRentalSystem.domain.login.LoginForm;
import seulgi.bookRentalSystem.domain.login.LoginService;
import seulgi.bookRentalSystem.domain.member.Member;
import seulgi.bookRentalSystem.domain.member.MemberServiceImpl;
import seulgi.bookRentalSystem.domain.member.UpdateForm;
import seulgi.bookRentalSystem.web.login.SessionConst;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;
    private final LoginService loginService;

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
    public String allMemberList(Model model
    , @RequestParam(defaultValue = "1") int page
    , @RequestParam(defaultValue = "10") int size){
        List<Member> members = memberService.allMemberList(page, size);
        int totalMembers = memberService.countMembers();
        int totalPages = (int) Math.ceil((double) totalMembers / size);
        model.addAttribute("members", members);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
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

    /**
     * 로그인 체크 폼
     * @return
     */
    @GetMapping("/{memberId}/checkPassword")
    public String checkPasswordForm(@ModelAttribute("loginForm")LoginForm loginForm) {
        return "member/checkPassword";
    }

    /**
     * 로그인 체크
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/{memberId}/checkPassword")
    public String checkPassword(@Valid @ModelAttribute LoginForm form
            , BindingResult bindingResult
            , Model model) {

        if (bindingResult.hasErrors()) {
            return "member/checkPassword";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "member/checkPassword";
        }
        Member member = memberService.findById(form.getLoginId());
        model.addAttribute("member", member);
        return "member/editForm";
    }
}
