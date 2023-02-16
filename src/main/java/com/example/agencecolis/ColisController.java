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
import javafx.scene.control.TextArea;
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

public class ColisController implements Initializable {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int idColis=0;

    @FXML
    private TableColumn<Colis, String> ColID;

    @FXML
    private TableColumn<Colis, String> ColType;

    @FXML
    private  TableColumn<Colis, String> ColDescription;

    @FXML
    private  TableColumn<Colis, String> ColFrais;

    @FXML
    private AnchorPane client;

    @FXML
    private TextArea description;

    @FXML
    private TextField frais;

    @FXML
    private TextField recherche;

    @FXML
    private TableView<Colis> table;

    @FXML
    private TextField type;

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
        String query = "insert into colis(description,type,prix) values(?,?,?)";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            st.setString(1,description.getText());
            st.setString(2,type.getText());
            st.setString(3, frais.getText());

            st.executeUpdate();
            clear();
            showClient();
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnModifier(ActionEvent event) {
        String update = "update colis set description = ?, type = ?, prix = ? where idColis=?";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(update);
            st.setString(1,description.getText());
            st.setString(2,type.getText());
            st.setString(3, frais.getText());
            st.setString(4, String.valueOf(idColis));
            st.executeUpdate();
            clear();
            showClient();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // nettoyer les champs du formulaire
    void clear(){
        description.setText(null);
        frais.setText(null);
        type.setText(null);
    }

    @FXML
    void btnSupprimer(ActionEvent event) {
        String delete = "delete from colis where idColis = ?";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(delete);
            st.setInt(1,idColis);
            st.executeUpdate();
            clear();
            showClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void getData(MouseEvent event) {
        Colis politique = table.getSelectionModel().getSelectedItem(); // un objet qui recupere l'element selectionner du tableau
        idColis = politique.getIdColis();
        description.setText(politique.getDescription());
        type.setText(politique.getType());
        frais.setText(String.valueOf(politique.getPrixFrais()));

    }

    public ObservableList<Colis> getPolitique(){
        ObservableList<Colis> politiques = FXCollections.observableArrayList();

        String query = "select * from colis";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Colis st = new Colis();
                st.setIdColis(rs.getInt("IdColis"));
                st.setDescription(rs.getString("description"));
                st.setType(rs.getString("type"));
                st.setPrixFrais(Integer.parseInt(rs.getString("prix")));

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
        ObservableList<Colis> list = getPolitique();
        table.setItems(list);
        ColID.setCellValueFactory(new PropertyValueFactory<Colis,String>("idColis"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<Colis,String>("description"));
        ColFrais.setCellValueFactory(new PropertyValueFactory<Colis, String>("prixFrais"));
        ColType.setCellValueFactory(new PropertyValueFactory<Colis, String>("type"));
    }
}
