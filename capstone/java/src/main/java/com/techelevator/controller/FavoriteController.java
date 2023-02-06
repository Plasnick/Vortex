package com.techelevator.controller;

import com.techelevator.dao.FavoriteDao;
import com.techelevator.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FavoriteController {

    @Autowired
    FavoriteDao favoriteDao;

    @GetMapping("/favorites/{id}")
    public List<Favorite> getFavoritesByUser(@PathVariable int id){
        return favoriteDao.getFavoritesByUserId(id);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/favorites", method = RequestMethod.POST)
    public void createFavorite(@RequestBody Favorite favorite){
        favoriteDao.createFavorite(favorite);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/favorites/{id}",method = RequestMethod.DELETE)
    public void deleteFavorite(@PathVariable int id){favoriteDao.deleteFavorite(id);}

}
