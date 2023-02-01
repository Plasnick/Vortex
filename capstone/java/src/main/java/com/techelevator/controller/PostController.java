package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostDao postDao;

    @GetMapping("/forum/{forumId}/posts")
    public List<Post> getPostsByForumId(@PathVariable int forumId){
        return postDao.getPostsByForum(forumId);
    }

    @GetMapping("/posts/top10")
    public List<Post> getTop10Posts(){
        return postDao.getTop10Posts();
    }

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable int postId){
        return postDao.getPostById(postId);
    }



}
