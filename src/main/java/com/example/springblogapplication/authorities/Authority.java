package com.example.springblogapplication.authorities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority implements Serializable {

  @Id
  @Column(length = 16)
  private String name;

  @Override
  public String toString() {
    return "Authority{" +
        "name='" + name + '\'' +
        '}';
  }
}
