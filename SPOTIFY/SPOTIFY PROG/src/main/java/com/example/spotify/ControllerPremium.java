package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ControllerPremium {

    @FXML
    private TextField usuario;

    @FXML
    private Button primero;

    void botones(ActionEvent event){
        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usuario_id FROM free WHERE usuario_id = (SELECT id FROM usuario WHERE username = '" + usuario.getText() + "')");
            LocalDate fecha = LocalDate.now();
            LocalDate renovacion = fecha.plusMonths(1);
            if(rs.next()){
                int usuid = rs.getInt("usuario_id");
                stmt.executeUpdate("DELETE FROM free WHERE usuario_id = '" + usuid + "'");
                stmt.executeUpdate("INSERT INTO premium(fecha_renovación, usuario_id)" +
                                       "Values('" + renovacion + "','" + usuid + "')");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) primero.getScene().getWindow();
        stage.close();
    }

    @FXML
    void primero(ActionEvent event) {
        botones(event);
    }

    @FXML
    void segundo(ActionEvent event) {
        botones(event);
    }

    @FXML
    void tercero(ActionEvent event) {
        botones(event);
    }

    @FXML
    void cuarto(ActionEvent event) {
        botones(event);
    }

}