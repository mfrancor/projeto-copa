package com.projetocopa.repository;

import com.projetocopa.model.Selecao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelecaoRepository {

    private static final ObservableList<Selecao> selecoes =
            FXCollections.observableArrayList();

    // inicialização opcional
    static {
        selecoes.add(new Selecao("Brasil"));
        selecoes.add(new Selecao("Argentina"));
        selecoes.add(new Selecao("França"));
        selecoes.add(new Selecao("Potugal"));
    }

    public static ObservableList<Selecao> getSelecoes() {
        return selecoes;
    }

    public static void adicionar(Selecao selecao) {
        selecoes.add(selecao);
    }

    public static void remover(Selecao selecao) {
        selecoes.remove(selecao);
    }
}