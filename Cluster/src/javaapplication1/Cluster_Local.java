/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nupasd-ufpi
 */
public class Cluster_Local extends JFrame {

    JLabel valor1 = new JLabel("Arquivo Utilizado");
    JLabel valor2 = new JLabel("valor 2");
    JLabel avisos = new JLabel("Status do Processamento: Inativo");

    JButton iniciar = new JButton("Iniciar");
    JLabel mostrarsoma = new JLabel("Resultado do Processamento:");
    JTextField tfsoma = new JTextField();
    JTextField tfvalor1 = new JTextField();
    JTextField tfvalor2 = new JTextField();

    JLabel motrar_resultados = new JLabel("Tempo de Processamento:");
    JTextField resultados = new JTextField();

    Gson gson = new Gson();

    //ArrayList<String> palavrasJSON = new ArrayList<>();
    //ArrayList<String> vezesPalavraJSON = new ArrayList<>();
    String texto = null;
    int codigo;

    //VARIÁVEIS PARA CONTAGEM DE PALAVRAS
    ArrayList<String> palavras = new ArrayList<>();
    ArrayList<String> vezesPalavra = new ArrayList<>();
    
    
    public Cluster_Local() {
        JPanel tela = new JPanel();
        tela.setLayout(null);
        tela.setBackground(Color.white);

        tela.add(motrar_resultados);
        motrar_resultados.setBounds(20, 60, 150, 200);

        tela.add(resultados);
        resultados.setBounds(180, 150, 200, 30);

        tela.add(valor1);
        valor1.setBounds(20, 60, 350, 40);

        tela.add(tfvalor1);
        tfvalor1.setBounds(160, 64, 200, 40);

        tela.add(avisos);
        avisos.setBounds(20, 110, 200, 20);

        tela.add(mostrarsoma);
        mostrarsoma.setBounds(20, 180, 350, 40);

        tela.add(tfsoma);
        tfsoma.setBounds(20, 220, 400, 160);

        tela.add(iniciar);

        iniciar.setBounds(20, 10, 400, 40);
        iniciar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                palavras.clear();
                //palavrasJSON.clear();
                //vezesPalavraJSON.clear();

                Modelo modelo2 = new Modelo();

                JFileChooser arquivo = new JFileChooser();
                arquivo.setMultiSelectionEnabled(true);
                //FileNameExtensionFilter filtroPDF = new FileNameExtensionFilter("Arquivos PDF", "pdf");
                //arquivo.addChoosableFileFilter(filtroPDF);
                arquivo.setAcceptAllFileFilterUsed(true);
                if (arquivo.showOpenDialog(Cluster_Local.this) == JFileChooser.APPROVE_OPTION) {
                    tfvalor1.setText(arquivo.getSelectedFile().getAbsolutePath());
                }
                //palavra.setText("");
                StringBuilder gambs = new StringBuilder();
                File[] files = arquivo.getSelectedFiles();

                //ArrayList<String> arq = new ArrayList<>();
                //for (int i = 0; i < files.length; i++) {
                //    System.out.println(files[i]);
                //}
                avisos.setText("Status do Processamento: Processando Arquivo");
                try {
                    
                    int cont = 0;
                    System.out.println("Lendo Arquivo!");
                    BufferedReader br = new BufferedReader(new FileReader(files[0]));
                    while (br.ready()) {
                        String linha = br.readLine();
                        texto = linha;

                        System.out.println(linha + "\n");
                        System.out.println("próxima linha");
                        cont++;
                    }
                    br.close();
                    //System.out.println(arra.get(0));

                    String[] texto1 = texto.split(" ");

                    for (int i = 0; i < 10; i++) {
                        System.out.println("Palavra: " + texto1[i]);
                        palavras.add(texto1[i]);
                    }

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                //CHAMANDO FUNÇÃO CONTAGEM PALAVRAS ABAIXO
                StringBuilder enviar = new StringBuilder();
                double inicial = System.currentTimeMillis();

                //ALTERAR PARA AS PALAVRAS QUE CADA PALVRA DO ARTIGO APARECEU
                for (int i = 0; i < palavras.size(); i++) {
                    vezesPalavra.add((Integer.toString(contaPalavras(palavras.get(i), texto))));
                }

                double fim = System.currentTimeMillis();

                double tempoProcessamento = fim - inicial;

                avisos.setText("Status do Processamento: Finalizado");

                System.out.println("Tempo de Realização da Tarefa: " + tempoProcessamento + "milissegundos");
                resultados.setText(Double.toString(tempoProcessamento));

                
                StringBuilder resultados = new StringBuilder();
                for (int i = 0; i < palavras.size(); i++){
                    resultados.append("Palavra: "+palavras.get(i)+"Vezes: "+vezesPalavra.get(i));
                    
                    
                }
                
                
                tfsoma.setText(resultados.toString());
                
                
                
                
                
            }

        });

        add(tela);
        setVisible(true);
        setSize(440, 440);
        setLocation(440, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static int contaPalavras(String palavra, String Texto) {
        int quant = 0;
        String texto = Texto;
        String[] arrayString = texto.split(" ");
        for (int i = 0; i < arrayString.length; i++) {
            if (arrayString[i].equals(palavra)) {
                quant++;
            }
        }
        return quant;
    }

    public static void main(String[] args) {

        new Cluster_Local();

    }

}
