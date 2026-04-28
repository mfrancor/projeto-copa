package com.projetocopa.repository;

import com.projetocopa.model.Estadio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstadioRepository {
	
	private static final ObservableList<Estadio> estadios =
            FXCollections.observableArrayList();
	
	// inicialização opcional
	static {
		estadios.add(new Estadio("Hard Rock Stadium", "Miami"));
		estadios.add(new Estadio("MetLife Stadium", "Nova York"));
		estadios.add(new Estadio("SoFi Stadium", "Los Angeles"));
		estadios.add(new Estadio("Estádio Azteca", "Cidade do México"));
	}
	
	public static ObservableList<Estadio> getEstadios() {
		return estadios;
	}
	
	public static void adicionar(Estadio estadio) {
		estadios.add(estadio);
	}
	
	public static void remover(Estadio estadio) {
		estadios.remove(estadio);
	}

}
