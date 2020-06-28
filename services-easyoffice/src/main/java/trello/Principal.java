package trello;

import Log.CriarLog;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import telegram.BotEasyOffice;
import telegram.SendTask;

public class Principal {
    public static void main(String[] args) throws ParseException, IOException {
        
        int delay = 0;
        int interval = 900000;
        int interval2 = 10000;
        
        Tarefas t = new Tarefas();
        SendTask st = new SendTask();
        
        t.getListaTarefas();
        t.exibirMensagem();
        
        //Teste de output
        System.out.println(t);
        
        //Timer do aviso 
        Timer time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                 CriarLog criarLog = new CriarLog();
                try {
                    st.send(t.mandarAviso());
                } catch (IOException ex) {
                 
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, delay, interval);
        
        //Timer que envia os dados das tarefas pro banco/Dash
        Timer time2 = new Timer();

        time2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    t.adiciona();
                } catch (ClassNotFoundException ex) {
                    System.out.println("ERRO");
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, delay, interval2);
        

        //Bot
        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();
        BotEasyOffice bot = new BotEasyOffice();

        try {
            telegram.registerBot(bot);
        } catch (TelegramApiRequestException ex) {
            System.out.println("esse?");         
        }
    }
}
