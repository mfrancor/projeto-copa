package com.projetocopa.controllersJavaFX;

import java.io.IOException;
import java.util.Optional;

import com.projetocopa.model.Estadio;
import com.projetocopa.repository.EstadioRepository;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EstadioListaController {
	@FXML
    private TableView<Estadio> tabela;

    @FXML
    private TableColumn<Estadio, String> colNome;
    
    @FXML
    private TableColumn<Estadio, String> colCidade;


    public void initialize() {

        // ligação das colunas
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));

        //Atribui os dados à tabela
        tabela.setItems(EstadioRepository.getEstadios());
    }

    // =========================
    // AÇÕES
    // =========================

    @FXML
    public void novoEstadio() {
        abrirFormulario(null);
    }

    @FXML
    public void editarEstadio() {
        Estadio selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            mostrarAlerta("Selecione um estádio para editar");
            return;
        }

        abrirFormulario(selecionado);
    }
    
    @FXML
    public void excluirEstadio() {
        Estadio selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            mostrarAlerta("Selecione um estádio para excluir");
            return;
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir o estádio " + selecionado.getNome() + "?");
        
        Optional<ButtonType> escolha = alert.showAndWait();
        if (escolha.isPresent() && escolha.get() == ButtonType.OK) {
        	EstadioRepository.remover(selecionado);	
		}
    }

    // =========================
    // FORMULÁRIO MODAL
    // =========================

    private void abrirFormulario(Estadio estadio) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/estadio-form.fxml")
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            //torna modal (bloqueia tela atrás)
            stage.initModality(Modality.APPLICATION_MODAL);

            //vincula à janela principal (boa prática)
            stage.initOwner(tabela.getScene().getWindow());

            //pega controller do form
            EstadioFormController controller = loader.getController();
            controller.setListaController(this);

            if (estadio != null) {
                controller.setEstadio(estadio);
            }

            stage.setTitle("Cadastro de Estádio");
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

    public void adicionarEstadio(Estadio estadio) {
    	EstadioRepository.adicionar(estadio);
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
