package com.projetocopa.controllersJavaFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaInicialController {

    private void abrirTela(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/" + fxml)
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirSelecoes() {
        abrirTela("selecao-lista.fxml");
    }

    public void abrirPartidas() {
        abrirTela("partida-lista.fxml");
    }

    public void abrirEstadios() {
        abrirTela("estadio-lista.fxml");
    }
}