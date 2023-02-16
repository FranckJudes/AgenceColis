package com.example.agencecolis;

public class Client {
   private int CNI_Client;
   private String nom;
   private  String prenom;
   private  String ville;
   private String profession;
   private  String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getCNI_Client() {
        return CNI_Client;
    }

    public void setCNI_Client(int CNI_Client) {
        this.CNI_Client = CNI_Client;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
