package com.techelevator.dao;

import com.techelevator.model.Forum;

import java.util.List;

public interface ForumDao {




    List<Forum> findTopFive();



    List<Forum> findAllForums();

    Forum findForumById(int id);


    //List<Forum> findForumByKeyword(String keyword);
}
