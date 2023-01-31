package com.techelevator.dao;

import com.techelevator.model.Forum;

import java.util.List;

public interface ForumDao {




    List<Forum> findTopFive();
//
//    Forum findByForumName(String forumName);
//
//    Forum findIdByForumName(int ForumId);
//
//    boolean create(String forumName, String description, String rules);


}
