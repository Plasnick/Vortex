package com.techelevator.dao;

import com.techelevator.model.Interaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcInteractionDao implements InteractionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcInteractionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Interaction> getInteractionsByUserId(int id) {
        List<Interaction> interactions = new ArrayList<>();
        String sql = "SELECT user_id, post_id FROM interaction WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()){
            interactions.add(mapRowToInteraction(results));
        }
        return interactions;
    }

    @Override
    public void createInteraction(Interaction interaction) {
        String sql = "INSERT INTO interaction (user_id, post_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, interaction.getUserId(), interaction.getPostId());
    }

    private Interaction mapRowToInteraction(SqlRowSet rowSet){
        Interaction interaction = new Interaction();
        interaction.setUserId(rowSet.getInt("user_id"));
        interaction.setPostId(rowSet.getInt("post_id"));
        return interaction;
    }
}
