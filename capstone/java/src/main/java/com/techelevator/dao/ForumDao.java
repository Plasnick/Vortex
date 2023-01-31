package com.techelevator.dao;

import com.techelevator.model.Forum;

import java.util.List;

public interface ForumDao {




    List<Forum> findTopFive();

    List<Forum> findForumByKeyword(String keyword);

    List<Forum> findAllForums();
//
//    boolean create(String forumName, String description, String rules);


}
