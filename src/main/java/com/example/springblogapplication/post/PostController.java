package com.example.springblogapplication.post;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

  private final PostService postService;

  @GetMapping("/posts/{id}")
  public String getPost(@PathVariable Long id, Model model) {
    Optional<Post> optionalPost = postService.getById(id);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      model.addAttribute("post", post);
      return "post";
    } else {
      return "404";
    }
  }
}
