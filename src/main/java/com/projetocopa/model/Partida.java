package com.projetocopa.model;

import java.time.LocalDate;

public class Partida {

    private Selecao selecaoA;
    private Selecao selecaoB;
    private Estadio estadio;
    private LocalDate data;

    public Partida(Selecao selecaoA, Selecao selecaoB, Estadio estadio, LocalDate data) {
        this.selecaoA = selecaoA;
        this.selecaoB = selecaoB;
        this.estadio = estadio;
        this.data = data;
    }

    public Selecao getSelecaoA() { return selecaoA; }
    public Selecao getSelecaoB() { return selecaoB; }
    public Estadio getEstadio() { return estadio; }
    public LocalDate getData() { return data; }

    public void setSelecaoA(Selecao selecaoA) { this.selecaoA = selecaoA; }
    public void setSelecaoB(Selecao selecaoB) { this.selecaoB = selecaoB; }
    public void setEstadio(Estadio estadio) { this.estadio = estadio; }
    public void setData(LocalDate data) { this.data = data; }

    // método útil para exibição
    public String getDescricao() {
        return selecaoA.getNome() + " x " + selecaoB.getNome();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partida)) return false;

        Partida p = (Partida) o;

        return selecaoA.equals(p.selecaoA) &&
               selecaoB.equals(p.selecaoB) &&
               data.equals(p.data);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(selecaoA, selecaoB, data);
    }
}