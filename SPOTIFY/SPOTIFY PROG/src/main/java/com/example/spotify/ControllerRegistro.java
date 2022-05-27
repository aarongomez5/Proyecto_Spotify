package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ControllerRegistro {

    @FXML
    private PasswordField contraseña;

    @FXML
    private PasswordField confcontraseña;

    @FXML
    private TextField usuario;

    @FXML
    private TextField email;

    @FXML
    private TextField pais;

    @FXML
    private TextField cp;

    @FXML
    private DatePicker fecha;

    @FXML
    private TextField genero;

    @FXML
    private Button registro;

    @FXML
    void botonregistro(ActionEvent event) {
        if (contraseña.getText().isEmpty() || confcontraseña.getText().isEmpty() || usuario.getText().isEmpty() || email.getText().isEmpty() || pais.getText().isEmpty() || cp.getText().isEmpty() || genero.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error");
            alerta.setContentText("Hay algún campo vacío.");
            alerta.show();
        } else if(!contraseña.getText().equals(confcontraseña.getText())) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error");
            alerta.setContentText("El campo contraseña y confirmar contraseña no coinciden.");
            alerta.show();
        } else {
            try {
                Statement stmt = Base.conexion().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT username FROM usuario WHERE username = '" + usuario.getText() + "'");
                LocalDate alta = LocalDate.now();
                if (rs.next()) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Error");
                    alerta.setContentText("El nombre de usuario ya existe.");
                    alerta.show();
                } else {
                    stmt.executeUpdate("INSERT INTO usuario(username, password, email, genero, fecha_nacimiento, pais, codigo_postal)" +
                            "Values('" + usuario.getText() + "','" + contraseña.getText() + "','" + email.getText() + "','" + genero.getText() + "','" + fecha.getValue() + "','" + pais.getText() + "','" + cp.getText() + "')");
                    try {
                        Statement stmt1 = Base.conexion().createStatement();
                        ResultSet rs1 = stmt1.executeQuery("SELECT id FROM usuario WHERE username = '" + usuario.getText() + "'");
                        if(rs1.next()){
                            int anuncios = (int) (Math.random() * (1000 - 200) + 200);
                            int iden = rs1.getInt("id");
                            stmt1.executeUpdate("INSERT INTO free(fecha_revision, tiempo_publicidad, usuario_id)" +
                                    "Values('" + alta + "','" + anuncios + "','" + iden + "')");
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                Base.conexion().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) registro.getScene().getWindow();
            stage.close();
        }
    }
}
