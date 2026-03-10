package com.instapro.com.demo.controller;

import com.instapro.com.demo.entity.Post;
import com.instapro.com.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private PostService postService;

    @GetMapping("/pending-posts")
    public List<Post> getPendingPosts() {
        return postService.pendingPosts();
    }

   
    @PutMapping("/approve/{id}")
    public void approvePost(@PathVariable Long id) {
        postService.approve(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectPost(@PathVariable Long id) {
        postService.reject(id);
    }
}
