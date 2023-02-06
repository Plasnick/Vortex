package com.techelevator.dao;

import com.techelevator.model.Moderator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcModeratorDao implements ModeratorDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcModeratorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Moderator> getModeratorsByForumId(int id) {
        List<Moderator> moderators = new ArrayList<>();
        String sql = "SELECT moderator_id, forum_id, username FROM moderator " +
                "JOIN users ON moderator_id = user_id WHERE forum_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()){
            moderators.add(mapRowToModerator(results));
        }
        return moderators;
    }

    @Override
    public List<Moderator> getForumsByModeratorId(int id) {
        List<Moderator> moderators = new ArrayList<>();
        String sql = "SELECT moderator_id, forum_id, username FROM moderator " +
                "JOIN users ON moderator_id = user_id WHERE moderator_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            moderators.add(mapRowToModerator(results));
        }
        return moderators;
    }

    @Override
    public void addModerator(Moderator moderator) {
        String sql = "INSERT INTO moderator (moderator_id, forum_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, moderator.getModeratorId(), moderator.getForumId());
    }

    private Moderator mapRowToModerator(SqlRowSet rowSet){
        Moderator moderator = new Moderator();
        moderator.setModeratorId(rowSet.getInt("moderator_id"));
        moderator.setForumId(rowSet.getInt("forum_id"));
        moderator.setUsername(rowSet.getString("username"));
        return moderator;
    }
}
