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
}
