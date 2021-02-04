package server.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Config {

    public static int PORT = 8000;
    private static final String PUBLIC_FILES_DIRECTORY = System.getProperty("user.dir") + "/public/assets/";
    public static String API_URL = "http://localhost:" + PORT;


    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        try (FileInputStream f = new FileInputStream("src/config/db.properties")) {

            // load the properties file
            Properties pros = new Properties();
            pros.load(f);

            // assign migrations.sql parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public static int getPORT() {
        return PORT;
    }

    public static String getPublicFilesDirectory() {
        return PUBLIC_FILES_DIRECTORY;
    }
}