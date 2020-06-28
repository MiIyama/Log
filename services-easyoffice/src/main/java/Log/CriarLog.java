/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

/**
 *
 * @author INGRIDNAOMIIYAMA
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriarLog {

    private String caminho = System.getProperty("user.home")+"\\EOServices_Logs\\";
    private String nomeLog;
    private String versao = "1.0";
    private int dia, mes, ano;

    private Integer hora;
    private Integer minutos;
    private Integer segundos;

    public void criarArquivo() throws IOException {

        File diretorio = new File(caminho);
        diretorio.mkdir();

        Calendar cal = Calendar.getInstance();

        dia = cal.get(Calendar.DATE);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        nomeLog = String.format("EasyOffice_%d%d%d.txt", ano, mes, dia);

        File arquivo = new File(caminho + File.separator + nomeLog);

        if (arquivo.exists()) {
            System.out.println(String.format("O arquivo %s existe", nomeLog));

        } else {
            System.out.println(String.format("O arquivo %s não existe", nomeLog));
            System.out.println(String.format("Criando arquivo %s em %s", nomeLog, caminho));
            arquivo.createNewFile();
            System.out.println(String.format("Arquivo %s criado em %s", nomeLog, caminho));
           
            
            System.out.println(caminho + File.separator + nomeLog);

        FileWriter fw = new FileWriter((caminho + File.separator + nomeLog), true);
        BufferedWriter bw = new BufferedWriter(fw);

        

        bw.write(String.format("EasyOffice 2020 - Versão %s", versao));
        bw.newLine();
        bw.newLine();
        bw.write(String.format("Criação do log em: %d/%d/%d", dia, mes, ano, versao));
        bw.newLine();
        bw.write("--------------------Services - EasyOffice--------------------");
        bw.close();

        }

    }

 
    public void registrarLog(String msgLog) throws IOException{
        criarArquivo();
        try {
            FileWriter fw1 = new FileWriter((caminho + File.separator + nomeLog), true);
            BufferedWriter bw1 = new BufferedWriter(fw1);

            hora = LocalDateTime.now().getHour();
            minutos = LocalDateTime.now().getMinute();
            segundos = LocalDateTime.now().getSecond();

            System.out.print(String.format("%d/%d/%d %d:%d:%d -", dia, mes, ano, hora, minutos, segundos));
            bw1.write(String.format("%d/%d/%d %d:%d:%d - %s", dia, mes, ano, hora, minutos, segundos, msgLog));
            bw1.newLine();

            bw1.close();
        } catch (IOException ex) {
            System.out.println("Erro ao abrir o arquivo");

        }

    }
    
        public static void main(String[] args) throws IOException {
        CriarLog log = new CriarLog();
        log.registrarLog("Teste");
    }
}
