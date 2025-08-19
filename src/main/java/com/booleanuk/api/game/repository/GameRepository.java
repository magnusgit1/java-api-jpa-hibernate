package com.booleanuk.api.game.repository;

import com.booleanuk.api.game.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
