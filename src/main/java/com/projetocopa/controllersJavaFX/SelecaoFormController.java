package com.projetocopa.controllersJavaFX;

import com.projetocopa.model.Selecao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelecaoFormController {

    @FXML
    private TextField txtNome;

    private SelecaoController selecaoController;
    private Selecao selecaoEdicao;

    public void setSelecaoController(SelecaoController controller) {
        this.selecaoController = controller;
    }

    //usado quando for editar
    public void setSelecao(Selecao selecao) {
        this.selecaoEdicao = selecao;
        txtNome.setText(selecao.getNome());
    }

    public void salvar() {

        String nome = txtNome.getText();

        if (selecaoEdicao == null) {
            // novo
            Selecao nova = new Selecao(nome);
            selecaoController.adicionarSelecao(nova);

        } else {
            // edição
            selecaoEdicao.setNome(nome);
            selecaoController.atualizarTabela();
        }

        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}