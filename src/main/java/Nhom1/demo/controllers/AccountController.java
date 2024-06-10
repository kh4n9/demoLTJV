package Nhom1.demo.controllers;

import Nhom1.demo.entities.Account;
import Nhom1.demo.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    // khong hash
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account/register";
        }

        accountService.save(account);
        return "redirect:/account/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "account/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("account") Account account, Model model) {
        boolean isAuthenticated = accountService.authenticate(account.getUsername(), account.getPassword());
        if (!isAuthenticated) {
            model.addAttribute("loginError", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "account/login";
        }

        return "account/logged";
    }

    @GetMapping("/logged")
    public String logged(Model model) {
        model.addAttribute("account", new Account());
        return "account/logged";
    }
    // md5 + salt
    @GetMapping("/registerMd5AndSalt")
    public String registerMd5AndSalt(Model model) {
        model.addAttribute("account", new Account());
        return "account/registerMd5AndSalt";
    }

    @PostMapping("/registerMd5AndSalt")
    public String registerMd5AndSalt(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account/registerMd5AndSalt";
        }

        accountService.save(account);
        return "redirect:/account/loginMd5AndSalt";
    }

    @GetMapping("/loginMd5AndSalt")
    public String loginMd5AndSalt(Model model) {
        model.addAttribute("account", new Account());
        return "account/loginMd5AndSalt";
    }

    @PostMapping("/loginMd5AndSalt")
    public String loginMd5AndSalt(@ModelAttribute("account") Account account, Model model) {
        boolean isAuthenticated = accountService.authenticate(account.getUsername(), account.getPassword());
        if (!isAuthenticated) {
            model.addAttribute("loginError", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "account/loginMd5AndSalt";
        }

        return "account/loggedMd5AndSalt";
    }

    @GetMapping("/loggedMd5AndSalt")
    public String loggedMd5AndSalt(Model model) {
        model.addAttribute("account", new Account());
        return "account/loggedMd5AndSalt";
    }

    // md5 + sha265
    @GetMapping("/registerMd5AndSha256")
    public String registerMd5AndSha256(Model model) {
        model.addAttribute("account", new Account());
        return "account/registerMd5AndSha256";
    }

    @PostMapping("/registerMd5AndSha256")
    public String registerMd5AndSha256(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account/registerMd5AndSha256";
        }

        accountService.save(account);
        return "redirect:/account/loginMd5AndSha256";
    }

    @GetMapping("/loginMd5AndSha256")
    public String loginMd5AndSha256(Model model) {
        model.addAttribute("account", new Account());
        return "account/loginMd5AndSha256";
    }

    @PostMapping("/loginMd5AndSha256")
    public String loginMd5AndSha256(@ModelAttribute("account") Account account, Model model) {
        boolean isAuthenticated = accountService.authenticate(account.getUsername(), account.getPassword());
        if (!isAuthenticated) {
            model.addAttribute("loginError", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "account/loginMd5AndSha256";
        }

        return "account/loggedMd5AndSha256";
    }

    @GetMapping("/loggedMd5AndSha256")
    public String loggedMd5AndSha256(Model model) {
        model.addAttribute("account", new Account());
        return "account/loggedMd5AndSha256";
    }
}
