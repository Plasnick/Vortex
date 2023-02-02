package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void createPost(@RequestBody Post post){
        postDao.createPost(post);
    }



}
