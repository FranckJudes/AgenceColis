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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class EnvoieController implements Initializable {


    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private TextField AgeClient;

    @FXML
    private TextField AgeDes;

    @FXML
    private ComboBox<String> CheckboxClient;

    @FXML
    private ComboBox<String> CheckboxColis;

    @FXML
    private ComboBox<String> CheckboxPersonnel;

    @FXML
    private TextArea DescriptionColis;

    @FXML
    private TextField PrenomClient;

    int identifiantClient = 0;
    int identifiantColis = 0;
    int identifiantPersonnel = 0;
    @FXML
    private TextField PrenomPersonnel;

    @FXML
    private TextField agenceDestination;

    @FXML
    private TextField nomClient;

    @FXML
    private TextField nomDes;

    @FXML
    private TextField nomPersonnel;

    @FXML
    private TextField permis;

    @FXML
    private TextField prenomDes;

    @FXML
    private TextField prix;

    @FXML
    private TextField professionClient;

    @FXML
    private TextField professionDes;

    @FXML
    private TextField service;

    @FXML
    private TextField typecolis;

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
            Parent garent = FXMLLoader.load(getClass().getResource("HistoriquEnvois.fxml"));
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene s =  new Scene(garent);
            window.setScene(s);
            window.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnEnvoyer(ActionEvent event) {
        String statut = "envoyer";
            if(nomDes.getText().equals("") || prenomDes.getText().equals("") || professionDes.getText().equals("")
                || agenceDestination.getText().equals("") || AgeDes.getText().equals("")
            ){
                Alert alert =  new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setContentText("Veuillez remplir tout les champs :)");
                alert.show();
            }else{
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String dd = s.format(date);
                Random random = new Random();
                int nb;
                nb = 1000+random.nextInt(10000-1000);


                String query = "insert into envois (CNI_Client,CNI_Personnel,idColis,dateExpedition,nomDes,prenomDes,professionDes,ageDes,agenceDestination,codeReception,statut) values(?,?,?,?,?,?,?,?,?,?,?)";
                conn = DBConnexion.getCon();
                try {
                    st = conn.prepareStatement(query);
                    st.setString(1, String.valueOf(identifiantClient));
                    st.setString(2, String.valueOf(identifiantPersonnel));
                    st.setString(3, String.valueOf(identifiantColis));
                    st.setString(4, dd);
                    st.setString(5,nomDes.getText());
                    st.setString(6,prenomDes.getText());
                    st.setString(7,professionDes.getText());
                    st.setString(8,AgeDes.getText());
                    st.setString(9,agenceDestination.getText());
                    st.setString(10, String.valueOf(nb));
                    st.setString(11, statut);
                    st.executeUpdate();
                    clear();
                    Alert alert1 =  new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Alert");
                    alert1.setContentText("Colis a ete envoye avec success");
                    alert1.show();

                    Alert alert =  new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setContentText("Code de reception du Colis est  "+nb);
                    alert.show();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    void clear(){
        agenceDestination.setText(null);
        nomDes.setText(null);
        prenomDes.setText(null);
        professionDes.setText(null);
        AgeDes.setText(null);
    }

    @FXML
    void getDataClient(ActionEvent event) throws SQLException {
        String politique = CheckboxClient.getSelectionModel().getSelectedItem();
        identifiantClient = Integer.parseInt(politique);
        String query = "select * from client where CNI_Client =?";
        conn = DBConnexion.getCon();
        st = conn.prepareStatement(query);
        st.setString(1, politique);
        rs = st.executeQuery();
        while (rs.next()) {
            String n = rs.getString("nom");
            String p = rs.getString("prenom");
            String s = rs.getString("age");
            String a = rs.getString("profession");
            nomClient.setText(n);
            PrenomClient.setText(p);
            AgeClient.setText(a);
            professionClient.setText(s);

        }

    }

    @FXML
    void getDataColis(ActionEvent event) throws SQLException {
        String politique = CheckboxColis.getSelectionModel().getSelectedItem();
        identifiantColis = Integer.parseInt(politique);
        String query = "select * from colis where idColis =?";
        conn = DBConnexion.getCon();
        st = conn.prepareStatement(query);
        st.setString(1, politique);
        rs = st.executeQuery();
        while (rs.next()) {
            String n = rs.getString("description");
            String p = rs.getString("type");
            String s = rs.getString("prix");

            DescriptionColis.setText(n);
            typecolis.setText(p);
            prix.setText(s);

        }
    }

    @FXML
    void getDataMedicament(MouseEvent event) {

    }

    @FXML
    void getDataPersonnel(ActionEvent event) throws SQLException {
        String politique = CheckboxPersonnel.getSelectionModel().getSelectedItem();
        identifiantPersonnel = Integer.parseInt(politique);
        String query = "select * from personnel where CNI_Personnel =?";
        conn = DBConnexion.getCon();
        st = conn.prepareStatement(query);
        st.setString(1, politique);
        rs = st.executeQuery();
        while (rs.next()) {
            String n = rs.getString("nom");
            String p = rs.getString("prenom");
            String s = rs.getString("nomService");
            String t = rs.getString("typePermis");

            nomPersonnel.setText(n);
            PrenomPersonnel.setText(p);
            service.setText(s);
            permis.setText((t));

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showClient();
    }
    public void showClient() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list2 = FXCollections.observableArrayList();
        ObservableList<String> list3 = FXCollections.observableArrayList();

        String query = "select * from client";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Client st = new Client();
                int ID = rs.getInt("CNI_Client");
                String IDString = Integer.toString(ID);
                String nom = rs.getString("nom");
                st.setCNI_Client(rs.getInt("CNI_Client"));
                st.setNom(rs.getString("nom"));
                st.setPrenom(rs.getString("prenom"));
                st.setAge(rs.getString("age"));
                st.setProfession(rs.getString("profession"));
                list.add(IDString);

            }

        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }
        String queryP = "select * from personnel";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(queryP);
            rs = st.executeQuery();
            while (rs.next()) {
                Client st = new Client();
                int ID = rs.getInt("CNI_Personnel");
                String IDString = Integer.toString(ID);
                String nom = rs.getString("nom");
                st.setCNI_Client(rs.getInt("CNI_Personnel"));
                st.setNom(rs.getString("nom"));
                st.setPrenom(rs.getString("prenom"));
                st.setAge(rs.getString("nomService"));
                st.setProfession(rs.getString("typePermis"));
                list3.add(IDString);

            }

        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }
        String query2 = "select * from colis";
        conn = DBConnexion.getCon();
        try {
            st = conn.prepareStatement(query2);
            rs = st.executeQuery();
            while (rs.next()) {
                Colis st = new Colis();
                int ID = rs.getInt("idColis");
                String IDString = Integer.toString(ID);
                st.setType(rs.getString("type"));
                st.setPrixFrais(Integer.parseInt(rs.getString("prix")));
                st.setDescription(rs.getString("description"));
                st.setIdColis(rs.getInt("prix"));
                list2.add(IDString);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Affecter tout les Client dans le Select
        CheckboxClient.setItems(null);
        CheckboxClient.setItems(list);
        // Afficher tous lec Colis dans le Select
        CheckboxColis.setItems(null);
        CheckboxColis.setItems(list2);
        CheckboxPersonnel.setItems(null);
        CheckboxPersonnel.setItems(list3);
    }


}
