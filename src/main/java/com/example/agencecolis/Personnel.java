package com.example.agencecolis;

public class Personnel {
    private int CNI_Personnel;
    private String nom;
    private  String prenom;
    private  String nomService;
    private  String typePermis;

    public int getCNI_Personnel() {
        return CNI_Personnel;
    }

    public void setCNI_Personnel(int CNI_Personnel) {
        this.CNI_Personnel = CNI_Personnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getTypePermis() {
        return typePermis;
    }

    public void setTypePermis(String typePermis) {
        this.typePermis = typePermis;
    }
}
