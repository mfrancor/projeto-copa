package com.projetocopa.controllersJavaFX;

import com.projetocopa.model.Selecao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelecaoFormController {

    @FXML
    private TextField txtNome;

    private SelecaoListaController listaController;
    private Selecao selecaoEdicao;

    public void setListaController(SelecaoListaController controller) {
        this.listaController = controller;
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
            listaController.adicionarSelecao(nova);

        } else {
            // edição
            selecaoEdicao.setNome(nome);
            listaController.atualizarTabela();
        }

        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}