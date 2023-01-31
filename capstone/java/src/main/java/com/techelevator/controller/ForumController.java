package com.techelevator.controller;


import com.techelevator.dao.ForumDao;
import com.techelevator.model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ForumController {

    @Autowired
    ForumDao forumDao;

    @GetMapping("/forum/top5")
    public List<Forum> findTopFive(){ return forumDao.findTopFive();}

    @GetMapping("/forum")
    public List<Forum> findAllForums() {return forumDao.findAllForums();}



}
