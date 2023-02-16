package com.example.agencecolis;

public class Reception {

    private String nom;
    private  String prenom;
    private String ville;
    private String nomDes;

    private  String PrenomDes;
    private String agenceDestination;
    private String statut;

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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNomDes() {
        return nomDes;
    }

    public void setNomDes(String nomDes) {
        this.nomDes = nomDes;
    }

    public String getPrenomDes() {
        return PrenomDes;
    }

    public void setPrenomDes(String prenomDes) {
        PrenomDes = prenomDes;
    }

    public String getAgenceDestination() {
        return agenceDestination;
    }

    public void setAgenceDestination(String agenceDestination) {
        this.agenceDestination = agenceDestination;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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

    private String type;
    private String  description;
}
