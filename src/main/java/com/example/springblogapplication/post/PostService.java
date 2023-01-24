package com.example.springblogapplication.post;

import java.util.List;
import java.util.Optional;

public interface PostService {

  Optional<Post> getById(Long id);

  List<Post> getAll();

  Post save(Post post);
}
