package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> getPostsByForum(int forumId) {
        List<Post> postsByForum = new ArrayList<>();
        String sql = "SELECT post_id, user_id, forum_id, title, body, img_url, posted_at, score " +
                     "FROM post WHERE forum_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
        while (results.next()){
            postsByForum.add(mapRowToPost(results));
        }
        return null;
    }

    private Post mapRowToPost(SqlRowSet rowSet){
        Post post = new Post();
        post.setPostId(rowSet.getInt("post_id"));
        post.setUserId(rowSet.getInt("user_id"));
        post.setForumId(rowSet.getInt("forum_id"));
        post.setTitle(rowSet.getString("title"));
        post.setBody(rowSet.getString("body"));
        post.setImg_url(rowSet.getString("img_url"));
        post.setScore(rowSet.getInt("score"));
        post.setPostedAt(rowSet.getTimestamp("posted_at").toLocalDateTime());
        return post;
    }
}
