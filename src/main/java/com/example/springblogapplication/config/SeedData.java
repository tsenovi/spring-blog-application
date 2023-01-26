package com.example.springblogapplication.config;

import com.example.springblogapplication.account.Account;
import com.example.springblogapplication.account.AccountService;
import com.example.springblogapplication.authorities.Authority;
import com.example.springblogapplication.authorities.AuthorityRepository;
import com.example.springblogapplication.post.Post;
import com.example.springblogapplication.post.PostService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeedData implements CommandLineRunner {

  private final PostService postService;

  private final AccountService accountService;

  private final AuthorityRepository authorityRepository;


  @Override
  public void run(String... args) throws Exception {
    List<Post> posts = postService.getAll();

    if (posts.size() == 0) {

      Authority user = new Authority();
      user.setName("ROLE_USER");
      authorityRepository.save(user);

      Authority admin = new Authority();
      admin.setName("ROLE_ADMIN");
      authorityRepository.save(admin);

      Account account1 = new Account();
      Account account2 = new Account();

      account1.setFirstName("admin");
      account1.setLastName("admin");
      account1.setEmail("admin.admin@gmail.com");
      account1.setPassword("admin");
      Set<Authority> authorities1 = new HashSet<>();
      authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities1::add);
      authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
      account1.setAuthorities(authorities1);

      account2.setFirstName("ivan");
      account2.setLastName("tsenov");
      account2.setEmail("ivan.tsenov@gmail.com");
      account2.setPassword("password");
      Set<Authority> authorities2 = new HashSet<>();
      authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
      account2.setAuthorities(authorities2);

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
