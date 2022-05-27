package com.example.spotify;
import java.sql.*;

public class Base {
    static Connection conexion() throws SQLException {
        Connection conexion2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/Spotify", "root",
                "dbrootpass");

        return conexion2;
    }
}
