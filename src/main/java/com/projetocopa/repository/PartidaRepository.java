package com.projetocopa.repository;

import com.projetocopa.model.Partida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PartidaRepository {

    private static final ObservableList<Partida> partidas =
            FXCollections.observableArrayList();

    public static ObservableList<Partida> getPartidas() {
        return partidas;
    }

    public static void adicionar(Partida partida) {
        partidas.add(partida);
    }

    public static void remover(Partida partida) {
        partidas.remove(partida);
    }
}