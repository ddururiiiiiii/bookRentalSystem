package seulgi.bookRentalSystem.web.login;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import seulgi.bookRentalSystem.domain.login.LoginForm;
import seulgi.bookRentalSystem.domain.login.LoginService;
import seulgi.bookRentalSystem.domain.member.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form
            , BindingResult bindingResult
            , @RequestParam(defaultValue = "/") String redirectURL
            , HttpServletRequest request){


        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        String memberName = loginMember.getMemberName();
        if (loginMember== null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        session.setAttribute("loginId", form.getLoginId());
        session.setAttribute("loginName", memberName);
        return "redirect:/book";
    }

    @GetMapping("/findLoginInfo")
    @ResponseBody
    public Map<String, String> findLoginInfo(HttpServletRequest request, Model model){
        String loginId = (String) request.getSession().getAttribute("loginId");
        String loginName = (String) request.getSession().getAttribute("loginName");
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("loginId", loginId);
        loginInfo.put("loginName", loginName);
        model.addAttribute("loginInfo", loginInfo);
        return loginInfo;
    }
}
