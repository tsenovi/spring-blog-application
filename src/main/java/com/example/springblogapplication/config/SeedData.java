package com.example.springblogapplication.config;

import com.example.springblogapplication.account.Account;
import com.example.springblogapplication.account.AccountService;
import com.example.springblogapplication.post.Post;
import com.example.springblogapplication.post.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeedData implements CommandLineRunner {

  private final PostService postService;

  private final AccountService accountService;


  @Override
  public void run(String... args) throws Exception {
    List<Post> posts = postService.getAll();

    if (posts.size() == 0){
      Account account1 = new Account();
      Account account2 = new Account();

      account1.setFirstName("admin");
      account1.setLastName("admin");
      account1.setEmail("admin.admin@gmail.com");
      account1.setPassword("admin");

      account2.setFirstName("ivan");
      account2.setLastName("tsenov");
      account2.setEmail("ivan.tsenov@gmail.com");
      account2.setPassword("password");

      accountService.save(account1);
      accountService.save(account2);

      Post post1 = new Post();
      post1.setTitle("Title of Post 1");
      post1.setBody("This is the body of Post 1");
      post1.setAccount(account1);

      Post post2 = new Post();
      post2.setTitle("Title of Post 2");
      post2.setBody("This is the body of Post 2");
      post2.setAccount(account2);

      postService.save(post1);
      postService.save(post2);
    }
  }
}
