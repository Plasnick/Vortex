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
        String sql = "SELECT post_id, user_id, forum_id, title, body, img_url, posted_at, up_votes, down_votes " +
                     "FROM post WHERE forum_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
        while (results.next()){
            postsByForum.add(mapRowToPost(results));
        }
        return postsByForum;
    }

    @Override
    public List<Post> getTop10Posts() {
        List<Post> top10 = new ArrayList<>();
        String sql = "SELECT post_id, user_id, forum_id, title, body, img_url, posted_at, up_votes, down_votes, up_votes - down_votes AS score  " +
                "FROM post WHERE posted_at >= NOW() - INTERVAL '24 HOURS' " +
                "ORDER BY score DESC " +
                "LIMIT 10;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            top10.add(mapRowToPost(results));
        }

        return top10;
    }

    @Override
    public Post getPostById(int id) {
        Post post = null;
        String sql = "SELECT post_id, user_id, forum_id, title, body, img_url, posted_at, up_votes, down_votes FROM post " +
                    "WHERE post_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            post = mapRowToPost(result);
        }
        return post;
    }

    @Override
    public void createPost(Post post) {
        String sql = "INSERT INTO post (user_id, forum_id, title, body, img_url) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getUserId(), post.getForumId(), post.getTitle(), post.getBody(), post.getImg_url());
    }

    private Post mapRowToPost(SqlRowSet rowSet){
        Post post = new Post();
        post.setPostId(rowSet.getInt("post_id"));
        post.setUserId(rowSet.getInt("user_id"));
        post.setForumId(rowSet.getInt("forum_id"));
        post.setTitle(rowSet.getString("title"));
        post.setBody(rowSet.getString("body"));
        post.setImg_url(rowSet.getString("img_url"));
        post.setUpVotes(rowSet.getInt("up_votes"));
        post.setDownVotes(rowSet.getInt("down_votes"));
        post.setPostedAt(rowSet.getTimestamp("posted_at").toLocalDateTime());
        return post;
    }
}
