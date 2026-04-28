package com.projetocopa.controllersJavaFX;

import com.projetocopa.model.Partida;
import com.projetocopa.model.Selecao;

import java.time.LocalDate;

import com.projetocopa.model.Estadio;
import com.projetocopa.repository.SelecaoRepository;
import com.projetocopa.repository.EstadioRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PartidaFormController {

    @FXML private ComboBox<Selecao> cbSelecaoA;
    @FXML private ComboBox<Selecao> cbSelecaoB;
    @FXML private ComboBox<Estadio> cbEstadio;
    @FXML private DatePicker dpData;
    
    private PartidaListaController listaController;
    private Partida partidaEdicao;
    
    public void initialize() {
        cbSelecaoA.setItems(SelecaoRepository.getSelecoes());
        cbSelecaoB.setItems(SelecaoRepository.getSelecoes());
        cbEstadio.setItems(EstadioRepository.getEstadios());
        
        configuraComboBoxSelecao(cbSelecaoA);
        configuraComboBoxSelecao(cbSelecaoB);
        configuraComboBoxEstadio(cbEstadio);
        
    }
    
    public void setListaController(PartidaListaController controller) {
    	this.listaController = controller;
    }
    
    public void setPartida(Partida partida) {
    	this.partidaEdicao = partida;
    	cbSelecaoA.setValue(partida.getSelecaoA());
    	cbSelecaoB.setValue(partida.getSelecaoB());
    	cbEstadio.setValue(partida.getEstadio());
    	dpData.setValue(partida.getData());
    }
    
    private void configuraComboBoxSelecao(ComboBox<Selecao> comboBox) {
    	comboBox.setCellFactory(cb -> new ListCell<Selecao>() {
            @Override
            protected void updateItem(Selecao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome());
            }
        });

    	comboBox.setButtonCell(new ListCell<Selecao>() {
            @Override
            protected void updateItem(Selecao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome());
            }
        });
    }
    
    private void configuraComboBoxEstadio(ComboBox<Estadio> comboBox) {
    	comboBox.setCellFactory(cb -> new ListCell<Estadio>() {
            @Override
            protected void updateItem(Estadio item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getResumo());
            }
        });

    	comboBox.setButtonCell(new ListCell<Estadio>() {
            @Override
            protected void updateItem(Estadio item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getResumo());
            }
        });
    }

    @FXML
    public void salvar() {

        Selecao selecaoA = cbSelecaoA.getValue();
        Selecao selecaoB = cbSelecaoB.getValue();
        Estadio estadio = cbEstadio.getValue();
        LocalDate data = dpData.getValue();

        if (selecaoA == null || selecaoB == null ||
            estadio == null || dpData.getValue() == null) {

            new Alert(Alert.AlertType.WARNING, "Preencha todos os campos").show();
            return;
        }

        if (selecaoA.equals(selecaoB)) {
            new Alert(Alert.AlertType.WARNING, "Seleções devem ser diferentes").show();
            return;
        }
        
        if (partidaEdicao == null) {
        	//novo
        	Partida novo = new Partida(selecaoA, selecaoB, estadio, data);
        	listaController.adicionarPartida(novo);
		} else {
			//edição
			partidaEdicao.setSelecaoA(selecaoA);
			partidaEdicao.setSelecaoB(selecaoB);
			partidaEdicao.setEstadio(estadio);
			partidaEdicao.setData(data);
			listaController.atualizarTabela();
		}
        
        fecharJanela();
    }
    
    private void fecharJanela() {
        Stage stage = (Stage) cbSelecaoA.getScene().getWindow();
        stage.close();
    }
}