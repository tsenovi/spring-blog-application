package com.example.springblogapplication.post;

import com.example.springblogapplication.account.Account;
import com.example.springblogapplication.account.AccountService;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
      return "post";
    }

    return "404";
  }

  @GetMapping("/posts/new")
  public String createNewPost(Principal principal, Model model) {
    Optional<Account> optionalAccount = accountService.findByEmail(principal.getName());
    if (optionalAccount.isPresent()) {
      Post post = new Post();
      post.setAccount(optionalAccount.get());
      model.addAttribute("post", post);
      return "post_new";
    }

    return "404";
  }

  @PostMapping("/posts/new")
  public String saveNewPost(@ModelAttribute Post post) {
    postService.save(post);

    return "redirect:/posts/" + post.getId();
  }

  @GetMapping("/posts/{id}/edit")
  @PreAuthorize("isAuthenticated()")
  public String getPostForEdit(@PathVariable Long id, Model model) {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isPresent()) {
      model.addAttribute("post", optionalPost.get());
      return "post_edit";
    }

    return "404";
  }

  @PostMapping("/posts/{id}")
  @PreAuthorize("isAuthenticated()")
  public String updatePost(@PathVariable Long id, Post post, Principal principal) {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isPresent()) {
      Post existingPost = optionalPost.get();
      existingPost.setUpdatedBy(principal.getName());
      existingPost.setTitle(post.getTitle());
      existingPost.setBody(post.getBody());
      postService.save(existingPost);
      return "redirect:/posts/" + post.getId();
    }

    return "404";
  }

  @GetMapping("/posts/{id}/delete")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String deletePost(@PathVariable Long id) {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isPresent()) {
      postService.delete(optionalPost.get());
      return "redirect:/";
    }

    return "404";
  }
}
