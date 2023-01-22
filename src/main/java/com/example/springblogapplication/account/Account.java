package com.example.springblogapplication.account;

import com.example.springblogapplication.post.Post;
import com.sun.source.doctree.SeeTree;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.awt.Point;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String email;

  private String password;

  private String firstName;

  private String lastName;

  @OneToMany(mappedBy = "account")
  private List<Post> posts;
}
