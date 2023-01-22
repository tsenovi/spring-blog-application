package com.example.springblogapplication.home;

import com.example.springblogapplication.post.Post;
import com.example.springblogapplication.post.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  private PostService postService;

  @GetMapping("/")
  public String home(Model model) {
    List<Post> posts = postService.getAll();
    model.addAttribute("posts", posts);
    return "home";
  }
}
