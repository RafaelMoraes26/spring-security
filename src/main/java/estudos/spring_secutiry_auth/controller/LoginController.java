package estudos.spring_secutiry_auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String BIO= "bio";
    private static final String AVATAR_URL = "avatar_url";

    @GetMapping("/auth/login")
    public String getLoginPage() {
        return LOGIN;  // Retorna o template `login.html`.
    }

    @GetMapping("/home")
    public String getLoginInfo(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            String username = principal.getAttribute(NAME);
            String login = principal.getAttribute(LOGIN);
            String bio = principal.getAttribute(BIO);
            String avatarUrl = principal.getAttribute(AVATAR_URL);

            model.addAttribute("username", username);
            model.addAttribute(LOGIN, login);
            model.addAttribute(BIO, bio);
            model.addAttribute(AVATAR_URL, avatarUrl);

            return "home";  // Retorna o template `home.html`.
        } else {
            return "redirect:/auth/login";
        }
    }
}

