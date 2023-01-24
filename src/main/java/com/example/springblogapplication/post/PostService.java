package com.example.springblogapplication.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {

  private final PostRepository postRepository;

  public Optional<Post> getById(Long id) {
    return postRepository.findById(id);
  }

  public List<Post> getAll() {
    return postRepository.findAll();
  }

  public Post save(Post post) {
    if (post.getId() == null) {
      post.setCreatedAt(LocalDateTime.now());
    }

    return postRepository.save(post);
  }
}
