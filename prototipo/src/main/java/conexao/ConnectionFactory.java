package conexao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Log.CriarLog;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Intel
 */
public class ConnectionFactory {
    CriarLog criarLog = new CriarLog();
    public Connection getConnection() throws IOException{
        String usuario = "EasyAdm";
        String senha = "#Gfgrupo2";
        String url = "jdbc:sqlserver://svreasyoffice.database.windows.net:1433;database=bdEasyOffice;"
                + "user= " + usuario + "@svreasyoffice;password=" + senha + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                criarLog.registrarLog(ex.toString());
            }
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            criarLog.registrarLog(e.toString());
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ConnectionFactory a = new ConnectionFactory();
        a.getConnection();
    }
}
