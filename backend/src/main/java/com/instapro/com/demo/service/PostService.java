package com.instapro.com.demo.service;

import com.instapro.com.demo.entity.Post;
import java.util.List;

public interface PostService {

    void createPost(String username, String content);

    List<Post> myPosts(String username);

    List<Post> approvedPosts();

    List<Post> pendingPosts();

    List<Post> otherUsersApprovedPosts(String username);

    void approve(Long id);

    void reject(Long id);
}

