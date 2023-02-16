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

public class PersonnelController implements Initializable {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int CNI_Personnel=0;
    @FXML
    private TableColumn<Personnel, String> ColID;

    @FXML
    private TableColumn<Personnel, String> ColPermis;

    @FXML
    private TableColumn<Personnel, String> ColPrenom;

    @FXML
    private TableColumn<Personnel, String> ColService;

    @FXML
    private TableColumn<Personnel, String> Colnom;

    @FXML
    private AnchorPane client;

    @FXML
    private TextField nom;

    @FXML
    private TextField permis;

    @FXML
    private TextField prenom;

    @FXML
    private TextField recherche;

    @FXML
    private TextField service;

    @FXML
    private TableView<Personnel> table;

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
    void btnAjout(ActionEvent event) {
        String query = "insert into personnel(nom,prenom,nomService,typePermis) values(?,?,?,?)";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            st.setString(1,nom.getText());
            st.setString(2,prenom.getText());
            st.setString(3, service.getText());
            st.setString(4, permis.getText());
            st.executeUpdate();
            clear();
            showClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @FXML
    void btnModifier(ActionEvent event) {
        String update = "update personnel set nom = ?, prenom = ?, nomService = ?, typePermis= ? where CNI_Personnel=?";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(update);
            st.setString(1,nom.getText());
            st.setString(2,prenom.getText());
            st.setString(3, service.getText());
            st.setString(4, permis.getText());
            st.setString(5, String.valueOf(CNI_Personnel));
            st.executeUpdate();
            clear();
            showClient();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupprimer(ActionEvent event) {
        String delete = "delete from personnel where CNI_Personnel = ?";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(delete);
            st.setInt(1,CNI_Personnel);
            st.executeUpdate();
            clear();
            showClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void getData(MouseEvent event) {
        Personnel politique = table.getSelectionModel().getSelectedItem(); // un objet qui recupere l'element selectionner du tableau
        CNI_Personnel = politique.getCNI_Personnel();
        nom.setText(politique.getNom());
        prenom.setText(politique.getPrenom());
        permis.setText(politique.getTypePermis());
        service.setText(politique.getNomService());

    }
    // nettoyer les champs du formulaire
    void clear(){
        nom.setText(null);
        prenom.setText(null);
        permis.setText(null);
        service.setText(null);
    }
    public ObservableList<Personnel> getPolitique(){
        ObservableList<Personnel> politiques = FXCollections.observableArrayList();

        String query = "select * from personnel";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Personnel st = new Personnel();
                st.setCNI_Personnel(rs.getInt("CNI_Personnel"));
                st.setNom(rs.getString("nom"));
                st.setPrenom(rs.getString("prenom"));
                st.setNomService(rs.getString("nomService"));
                st.setTypePermis(rs.getString("typePermis"));
                politiques.add(st);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return politiques;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showClient();
    }
    public void showClient(){
        ObservableList<Personnel> list = getPolitique();
        table.setItems(list);
        ColID.setCellValueFactory(new PropertyValueFactory<Personnel,String>("CNI_Personnel"));
        Colnom.setCellValueFactory(new PropertyValueFactory<Personnel,String>("nom"));
        ColPrenom.setCellValueFactory(new PropertyValueFactory<Personnel, String>("prenom"));
        ColService.setCellValueFactory(new PropertyValueFactory<Personnel, String>("nomService"));
        ColPermis.setCellValueFactory(new PropertyValueFactory<Personnel  ,String>("typePermis"));
    }
}
