package com.example.springblogapplication.register;

import com.example.springblogapplication.account.Account;
import com.example.springblogapplication.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

  private final AccountService accountService;

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("account", new Account());
    return "register";
  }

  @PostMapping("/register")
  public String registerNewAccount(@ModelAttribute Account account) {
    accountService.save(account);
    return "redirect:/";
  }
}
