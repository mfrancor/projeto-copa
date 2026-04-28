package com.projetocopa.controllersJavaFX;

import com.projetocopa.model.Selecao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SelecaoController {

    @FXML
    private TableView<Selecao> tabela;

    @FXML
    private TableColumn<Selecao, String> colNome;

    @FXML
    private TableColumn<Selecao, String> colPais;

    private ObservableList<Selecao> dados;

    public void initialize() {

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

        dados = FXCollections.observableArrayList(
                new Selecao("Brasil", "Brasil"),
                new Selecao("Argentina", "Argentina")
        );

        tabela.setItems(dados);
    }

    public void novaSelecao() {
        abrirFormulario(null);
    }

    public void editarSelecao() {
        Selecao selecionada = tabela.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            mostrarAlerta("Selecione um item para editar");
            return;
        }

        abrirFormulario(selecionada);
    }

    public void excluirSelecao() {
        Selecao selecionada = tabela.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            mostrarAlerta("Selecione um item para excluir");
            return;
        }

        dados.remove(selecionada);
    }

    private void abrirFormulario(Selecao selecao) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/selecao-form.fxml")
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            SelecaoFormController controller = loader.getController();
            controller.setSelecaoController(this);

            if (selecao != null) {
                controller.setSelecao(selecao);
            }

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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