package hu.nye.progtech.torpedo.persistence;

import hu.nye.progtech.torpedo.model.Players;

import java.util.List;

public interface GameResultRepository {

    void insertOrUpdate(Players players);

    List<Players> load();
}
