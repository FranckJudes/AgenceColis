package com.example.agencecolis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HistoriqueEnvois implements Initializable {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private TableColumn<Client, String> ColAge;

    @FXML
    private TableColumn<Envoie, String>  ColDescription;

    @FXML
    private TableColumn<Client, String>  ColID;

    @FXML
    private TableColumn<Client, String>  ColNom;

    @FXML
    private TableColumn<Envoie, String> ColNomDesti;

    @FXML
    private TableColumn<Client, String>  ColPrenom;

    @FXML
    private TableColumn<Envoie, String>  ColPrenomDes;

    @FXML
    private TableColumn<Client, String>  ColProfession;


    @FXML
    private TableColumn<Envoie, String> ColDate;


    @FXML
    private TableColumn<Envoie, String>  ColStatus;

    @FXML
    private TableColumn<Envoie, String>  ColTrajet;

    @FXML
    private TableColumn<Envoie, String>  ColType;

    @FXML
    private TableColumn<Client , String>  ColVille;

    @FXML
    private AnchorPane client;

    @FXML
    private TableView<Envoie> table;

    @FXML
    private TableView<Client> table1;

    @FXML
    void GestionClient(ActionEvent event) {
        try {
            Parent garent = FXMLLoader.load(getClass().getResource("Client.fxml"));
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene s =  new Scene(garent);
            window.setScene(s);
            window.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GestionColis(ActionEvent event) {
        try {
            Parent garent = FXMLLoader.load(getClass().getResource("Colis.fxml"));
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene s =  new Scene(garent);
            window.setScene(s);
            window.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GestionEnvoies(ActionEvent event) {
        try {
            Parent garent = FXMLLoader.load(getClass().getResource("Envoie.fxml"));
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene s =  new Scene(garent);
            window.setScene(s);
            window.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GestionHistorique(ActionEvent event) {
        try {
            Parent garent = FXMLLoader.load(getClass().getResource("HistoriqueEnvois.fxml"));
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene s =  new Scene(garent);
            window.setScene(s);
            window.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GestionPersonnel(ActionEvent event) {
        try {
            Parent garent = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene s =  new Scene(garent);
            window.setScene(s);
            window.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void GestionReception(ActionEvent event) {
        try {
            Parent garent = FXMLLoader.load(getClass().getResource("Reception.fxml"));
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene s =  new Scene(garent);
            window.setScene(s);
            window.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getData(MouseEvent event) {
        Client politique = table1.getSelectionModel().getSelectedItem();
        int CNI_Client = politique.getCNI_Client();
        ObservableList<Envoie> politiques = FXCollections.observableArrayList();
        ObservableList<Reception> politiques1 = FXCollections.observableArrayList();
        String query = "select nomDes, PrenomDes,agenceDestination,statut,type,description,dateExpedition from envois  join colis using(idColis) where CNI_Client=?;";

        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            st.setString(1, String.valueOf(CNI_Client));
            rs = st.executeQuery();
            while (rs.next()){
                Envoie st = new Envoie();
                Reception st1 = new Reception();

                st.setAgenceDestination(rs.getString("agenceDestination"));
                st.setStatus(rs.getString("statut"));
                st.setDateExpedition(rs.getString("dateExpedition"));
                st.setNomDes(rs.getString("nomDes"));
                st.setPrenomDes(rs.getString("prenomDes"));
                st.setType(rs.getString("type"));
                st.setDescription(rs.getString("description"));


                politiques.add(st);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Envoie> list = politiques;
        table.setItems(list);
        ColDescription.setCellValueFactory(new PropertyValueFactory<Envoie,String>("description"));
        ColDate.setCellValueFactory(new PropertyValueFactory<Envoie,String>("dateExpedition"));
        ColPrenomDes.setCellValueFactory(new PropertyValueFactory<Envoie, String>("prenomDes"));
        ColNomDesti.setCellValueFactory(new PropertyValueFactory<Envoie,String>("nomDes"));
        ColStatus.setCellValueFactory(new PropertyValueFactory<Envoie,String>("status"));
        ColTrajet.setCellValueFactory(new PropertyValueFactory<Envoie, String>("agenceDestination"));
        ColType.setCellValueFactory(new PropertyValueFactory<Envoie, String>("type"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showClient();
    }
    public void showClient(){
        ObservableList<Client> list = getPolitique();
        table1.setItems(list);
        ColID.setCellValueFactory(new PropertyValueFactory<Client,String>("CNI_Client"));
        ColNom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        ColPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        ColAge.setCellValueFactory(new PropertyValueFactory<Client, String>("age"));
        ColVille.setCellValueFactory(new PropertyValueFactory<Client, String>("ville"));
        ColProfession.setCellValueFactory(new PropertyValueFactory<Client,String>("profession"));
    }
    public ObservableList<Client> getPolitique(){
        ObservableList<Client> politiques = FXCollections.observableArrayList();

        String query = "select * from client";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Client st = new Client();
                st.setCNI_Client(rs.getInt("CNI_Client"));
                st.setNom(rs.getString("nom"));
                st.setPrenom(rs.getString("prenom"));
                st.setAge(rs.getString("age"));
                st.setVille(rs.getString("ville"));
                st.setProfession(rs.getString("profession"));
                politiques.add(st);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return politiques;
    }
}
