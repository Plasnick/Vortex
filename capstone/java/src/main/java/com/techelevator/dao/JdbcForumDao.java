package com.techelevator.dao;

import com.techelevator.model.Forum;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcForumDao implements ForumDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Forum> findTopFive() {

        String sql = "SELECT forum_id, forum.name, forum.description, forum.rules FROM forum\n" +
                "JOIN post ON post.post_id = forum.post_id\n" +
                "ORDER BY post.posted_at\n" +
                "GROUP BY forum_id\n" +
                "LIMIT 5;";
        List<Forum> forumList = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()){
            Forum forum = mapRowToForum(results);
            int forumId = forum.getId();
            String sqlId = "SELECT moderator_id FROM moderator WHERE forum_id=?";
            SqlRowSet resultId = jdbcTemplate.queryForRowSet(sqlId, forumId);
            List<Integer> moderatorList = new ArrayList<>();
            while (resultId.next()){
                moderatorList.add(mapRowToModeratorList(resultId));
            }
            forum.setModeratorList(moderatorList);
            forumList.add(forum);
        }
        return forumList;
    }




    private Forum mapRowToForum(SqlRowSet result){
        Forum forum = new Forum();
        forum.setId(result.getInt("forum_id"));
        forum.setForumName(result.getString("name"));
        forum.setDescription(result.getString("description"));
        forum.setRules(result.getString("rules"));

        return forum;
    }

    private int mapRowToModeratorList(SqlRowSet result){
        int moderatorId = result.getInt("moderator_id");
        return moderatorId;
    }



}
