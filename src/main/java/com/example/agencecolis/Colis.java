package com.example.agencecolis;

public class Colis {

    private  int idColis;
    private String type;
    private String description;
    private int prixFrais;

    public int getIdColis() {
        return idColis;
    }

    public void setIdColis(int idColis) {
        this.idColis = idColis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrixFrais() {
        return prixFrais;
    }

    public void setPrixFrais(int prixFrais) {
        this.prixFrais = prixFrais;
    }
}
