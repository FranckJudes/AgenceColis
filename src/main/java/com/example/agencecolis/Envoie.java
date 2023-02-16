package com.example.agencecolis;

import java.sql.Date;

public class Envoie {
    private  int idEnvois;
    private  int CNI_Client;
    private  int CNI_Personnel;

    public String getNomDes() {
        return nomDes;
    }

    public void setNomDes(String nomDes) {
        this.nomDes = nomDes;
    }

    public String getPrenomDes() {
        return prenomDes;
    }

    public void setPrenomDes(String prenomDes) {
        this.prenomDes = prenomDes;
    }

    private String dateExpedition;
    private String codeReception;

    private  String status;
    private  String agenceDestination;

    private String nomDes;
    private String prenomDes;

    private  String type;

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

    private  String description;

    public int getIdEnvois() {
        return idEnvois;
    }

    public void setIdEnvois(int idEnvois) {
        this.idEnvois = idEnvois;
    }

    public int getCNI_Client() {
        return CNI_Client;
    }

    public void setCNI_Client(int CNI_Client) {
        this.CNI_Client = CNI_Client;
    }

    public int getCNI_Personnel() {
        return CNI_Personnel;
    }

    public void setCNI_Personnel(int CNI_Personnel) {
        this.CNI_Personnel = CNI_Personnel;
    }

    public String getDateExpedition() {
        return dateExpedition;
    }

    public void setDateExpedition(String dateExpedition) {
        this.dateExpedition = dateExpedition;
    }

    public String getCodeReception() {
        return codeReception;
    }

    public void setCodeReception(String codeReception) {
        this.codeReception = codeReception;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgenceDestination() {
        return agenceDestination;
    }

    public void setAgenceDestination(String agenceDestination) {
        this.agenceDestination = agenceDestination;
    }

}
