package easy.office;

import Log.CriarLog;
import conexao.ConnectionFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oshi.SystemInfo;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class InfoSO {

    SystemInfo si = new SystemInfo();
    OperatingSystem so = si.getOperatingSystem();
    public String nome = so.toString();

    public void adiciona() throws IOException{
        
         CriarLog criarLog = new CriarLog();
         Connection connection = new ConnectionFactory().getConnection();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String sql = "INSERT INTO SO values (?, ?, ?)";
         try {
             stmt = connection.prepareStatement(sql);
             stmt.setInt(1, 11);
             stmt.setString(2, nome);
             stmt.setInt(3, 1);
             stmt.execute();
             stmt.close();
         } catch (SQLException e) {
              criarLog.registrarLog(e.toString());
             throw new RuntimeException(e);
         }
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        InfoSO i = new InfoSO();
        i.adiciona();
    }
    //Criar objeto e usar *label*.setText(*objeto*.so.toString());
}
