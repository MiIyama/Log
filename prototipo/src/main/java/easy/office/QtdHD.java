package easy.office;

import Log.CriarLog;
import conexao.ConnectionFactory;
import java.io.IOException;
import telegram.SendMessage_1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

public class QtdHD {

//    double hdTotal;
//    double hdDisp;
//    double hdUsado;
    SystemInfo si = new SystemInfo();
    HardwareAbstractionLayer hal = si.getHardware();
    HWDiskStore[] hd = hal.getDiskStores();

    public double hdTotal = hd[0].getSize() / 1024 / 1024 / 1024;
    double hdDisp = ((hd[0].getSize() - hd[0].getWriteBytes()) / 1024 / 1024 / 1024);
    double hdUsado = hd[0].getWriteBytes() / 1024 / 1024 / 1024;

    double porcHd = (hdUsado * 100) / hdTotal;

    public void adiciona() throws IOException{
        CriarLog criarLog = new CriarLog();
        telegram();
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO DISCO_MEMORIA values (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, 11);
            stmt.setDouble(2, hdDisp);
            stmt.setDouble(3, hdTotal);
            stmt.setInt(4, 1);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
             criarLog.registrarLog(e.toString());
            throw new RuntimeException(e);
        }
    }

    public void telegram() throws IOException {
        if (porcHd < 70) {
            SendMessage_1 a = new SendMessage_1();
            a.send("HD disponivel: "+ hdDisp+ "GB de "+hdTotal+ "GB");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        QtdHD q1 = new QtdHD();
        q1.adiciona();
    }
}
