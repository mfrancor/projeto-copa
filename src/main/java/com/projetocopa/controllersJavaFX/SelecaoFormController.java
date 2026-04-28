package com.projetocopa.controllersJavaFX;

import com.projetocopa.model.Selecao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelecaoFormController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPais;

    private SelecaoController selecaoController;
    private Selecao selecaoEdicao;

    public void setSelecaoController(SelecaoController controller) {
        this.selecaoController = controller;
    }

    //usado quando for editar
    public void setSelecao(Selecao selecao) {
        this.selecaoEdicao = selecao;

        txtNome.setText(selecao.getNome());
        txtPais.setText(selecao.getPais());
    }

    public void salvar() {

        String nome = txtNome.getText();
        String pais = txtPais.getText();

        if (selecaoEdicao == null) {
            // novo
            Selecao nova = new Selecao(nome, pais);
            selecaoController.adicionarSelecao(nova);

        } else {
            // edição
            selecaoEdicao.setNome(nome);
            selecaoEdicao.setPais(pais);

            selecaoController.atualizarTabela();
        }

        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}