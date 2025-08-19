package com.booleanuk.api.game.controller;

import com.booleanuk.api.game.model.Game;
import com.booleanuk.api.game.repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameRepository repo;
    
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        return new ResponseEntity<>(this.repo.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") int id) {
        Game Game = this.repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game was not found"));
        return new ResponseEntity<>(Game, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return new ResponseEntity<>(this.repo.save(game), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game gameToBeUpdated = this.repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Game"));
        gameToBeUpdated.setTitle(game.getTitle());
        gameToBeUpdated.setGenre(game.getGenre());
        gameToBeUpdated.setDeveloper(game.getDeveloper());
        gameToBeUpdated.setPublisher(game.getPublisher());
        gameToBeUpdated.setReleaseYear(game.getReleaseYear());
        gameToBeUpdated.setEarlyAccess(game.isEarlyAccess());
        return new ResponseEntity<>(this.repo.save(gameToBeUpdated), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Game gameToDelete = this.repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.repo.delete(gameToDelete);
        return ResponseEntity.ok(gameToDelete);
    }
}
