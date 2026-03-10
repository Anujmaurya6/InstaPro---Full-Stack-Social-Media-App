package com.instapro.com.demo.repository;

import com.instapro.com.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserUsername(String username);

    List<Post> findByStatus(String status);

    List<Post> findByStatusAndUserUsernameNot(String status, String username);
}
