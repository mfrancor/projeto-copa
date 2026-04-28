package com.projetocopa.controllersJavaFX;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class TelaInicialController {

    @FXML
    private StackPane conteudo;

    /**
     * Método genérico para carregar telas dentro da área central
     */
    private void carregarTela(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/" + fxml)
            );

            Parent tela = loader.load();            
            conteudo.getChildren().setAll(tela);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // AÇÕES DO MENU
    // =========================

    @FXML
    public void abrirSelecoes() {
        carregarTela("selecao-lista.fxml");
    }

    @FXML
    public void abrirPartidas() {
        carregarTela("partida-lista.fxml");
    }

    @FXML
    public void abrirEstadios() {
        carregarTela("estadio-lista.fxml");
    }
    
    public void limparConteudo() {
        conteudo.getChildren().clear();
    }
}