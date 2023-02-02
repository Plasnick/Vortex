package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getPostsByForum(int forumId);
    List<Post> getTop10Posts();
    Post getPostById(int id);
    void createPost(Post post);
    void updatePost(int id, Post post);
}
