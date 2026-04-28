package com.projetocopa.controllersJavaFX;

import com.projetocopa.model.Estadio;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EstadioFormController {

	@FXML
    private TextField txtNome;
	
	@FXML
    private TextField txtCidade;

    private EstadioListaController listaController;
    private Estadio estadioEdicao;

    public void setListaController(EstadioListaController controller) {
        this.listaController = controller;
    }

    //usado quando for editar
    public void setEstadio(Estadio estadio) {
        this.estadioEdicao = estadio;
        txtNome.setText(estadio.getNome());
        txtCidade.setText(estadio.getCidade());
    }

    public void salvar() {

        String nome = txtNome.getText();
        String cidade = txtCidade.getText();

        if (estadioEdicao == null) {
            // novo
            Estadio novo = new Estadio(nome, cidade);
            listaController.adicionarEstadio(novo);

        } else {
            // edição
        	estadioEdicao.setNome(nome);
        	estadioEdicao.setCidade(cidade);
        	listaController.atualizarTabela();
        }

        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}
