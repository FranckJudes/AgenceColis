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
import javafx.scene.control.Alert;
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

public class ReceptionController implements Initializable {

    @FXML
    private TableColumn<Reception, String> ColDescription;

    @FXML
    private TableColumn<Reception, String> ColNomDesti;

    @FXML
    private TableColumn<Reception, String> ColNomEx;

    @FXML
    private TableColumn<Reception, String> ColPrenomDes;

    @FXML
    private TableColumn<Reception, String> ColPrenomEx;

    @FXML
    private TableColumn<Reception, String> ColStatus;

    @FXML
    private TableColumn<Reception, String> ColTrajet;

    @FXML
    private TableColumn<Reception, String> ColType;

    @FXML
    private TableColumn<Reception, String> ColVilleEx;

    @FXML
    private TextField Traget;

    @FXML
    private AnchorPane client;

    @FXML
    private TextField codeReception;

    @FXML
    private TextField nomExpediteur;

    @FXML
    private TextField nomRecepteur;

    @FXML
    private TextField prenomExpediteur;

    @FXML
    private TextField prenomRecepetur;

    @FXML
    private TextField staut;

    @FXML
    private TableView<Reception> table;

    @FXML
    private TextField typecolis;

    @FXML
    private TextField villeExpediteur;
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

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
    void btnConfirmer(ActionEvent event) {
        if(codeReception.getText().equals(""))
        {
            Alert alert1 =  new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Alert");
            alert1.setContentText("Svp Veuillez entrez le code de reception");
            alert1.show();
        }
        else{
            String query = "select * from envois where codeReception= ?";

            conn = DBConnexion.getCon();
            try {
                st = conn.prepareStatement(query);
                st.setString(1,codeReception.getText());
                rs = st.executeQuery();
               if (rs.next()){
                   String update = "update envois set statut = ? where codeReception=?";
                   conn = DBConnexion.getCon();
                   st = conn.prepareStatement(update);
                   st.setString(1,"Recu");
                   st.setString(2,codeReception.getText());
                   st.executeUpdate();

                   Alert alert1 =  new Alert(Alert.AlertType.INFORMATION);
                   alert1.setTitle("Alert");
                   alert1.setContentText("Colis Recu avec success");
                   alert1.show();

                   Parent garent = FXMLLoader.load(getClass().getResource("Reception.fxml"));
                   Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                   Scene s =  new Scene(garent);
                   window.setScene(s);
                   window.show();
               }else
               {
                   Alert alert1 =  new Alert(Alert.AlertType.INFORMATION);
                   alert1.setTitle("Alert");
                   alert1.setContentText("Colis introuvable ou Code de reception incorrect");
                   alert1.show();
               }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    void getData(MouseEvent event) {
        Reception politique = table.getSelectionModel().getSelectedItem(); // un objet qui recupere l'element selectionner du tableau
          nomExpediteur.setText(politique.getNom());
        prenomExpediteur.setText(politique.getPrenom());
        villeExpediteur.setText(politique.getVille());
        nomRecepteur.setText(politique.getNomDes());
        prenomRecepetur.setText(politique.getPrenomDes());
         Traget.setText(politique.getAgenceDestination());
         staut.setText(politique.getStatut());
        typecolis.setText(politique.getType());

    }
    public ObservableList<Reception> getPolitique(){
        ObservableList<Reception> politiques = FXCollections.observableArrayList();

        String query = "select nom,prenom,ville, nomDes, PrenomDes,agenceDestination,statut,type,description from envois join client using(CNI_Client) join colis using(idColis) where statut='envoyer' ";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Reception st = new Reception();
                st.setNom(rs.getString("nom"));
                st.setPrenom(rs.getString("prenom"));
                st.setVille(rs.getString("ville"));
                st.setNomDes(rs.getString("nomDes"));
                st.setPrenomDes(rs.getString("prenomDes"));
                st.setAgenceDestination(rs.getString("agenceDestination"));
                st.setStatut(rs.getString("statut"));
                st.setType(rs.getString("type"));
                st.setDescription(rs.getString("description"));
                politiques.add(st);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return politiques;
    }
    public void showClient(){
        ObservableList<Reception> list = getPolitique();
        table.setItems(list);
        ColNomEx.setCellValueFactory(new PropertyValueFactory<Reception,String>("nom"));
        ColPrenomEx.setCellValueFactory(new PropertyValueFactory<Reception,String>("prenom"));
        ColVilleEx.setCellValueFactory(new PropertyValueFactory<Reception,String>("ville"));
        ColNomDesti.setCellValueFactory(new PropertyValueFactory<Reception,String>("nomDes"));
        ColPrenomDes.setCellValueFactory(new PropertyValueFactory<Reception,String>("prenomDes"));
        ColTrajet.setCellValueFactory(new PropertyValueFactory<Reception,String>("agenceDestination"));
        ColStatus.setCellValueFactory(new PropertyValueFactory<Reception,String>("statut"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<Reception,String>("description"));
        ColType.setCellValueFactory(new PropertyValueFactory<Reception,String>("type"));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showClient();
    }
}
