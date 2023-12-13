package com.example.testcontainers.testcontainersdemo.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

  @Autowired
  JdbcConnectionDetails jdbcConnectionDetails;

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

  @Autowired
  PostRepository postRepository;


  @BeforeEach
  void setUp() {
    List<Post> posts = List.of(new Post(1,1,"Hello, World!", "This is my first post!",null));
    postRepository.saveAll(posts);
  }

  @Test
  void connectionEstablished() {
    assertThat(postgres.isCreated()).isTrue();
    assertThat(postgres.isRunning()).isTrue();
  }

  @Test
  void shouldReturnPostByTitle() {
    Post post = postRepository.findByTitle("Hello, World!").orElseThrow();
    log.info("### post : " + post);
    assertEquals("Hello, World!", post.title(), "Post title should be 'Hello, World!'");
  }

  @Test
  void shouldNotReturnPostWhenTitleIsNotFound() {
    Optional<Post> post = postRepository.findByTitle("Hello, Wrong Title!");
    assertFalse(post.isPresent(), "Post should not be present");
  }
}
