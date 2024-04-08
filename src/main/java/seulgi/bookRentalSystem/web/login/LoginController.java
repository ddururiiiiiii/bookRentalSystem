package seulgi.bookRentalSystem.web.login;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import seulgi.bookRentalSystem.domain.login.LoginForm;
import seulgi.bookRentalSystem.domain.login.LoginService;
import seulgi.bookRentalSystem.domain.member.Member;
import seulgi.bookRentalSystem.domain.member.MemberService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final MemberService memberService;

    /**
     * 로그인 화면 호출
     * @param loginForm
     * @return
     */
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm){
        return "login/loginForm";
    }

    /**
     * 로그인
     * @param form
     * @param bindingResult
     * @param redirectURL
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form
            , BindingResult bindingResult
            , @RequestParam(defaultValue = "/") String redirectURL
            , HttpServletRequest request){


        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        String memberName = loginMember.getMemberName();
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        session.setAttribute("loginId", form.getLoginId());
        session.setAttribute("loginName", memberName);
        return "redirect:" + redirectURL;
    }

    /**
     * 로그인 정보 조회
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/findLoginInfo")
    @ResponseBody
    public Map<String, String> findLoginInfo(HttpServletRequest request, Model model){
        String loginId = (String) request.getSession().getAttribute("loginId");
        Member member = memberService.findById(loginId);
        String loginName;
        if (member == null){
            loginName = null;
        } else {
            loginName = member.getMemberName();
        }
        request.setAttribute("loginName", loginName);

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("loginId", loginId);
        loginInfo.put("loginName", loginName);
        model.addAttribute("loginInfo", loginInfo);
        return loginInfo;
    }

    /**
     * 로그아웃
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/book";
    }

    @PostMapping("/{memberId}/idCheck")
    public ResponseEntity<Boolean> idCheck(@PathVariable String memberId){
       String idCheck = memberService.idCheck(memberId);
        if (idCheck == null){
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}
