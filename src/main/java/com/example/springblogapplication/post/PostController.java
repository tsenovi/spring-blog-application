package com.example.springblogapplication.post;

import com.example.springblogapplication.account.Account;
import com.example.springblogapplication.account.AccountService;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

  private final PostService postService;

  private final AccountService accountService;

  @GetMapping("/posts/{id}")
  public String getPost(@PathVariable Long id, Model model) {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isPresent()) {
      model.addAttribute("post", optionalPost.get());
    } else {
      return "404";
    }

    return "post";
  }

  @GetMapping("/posts/new")
  public String createNewPost(Principal principal, Model model) {
    Optional<Account> optionalAccount = accountService.findByEmail(principal.getName());
    if (optionalAccount.isPresent()){
      Post post = new Post();
      post.setAccount(optionalAccount.get());
      model.addAttribute("post", post);
    } else {
      return "404";
    }

    return "post_new";
  }

  @PostMapping("/posts/new")
  public String saveNewPost(@ModelAttribute Post post) {
    postService.save(post);
    return "redirect:/posts/" + post.getId();
  }
}
