package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerInicioSesion implements Initializable {

    private String reg;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contraseña;

    @FXML
    private Button boton;

    @FXML
    private Label error;

    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    void spotify() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1315, 890);
        HelloController nom = fxmlLoader.getController();
        this.stage.setTitle("Spotify");
        this.stage.setScene(scene);
        nom.pasosu(usuario.getText(), this.stage,this);
        this.stage.show();
    }

    @FXML
    void inicio(ActionEvent event) {
        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, password FROM usuario WHERE username ='" + usuario.getText() + "' AND password ='" + contraseña.getText() + "'");
            if (rs.next()){
                stage = (Stage) boton.getScene().getWindow();
                stage.close();
                spotify();
            }else{error.setVisible(true);}
            Base.conexion().close();
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void registro(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registro.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrarse");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
