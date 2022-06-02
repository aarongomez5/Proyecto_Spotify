package com.example.spotify;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private HBox inicio;

    @FXML
    private HBox parati;

    @FXML
    private HBox reciente;

    @FXML
    private HBox megusta;

    @FXML
    private HBox album;

    @FXML
    private HBox artistas;

    @FXML
    private HBox podcast;

    @FXML
    private HBox playlist;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Button premium;

    private FXMLLoader fxmlLoader;

    @FXML
    private Label nombreusu;

    @FXML
    private Label parati1;

    @FXML
    private Label parati2;

    @FXML
    private Label parati3;

    @FXML
    private Label parati4;

    @FXML
    private Label parati5;

    @FXML
    private Label escuchado1;

    @FXML
    private Label escuchado3;

    @FXML
    private Label escuchado2;

    @FXML
    private Label escuchado4;

    @FXML
    private Label escuchado5;

    @FXML
    private Label favorita1;

    @FXML
    private Label favorita3;

    @FXML
    private Label favorita2;

    @FXML
    private Label favorita4;

    @FXML
    private Label favorita5;

    @FXML
    private Label album1;

    @FXML
    private Label album3;

    @FXML
    private Label album2;

    @FXML
    private Label album4;

    @FXML
    private Label album5;

    @FXML
    private Label artistas1;

    @FXML
    private Label artistas3;

    @FXML
    private Label artistas2;

    @FXML
    private Label artistas4;

    @FXML
    private Label artistas5;

    @FXML
    private Label podcast1;

    @FXML
    private Label podcast3;

    @FXML
    private Label podcast2;

    @FXML
    private Label podcast4;

    @FXML
    private Label podcast5;

    @FXML
    private Label titplay;

    @FXML
    private Label artplay;

    @FXML
    private ImageView corazonvacio;

    @FXML
    private HBox reproductor;

    @FXML
    private Label tituloplaylist;

    @FXML
    private Label introduceplaylist;

    @FXML
    private TextField textfieldplaylist;

    @FXML
    private Button botonplaylist;

    @FXML
    private TableView<Playlist> tablaplaylist;

    @FXML
    private TableColumn<Playlist, String> col_playlist;

    ObservableList <Playlist> listplay;

    @FXML
    private ImageView anuncio;

    @FXML
    private Pane anyadirplaylist;

    @FXML
    private TableView<Album> tablaalbum;

    @FXML
    private TableColumn<Album, Integer> col_anyoalbum;

    @FXML
    private TableColumn<Album, String> col_tituloalbum;

    ObservableList <Album> albumtab;

    @FXML
    private TableView<Artista> tablaartistas;

    @FXML
    private TableColumn<Artista, String> col_nombreartista;

    ObservableList <Artista> artistatab;

    @FXML
    private TableView<Cancion> tablacancion;

    @FXML
    private TableColumn<Cancion, String> col_cancion;

    ObservableList <Cancion> canciontab;

    ControllerInicioSesion usuusu;
    Stage stage = new Stage();


    @FXML
    void premium(ActionEvent event) {
        premium.setText("Usuario premium");
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("premium.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Premium");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pasosu(String text, Stage stage, ControllerInicioSesion controllerInicioSesion) {
        this.usuusu = controllerInicioSesion;
        this.stage = stage;
        nombreusu.setText(text);

        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.titulo FROM cancion c INNER JOIN guarda_cancion gc ON gc.cancion_id = c.id INNER JOIN usuario u ON gc.usuario_id = u.id WHERE u.username LIKE '" + nombreusu.getText() + "'");
            int i = 0;
            while(rs.next()){
                String nombre = rs.getString("titulo");
                if(i == 0){
                    favorita1.setText(nombre);
                }else if(i == 1){
                    favorita2.setText(nombre);
                }else if(i == 2){
                    favorita3.setText(nombre);
                }else if(i == 3){
                    favorita4.setText(nombre);
                }else if(i == 4){
                    favorita5.setText(nombre);
                }
                i++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void senyalado(MouseEvent event) {
        event.getPickResult().getIntersectedNode().setStyle("-fx-border-color: #121212 #121212 #121212 green; -fx-border-width: 5;");
    }

    @FXML
    void sinsenyalar(MouseEvent event) {
        inicio.setStyle("-fx-border-color: none;");
        parati.setStyle("-fx-border-color: none;");
        reciente.setStyle("-fx-border-color: none;");
        megusta.setStyle("-fx-border-color: none;");
        album.setStyle("-fx-border-color: none;");
        artistas.setStyle("-fx-border-color: none;");
        podcast.setStyle("-fx-border-color: none;");
    }

    @FXML
    void click(MouseEvent event) {
        String opcion = event.getPickResult().getIntersectedNode().getId();
        switch (opcion){
            case "inicio":
                scrollpane.setVisible(true);
                tablaartistas.setVisible(false);
                tablacancion.setVisible(false);
                tablaalbum.setVisible(false);
                scrollpane.setVvalue(0.0);
                break;
            case "parati":
                scrollpane.setVisible(true);
                tablaartistas.setVisible(false);
                tablacancion.setVisible(false);
                tablaalbum.setVisible(false);
                scrollpane.setVvalue(0);
                break;
            case "reciente":
                scrollpane.setVisible(true);
                tablaartistas.setVisible(false);
                tablacancion.setVisible(false);
                tablaalbum.setVisible(false);
                scrollpane.setVvalue(0.23);
                break;
            case "megusta":
                scrollpane.setVisible(true);
                tablaartistas.setVisible(false);
                tablacancion.setVisible(false);
                tablaalbum.setVisible(false);
                scrollpane.setVvalue(0.46);
                break;
            case "album":
                scrollpane.setVisible(true);
                tablaartistas.setVisible(false);
                tablacancion.setVisible(false);
                tablaalbum.setVisible(false);
                scrollpane.setVvalue(0.69);
                break;
            case "artistas":
                scrollpane.setVisible(true);
                tablaartistas.setVisible(false);
                tablacancion.setVisible(false);
                tablaalbum.setVisible(false);
                scrollpane.setVvalue(0.92);
                break;
            case "podcast":
                scrollpane.setVisible(true);
                tablaartistas.setVisible(false);
                tablacancion.setVisible(false);
                tablaalbum.setVisible(false);
                scrollpane.setVvalue(1.1);
                break;
        }
    }

    void titulos() throws SQLException{
        try{
            int numcan;
            for (int i = 0; i < 10; i++) {
                numcan = (int) (Math.random() * (359 - 1) + 1);
                Statement stmt = Base.conexion().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT titulo FROM cancion WHERE id LIKE '" + numcan + "'");
                if(rs.next()){
                    String nombre = rs.getString("titulo");
                    if(i == 0){
                        parati1.setText(nombre);
                    }else if(i == 1){
                        parati2.setText(nombre);
                    }else if(i == 2){
                        parati3.setText(nombre);
                    }else if(i == 3){
                        parati4.setText(nombre);
                    }else if(i == 4){
                        parati5.setText(nombre);
                    }else if(i == 5){
                        escuchado1.setText(nombre);
                    }else if(i == 6){
                        escuchado2.setText(nombre);
                    }else if(i == 7){
                        escuchado3.setText(nombre);
                    }else if(i == 8){
                        escuchado4.setText(nombre);
                    }else if(i == 9){
                        escuchado5.setText(nombre);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void tituloalbum() throws SQLException{
        try{
            int numcan;
            for (int i = 0; i < 5; i++) {
                numcan = (int) (Math.random() * (26 - 1) + 1);
                Statement stmt = Base.conexion().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT titulo FROM album WHERE id = '" + numcan + "'");
                if(rs.next()){
                    String nombre = rs.getString("titulo");
                    if(i == 0){
                        album1.setText(nombre);
                    }else if(i == 1){
                        album2.setText(nombre);
                    }else if(i == 2){
                        album3.setText(nombre);
                    }else if(i == 3){
                        album4.setText(nombre);
                    }else if(i == 4){
                        album5.setText(nombre);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void nombreartista() throws SQLException{
        try{
            int numcan;
            for (int i = 0; i < 5; i++) {
                numcan = (int) (Math.random() * (150 - 1) + 1);
                Statement stmt = Base.conexion().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT nombre FROM artista WHERE id = '" + numcan + "'");
                if(rs.next()){
                    String nombre = rs.getString("nombre");
                    if(i == 0){
                        artistas1.setText(nombre);
                    }else if(i == 1){
                        artistas2.setText(nombre);
                    }else if(i == 2){
                        artistas3.setText(nombre);
                    }else if(i == 3){
                        artistas4.setText(nombre);
                    }else if(i == 4){
                        artistas5.setText(nombre);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void titulopodcast() throws SQLException{
        try{
            int numcan;
            for (int i = 0; i < 5; i++) {
                numcan = (int) (Math.random() * (86 - 1) + 1);
                Statement stmt = Base.conexion().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT titulo FROM capitulo WHERE id = '" + numcan + "'");
                if(rs.next()){
                    String nombre = rs.getString("titulo");
                    if(i == 0){
                        podcast1.setText(nombre);
                    }else if(i == 1){
                        podcast2.setText(nombre);
                    }else if(i == 2){
                        podcast3.setText(nombre);
                    }else if(i == 3){
                        podcast4.setText(nombre);
                    }else if(i == 4){
                        podcast5.setText(nombre);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void reproducir(MouseEvent event) {
        String opcion1 = event.getPickResult().getIntersectedNode().getId();
        switch (opcion1){
            case "fotoparati1":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(parati1.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoparati2":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(parati2.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoparati3":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(parati3.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoparati4":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(parati4.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoparati5":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(parati5.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoescuchado1":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(escuchado1.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoescuchado2":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(escuchado2.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoescuchado3":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(escuchado3.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoescuchado4":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(escuchado4.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoescuchado5":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(escuchado5.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotofavoritas1":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(favorita1.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotofavoritas2":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(favorita2.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotofavoritas3":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(favorita3.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotofavoritas4":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(favorita4.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotofavoritas5":
                reproductor.setVisible(true);
                corazonvacio.setVisible(true);
                titplay.setText(favorita5.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String art = rs.getString("nombre");
                        artplay.setText(art);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoalbum1":
                scrollpane.setVisible(false);
                tablaalbum.setVisible(true);
                albumtab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT anyo, titulo FROM album");
                    while(rs.next()){
                        int any = rs.getInt("anyo");
                        String tit = rs.getString("titulo");
                        Album a = new Album(any, tit);
                        albumtab.add(a);
                    }
                    tablaalbum.setItems(albumtab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoalbum2":
                int distinto1 = 0;
                scrollpane.setVisible(false);
                tablaalbum.setVisible(true);
                albumtab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT anyo, titulo FROM album");
                    while(rs.next()){
                        int any = rs.getInt("anyo");
                        String tit = rs.getString("titulo");
                        Album a = new Album(any, tit);
                        albumtab.add(a);
                    }
                    tablaalbum.setItems(albumtab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoalbum3":
                int distinto2 = 1;
                scrollpane.setVisible(false);
                tablaalbum.setVisible(true);
                albumtab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT anyo, titulo FROM album");
                    while(rs.next()){
                        int any = rs.getInt("anyo");
                        String tit = rs.getString("titulo");
                        Album a = new Album(any, tit);
                        albumtab.add(a);
                    }
                    tablaalbum.setItems(albumtab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoalbum4":
                int distinto3 = 2;
                scrollpane.setVisible(false);
                tablaalbum.setVisible(true);
                albumtab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT anyo, titulo FROM album");
                    while(rs.next()){
                        int any = rs.getInt("anyo");
                        String tit = rs.getString("titulo");
                        Album a = new Album(any, tit);
                        albumtab.add(a);
                    }
                    tablaalbum.setItems(albumtab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoalbum5":
                int distinto4 = 3;
                scrollpane.setVisible(false);
                tablaalbum.setVisible(true);
                albumtab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT anyo, titulo FROM album");
                    while(rs.next()){
                        int any = rs.getInt("anyo");
                        String tit = rs.getString("titulo");
                        Album a = new Album(any, tit);
                        albumtab.add(a);
                    }
                    tablaalbum.setItems(albumtab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoartistas1":
                scrollpane.setVisible(false);
                tablaartistas.setVisible(true);
                artistatab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT nombre FROM artista");
                    while(rs.next()){
                        String nom = rs.getString("nombre");
                        Artista a = new Artista(nom);
                        artistatab.add(a);
                    }
                    tablaartistas.setItems(artistatab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoartistas2":
                int distinto5 = 4;
                scrollpane.setVisible(false);
                tablaartistas.setVisible(true);
                artistatab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT nombre FROM artista");
                    while(rs.next()){
                        String nom = rs.getString("nombre");
                        Artista a = new Artista(nom);
                        artistatab.add(a);
                    }
                    tablaartistas.setItems(artistatab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoartistas3":
                int distinto6 = 5;
                scrollpane.setVisible(false);
                tablaartistas.setVisible(true);
                artistatab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT nombre FROM artista");
                    while(rs.next()){
                        String nom = rs.getString("nombre");
                        Artista a = new Artista(nom);
                        artistatab.add(a);
                    }
                    tablaartistas.setItems(artistatab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoartistas4":
                int distinto7 = 6;
                scrollpane.setVisible(false);
                tablaartistas.setVisible(true);
                artistatab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT nombre FROM artista");
                    while(rs.next()){
                        String nom = rs.getString("nombre");
                        Artista a = new Artista(nom);
                        artistatab.add(a);
                    }
                    tablaartistas.setItems(artistatab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotoartistas5":
                int distinto8 = 7;
                scrollpane.setVisible(false);
                tablaartistas.setVisible(true);
                artistatab.clear();
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT nombre FROM artista");
                    while(rs.next()){
                        String nom = rs.getString("nombre");
                        Artista a = new Artista(nom);
                        artistatab.add(a);
                    }
                    tablaartistas.setItems(artistatab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotopodcast1":
                reproductor.setVisible(true);
                corazonvacio.setVisible(false);
                titplay.setText(podcast1.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT p.titulo FROM podcast p INNER JOIN capitulo c ON c.podcast_id = p.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String pod = rs.getString("titulo");
                        artplay.setText(pod);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotopodcast2":
                reproductor.setVisible(true);
                corazonvacio.setVisible(false);
                titplay.setText(podcast2.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT p.titulo FROM podcast p INNER JOIN capitulo c ON c.podcast_id = p.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String pod = rs.getString("titulo");
                        artplay.setText(pod);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotopodcast3":
                reproductor.setVisible(true);
                corazonvacio.setVisible(false);
                titplay.setText(podcast3.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT p.titulo FROM podcast p INNER JOIN capitulo c ON c.podcast_id = p.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String pod = rs.getString("titulo");
                        artplay.setText(pod);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotopodcast4":
                reproductor.setVisible(true);
                corazonvacio.setVisible(false);
                titplay.setText(podcast4.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT p.titulo FROM podcast p INNER JOIN capitulo c ON c.podcast_id = p.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String pod = rs.getString("titulo");
                        artplay.setText(pod);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "fotopodcast5":
                reproductor.setVisible(true);
                corazonvacio.setVisible(false);
                titplay.setText(podcast5.getText());
                try{
                    Statement stmt = Base.conexion().createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT p.titulo FROM podcast p INNER JOIN capitulo c ON c.podcast_id = p.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
                    if(rs.next()){
                        String pod = rs.getString("titulo");
                        artplay.setText(pod);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @FXML
    void lista(MouseEvent event) {
        tablacancion.setVisible(false);
        tablaartistas.setVisible(false);
        tablaalbum.setVisible(false);
        anuncio.setVisible(false);
        anyadirplaylist.setVisible(true);
        tituloplaylist.setVisible(true);
        introduceplaylist.setVisible(true);
        textfieldplaylist.setVisible(true);
        botonplaylist.setVisible(true);
    }

    @FXML
    void anyadirplaylist(ActionEvent event) {
        tablaartistas.setVisible(false);
        tablaalbum.setVisible(false);
        tablacancion.setVisible(false);
        anuncio.setVisible(true);
        anyadirplaylist.setVisible(false);
        tituloplaylist.setVisible(false);
        introduceplaylist.setVisible(false);
        textfieldplaylist.setVisible(false);
        botonplaylist.setVisible(false);
        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM usuario WHERE username LIKE '" + nombreusu.getText() + "'");
            LocalDate fecha = LocalDate.now();
            if(rs.next()){
                int usuarioid = rs.getInt("id");
                stmt.executeUpdate("INSERT INTO playlist(titulo, numero_canciones, fecha_creacion, usuario_id)" +
                        "Values('" + textfieldplaylist.getText() + "','" + 0 + "','" + fecha + "','" + usuarioid + "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listarep(MouseEvent event) {
        tablaplaylist.setVisible(true);
        listplay.clear();
        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.titulo FROM playlist p INNER JOIN sigue_playlist sp ON sp.playlist_id = p.id WHERE sp.usuario_id = (SELECT id FROM usuario WHERE username LIKE '" + nombreusu.getText() + "')");
            while(rs.next()){
                String pl = rs.getString("titulo");
                Playlist p = new Playlist(pl);
                listplay.add(p);
            }
            tablaplaylist.setItems(listplay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void favvacio(MouseEvent event) {
        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.titulo FROM cancion c INNER JOIN guarda_cancion gc ON gc.cancion_id = c.id INNER JOIN usuario u ON gc.usuario_id = u.id WHERE gc.cancion_id = (SELECT id FROM cancion WHERE titulo LIKE  '" + titplay.getText() + "') AND gc.usuario_id = (SELECT id FROM usuario WHERE username LIKE  '" + nombreusu.getText() + "')");
            if(rs.next()){
                stmt.executeUpdate("DELETE FROM guarda_cancion WHERE cancion_id = (SELECT id FROM cancion WHERE titulo LIKE  '" + titplay.getText() + "') AND usuario_id = (SELECT id FROM usuario WHERE username LIKE  '" + nombreusu.getText() + "')");
                Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/ic_love.png")));
                corazonvacio.setImage(img);
            } else {
                stmt.executeUpdate("INSERT INTO guarda_cancion(usuario_id, cancion_id)" +
                                        "Values((SELECT id FROM usuario WHERE username LIKE '" + nombreusu.getText() + "'),(SELECT id FROM cancion WHERE titulo LIKE '" + titplay.getText() + "'))");
                Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/ic_love lleno.png")));
                corazonvacio.setImage(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void artistas_click(MouseEvent event) {
        Artista artista1 = tablaartistas.getSelectionModel().getSelectedItem();
        System.out.println(artista1.getNom_artista());
        tablacancion.setVisible(true);
        canciontab.clear();
        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.titulo FROM cancion c INNER JOIN album al ON al.id = c.album_id INNER JOIN artista art ON art.id = al.artista_id WHERE art.nombre LIKE '" + artista1.getNom_artista() + "'");
            while(rs.next()){
                String pl = rs.getString("titulo");
                Cancion can = new Cancion(pl);
                canciontab.add(can);
            }
            tablacancion.setItems(canciontab);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancion_click(MouseEvent event) {
        Cancion cancion1 = tablacancion.getSelectionModel().getSelectedItem();
        reproductor.setVisible(true);
        corazonvacio.setVisible(true);
        titplay.setText(cancion1.getNombre_cancion());
        try{
            Statement stmt = Base.conexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT a.nombre FROM artista a INNER JOIN album al ON al.artista_id = a.id INNER JOIN cancion c ON c.album_id = al.id WHERE c.titulo LIKE '" + titplay.getText() + "'");
            if(rs.next()){
                String art = rs.getString("nombre");
                artplay.setText(art);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            titulos();
            tituloalbum();
            nombreartista();
            titulopodcast();
            listplay = FXCollections.observableArrayList();
            col_playlist.setCellValueFactory(new PropertyValueFactory<>("tit_playlist"));
            albumtab = FXCollections.observableArrayList();
            col_anyoalbum.setCellValueFactory(new PropertyValueFactory<>("anyo_album"));
            col_tituloalbum.setCellValueFactory(new PropertyValueFactory<>("tit_album"));
            artistatab = FXCollections.observableArrayList();
            col_nombreartista.setCellValueFactory(new PropertyValueFactory<>("nom_artista"));
            canciontab = FXCollections.observableArrayList();
            col_cancion.setCellValueFactory(new PropertyValueFactory<>("nombre_cancion"));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
