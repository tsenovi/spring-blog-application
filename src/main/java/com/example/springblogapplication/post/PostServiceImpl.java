package com.example.springblogapplication.post;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  @Override
  public Optional<Post> getById(Long id) {
    return postRepository.findById(id);
  }

  @Override
  public List<Post> getAll() {
    return postRepository.findAll();
  }

  @Override
  public Post save(Post post) {
    if (post.getId() == null) {
      post.setCreatedAt(LocalDateTime.now());
    }
    post.setUpdatedAt(LocalDateTime.now());
    return postRepository.save(post);
  }

  @Override
  public void delete(Post post) {
    postRepository.delete(post);
  }

  @Override
  public void update(Principal principal, Post existingPost, Post post) {
    existingPost.setUpdatedBy(principal.getName());
    existingPost.setTitle(post.getTitle());
    existingPost.setBody(post.getBody());
    save(existingPost);
  }
}
