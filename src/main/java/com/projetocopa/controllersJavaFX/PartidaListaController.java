package com.projetocopa.controllersJavaFX;

import java.io.IOException;
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
    
    // =========================
    // AÇÕES
    // =========================

    @FXML
    public void novaPartida() {
        abrirFormulario(null);
    }
    
    @FXML
    public void editarPartida() {
        Partida selecionada = tabela.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            mostrarAlerta("Selecione uma partida para editar");
            return;
        }

        abrirFormulario(selecionada);
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
    
 // =========================
    // FORMULÁRIO MODAL
    // =========================

    private void abrirFormulario(Partida partida) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/partida-form.fxml")
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            //torna modal (bloqueia tela atrás)
            stage.initModality(Modality.APPLICATION_MODAL);

            //vincula à janela principal (boa prática)
            stage.initOwner(tabela.getScene().getWindow());

            //pega controller do form
            PartidaFormController controller = loader.getController();
            controller.setListaController(this);

            if (partida != null) {
                controller.setPartida(partida);
            }

            stage.setTitle("Cadastro de Partida");
            stage.setResizable(false);

            //ESSENCIAL: trava até fechar
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // =========================
    // MÉTODOS DE APOIO
    // =========================

    public void adicionarPartida(Partida partida) {
    	PartidaRepository.adicionar(partida);
    }

    public void atualizarTabela() {
        tabela.refresh();
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.show();
    }
}