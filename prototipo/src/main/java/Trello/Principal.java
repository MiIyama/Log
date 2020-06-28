package Trello;

import Log.CriarLog;
import java.io.IOException;
import java.text.ParseException;

public class Principal {
    public static void main(String[] args) throws IOException{
         Tarefas t = new Tarefas();
         t.getListaTarefas();
         t.exibirMensagem();
    }
}
