package com.projetocopa.controllersJavaFX;

import java.io.IOException;
import java.util.Optional;

import com.projetocopa.model.Selecao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelecaoController {

    @FXML
    private TableView<Selecao> tabela;

    @FXML
    private TableColumn<Selecao, String> colNome;

    private ObservableList<Selecao> dados;

    public void initialize() {

        // ligação das colunas
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        // dados iniciais
        dados = FXCollections.observableArrayList(
                new Selecao("Brasil"),
                new Selecao("Argentina")
        );

        tabela.setItems(dados);
    }

    // =========================
    // AÇÕES
    // =========================

    @FXML
    public void novaSelecao() {
        abrirFormulario(null);
    }

    @FXML
    public void editarSelecao() {
        Selecao selecionada = tabela.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            mostrarAlerta("Selecione um item para editar");
            return;
        }

        abrirFormulario(selecionada);
    }
    
    @FXML
    public void excluirSelecao() {
        Selecao selecionada = tabela.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            mostrarAlerta("Selecione um item para excluir");
            return;
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir a seleção " + selecionada.getNome() + "?");
        
        Optional<ButtonType> escolha = alert.showAndWait();
        if (escolha.isPresent() && escolha.get() == ButtonType.OK) {
        	dados.remove(selecionada);			
		}
    }

    // =========================
    // FORMULÁRIO MODAL
    // =========================

    private void abrirFormulario(Selecao selecao) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/selecao-form.fxml")
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            //torna modal (bloqueia tela atrás)
            stage.initModality(Modality.APPLICATION_MODAL);

            //vincula à janela principal (boa prática)
            stage.initOwner(tabela.getScene().getWindow());

            //pega controller do form
            SelecaoFormController controller = loader.getController();
            controller.setSelecaoController(this);

            if (selecao != null) {
                controller.setSelecao(selecao);
            }

            stage.setTitle("Cadastro de Seleção");
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

    public void adicionarSelecao(Selecao selecao) {
        dados.add(selecao);
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