package com.instapro.com.demo.controller;

import com.instapro.com.demo.entity.Post;
import com.instapro.com.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    // USER
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public void createPost(@RequestBody Post post, Principal principal) {
        postService.createPost(principal.getName(), post.getContent());
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public List<Post> myPosts(Principal principal) {
        return postService.myPosts(principal.getName());
    }

    @GetMapping("/others")
    @PreAuthorize("hasRole('USER')")
    public List<Post> otherUsersPosts(Principal principal) {
        return postService.otherUsersApprovedPosts(principal.getName());
    }

    // ADMIN
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Post> pendingPosts() {
        return postService.pendingPosts();
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void approve(@PathVariable Long id) {
        postService.approve(id);
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void reject(@PathVariable Long id) {
        postService.reject(id);
    }
}

