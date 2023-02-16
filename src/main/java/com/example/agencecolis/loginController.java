package com.example.agencecolis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginController {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    @FXML
    private TextField password;

    @FXML
    private TextField user;

    @FXML
    void login(ActionEvent event) {
        if (user.getText().equals("")  || password.getText().equals("")){
            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setContentText("Veuillez remplir tous les champs :)");
            alert.show();
        }else {
            conn = DBConnexion.getCon();
            String query = "select * from admin where username=? and password=?";
            try {
                st = conn.prepareStatement(query);
                st.setString(1,user.getText());
                st.setString(2,password.getText());
                rs = st.executeQuery();
                if (rs.next()){
                    Parent garent = FXMLLoader.load(getClass().getResource("Client.fxml"));
                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Scene s =  new Scene(garent);
                    window.setScene(s);
                    window.show();
                }else{
                    Alert alert =  new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setContentText("Username ou Mot de passe incorrect");
                    alert.show();
                    user.setText(null);
                    password.setText(null);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
