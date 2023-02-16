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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int CNI_Client = 0;
    @FXML
    private TableColumn<Client, String> ColAge;

    @FXML
    private TableColumn<Client, String> ColID;

    @FXML
    private TableColumn<Client, String> ColNom;

    @FXML
    private TableColumn<Client, String> ColPrenom;

    @FXML
    private TableColumn<Client, String> ColProfession;

    @FXML
    private TableColumn<Client, String> ColVille;

    @FXML
    private TextField age;

    @FXML
    private AnchorPane client;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField profession;

    @FXML
    private TextField recherche;

    @FXML
    private TableView<Client> table;

    @FXML
    private TextField ville;

    @FXML
    void BtnClear(ActionEvent event) {
        clear();
    }

    @FXML
    void BtnRecherche(ActionEvent event) {

    }

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
    void HistoriqueEnvoies(ActionEvent event) {
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
    void btnAjout(ActionEvent event) {
        String query = "insert into client(nom,prenom,profession,ville,age) values(?,?,?,?,?)";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            st.setString(1,nom.getText());
            st.setString(2,prenom.getText());
            st.setString(3,profession.getText());
            st.setString(4,ville.getText());
            st.setString(5,age.getText());
            st.executeUpdate();
            clear();
            showClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnModifier(ActionEvent event) {
        String update = "update client set nom = ?, prenom = ?, profession = ?,ville = ?,age=? where CNI_Client=?";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(update);
            st.setString(1,nom.getText());
            st.setString(2,prenom.getText());
            st.setString(3,profession.getText());
            st.setString(4, ville.getText());
            st.setString(5, age.getText());
            st.setString(6, String.valueOf(CNI_Client));
            st.executeUpdate();
            clear();
            showClient();

        } catch (  SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSupprimer(ActionEvent event) {
        String delete = "delete from client where CNI_Client = ?";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(delete);
            st.setInt(1,CNI_Client);
            st.executeUpdate();
            clear();
            showClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void x(MouseEvent event) {

    }

    // nettoyer les champs du formulaire
    void clear(){
        nom.setText(null);
        prenom.setText(null);
        age.setText(null);
        profession.setText(null);
        ville.setText(null);
    }
    @FXML
    void getData(MouseEvent event) {
        Client politique = table.getSelectionModel().getSelectedItem(); // un objet qui recupere l'element selectionner du tableau
        CNI_Client = politique.getCNI_Client();
        nom.setText(politique.getNom());
        prenom.setText(politique.getPrenom());
        profession.setText(politique.getProfession());
        age.setText(politique.getAge());
        ville.setText(politique.getVille());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            showClient();
    }
    public void showClient(){
        ObservableList<Client> list = getPolitique();
        table.setItems(list);
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
