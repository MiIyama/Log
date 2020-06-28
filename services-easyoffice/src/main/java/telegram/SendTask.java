package telegram;

import Log.CriarLog;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SendTask {
    
    public void send(String msg) throws IOException {
         CriarLog criarLog = new CriarLog();
        
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //Add Telegram token (given Token is fake)
        String apiToken = "1077364020:AAE6MICtohBqk3uiNjDaFEZ3dmI3z3KlV00";
      
        //Add chatId (given chatId is fake)
        String chatId = "-427567245";

        urlString = String.format(urlString, apiToken, chatId, msg);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
             criarLog.registrarLog(e.toString());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        SendTask a = new SendTask();
        a.send("Hello World");
    }
}

