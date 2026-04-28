package com.projetocopa.controllersJavaFX;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.projetocopa.model.Partida;
import com.projetocopa.repository.PartidaRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PartidaListaController {

    @FXML
    private TableView<Partida> tabela;
    @FXML
    private TableColumn<Partida, String> colDescricao;
    @FXML
    private TableColumn<Partida, String> colEstadio;
    @FXML
    private TableColumn<Partida, String> colData;

    public void initialize() {

        //Brasil x Argentina    	
    	colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        colEstadio.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getEstadio().getResumo()));

        colData.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));    	

        tabela.setItems(PartidaRepository.getPartidas());
    }

    @FXML
    public void novaPartida() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/partida-form.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(tabela.getScene().getWindow());

            stage.setTitle("Cadastro de Partida");
            stage.setResizable(false);

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void excluirPartida() {
        Partida selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione uma partida").show();
            return;
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir a partida " + selecionado.getDescricao() + "?");
        
        Optional<ButtonType> escolha = alert.showAndWait();
        if (escolha.isPresent() && escolha.get() == ButtonType.OK) {
        	PartidaRepository.remover(selecionado);	
		}
    }
}