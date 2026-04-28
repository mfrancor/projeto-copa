package com.projetocopa.repository;

import com.projetocopa.model.Estadio;
import com.projetocopa.model.Partida;
import com.projetocopa.model.Selecao;

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
    
    public static boolean existePartidaComSelecao(Selecao selecao) {
    	return !partidas
    			.filtered(partida -> partida.getSelecaoA().equals(selecao) || partida.getSelecaoB().equals(selecao))
    			.isEmpty();
    }
    
    public static boolean existePartidaComEstadio(Estadio estadio) {
    	return !partidas
    			.filtered(partida -> partida.getEstadio().equals(estadio))
    			.isEmpty();
    }
}