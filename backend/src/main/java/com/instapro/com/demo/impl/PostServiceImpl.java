package com.instapro.com.demo.impl;

import com.instapro.com.demo.entity.Post;
import com.instapro.com.demo.entity.User;
import com.instapro.com.demo.repository.PostRepository;
import com.instapro.com.demo.repository.UserRepository;
import com.instapro.com.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void createPost(String username, String content) {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        post.setStatus("PENDING");
        post.setCreatedAt(LocalDateTime.now());

        postRepo.save(post);
    }

    @Override
    public List<Post> myPosts(String username) {
        return postRepo.findByUserUsername(username);
    }

    @Override
    public List<Post> approvedPosts() {
        return postRepo.findByStatus("APPROVED");
    }

    @Override
    public List<Post> pendingPosts() {
        return postRepo.findByStatus("PENDING");
    }

    // 🔥 MAIN FIX
    @Override
    public List<Post> otherUsersApprovedPosts(String username) {
        return postRepo.findByStatusAndUserUsernameNot("APPROVED", username);
    }

    @Override
    public void approve(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setStatus("APPROVED");
        postRepo.save(post);
    }

    @Override
    public void reject(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setStatus("REJECTED");
        postRepo.save(post);
    }
}
