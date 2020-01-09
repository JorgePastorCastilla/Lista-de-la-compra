package com.example.examentrimestre1;

public class Item {

    private int codi;
    private String nom;
    private String quantitat;
    private boolean comprat;

    public Item(int codi, String nom, String quantitat, boolean comprat) {
        this.codi = codi;
        this.nom = nom;
        this.quantitat = quantitat;
        this.comprat = comprat;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(String quantitat) {
        this.quantitat = quantitat;
    }

    public boolean isComprat() {
        return comprat;
    }

    public void setComprat(boolean comprat) {
        this.comprat = comprat;
    }
}
