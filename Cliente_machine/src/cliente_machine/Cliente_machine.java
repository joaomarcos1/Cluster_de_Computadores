/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Cliente_machine extends JFrame {

    //criando o arquivo gson
    Gson gson = new Gson();
    int codigo = 1;
    ArrayList<String> palavras_enviada = new ArrayList<>();
    String texto_enviar;//string que recebe o texto busacado
    int porta;

    public String getTxt() {
        return texto_enviar;
    }

    JLabel nome_cliente = new JLabel("Cliente");

    JLabel nome_texto = new JLabel("Texto que deseja verificar");
    JTextArea texto = new JTextArea();
    JScrollPane barraRolagem;

    JButton enviar = new JButton("Enviar");
    JButton adicionar = new JButton("Escolher Arquivos");

    private Socket cliente;
    //parte para enviar a palavra e receber 
    JLabel nome_palavra = new JLabel("Informe a palavra que deseja verificar");
    JTextField palavra = new JTextField();

    JTextArea palavras_adicionadas = new JTextArea();

    JButton verificar = new JButton("Verificar");

    private Socket cliente1;

    //String caminho = "C:\\Users\\pasid\\Documents\\zipado.zip";
    String caminho = "zipado.zip";

    JLabel confirmacao = new JLabel();

    public Cliente_machine() {

        /*
        JPanel tela = new JPanel();
        tela.setLayout(null);
        tela.setBackground(Color.white);

        JLabel Imagem = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/Imagens/texto.jpg")));

        tela.add(Imagem);
        Imagem.setSize(440, 150);
        Imagem.setLocation(0, 5);

        tela.add(palavra);
        palavra.setBounds(40, 190, 200, 32);

        tela.add(nome_palavra);
        nome_palavra.setBounds(35, 160, 250, 30);
         */

 /*
        tela.add(palavras_adicionadas);
        palavras_adicionadas.setBounds(40, 235, 300, 100);
        palavras_adicionadas.setBorder(new LineBorder(Color.GRAY));
        palavras_adicionadas.setLineWrap(true);
        palavras_adicionadas.setWrapStyleWord(true);
        palavras_adicionadas.setEditable(false);
        barraRolagem = new JScrollPane(palavras_adicionadas);
        barraRolagem.setBounds(40, 235, 300, 100);
        tela.add(barraRolagem);
         */
 /*
        tela.add(texto);
        texto.setBounds(40, 380, 350, 200);
        texto.setBorder(new LineBorder(Color.GRAY));
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        barraRolagem = new JScrollPane(texto);
        barraRolagem.setBounds(40, 380, 340, 180);
        tela.add(barraRolagem);

        
        
        tela.add(nome_texto);
        nome_texto.setBounds(30, 350, 150, 30);
        tela.add(adicionar);
        adicionar.setBounds(40, 230, 150, 30);
        adicionar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //if (!"".equals(palavra.getText())) {
                
                       String a = palavra.getText();
                       palavras_adicionadas.insert(a, palavras_adicionadas.getCaretPosition());
                       palavras_adicionadas.append("\n");
                       palavras_enviada.add(palavra.getText());
                       Modelo modelo1 = new Modelo();
                       modelo1.setpalavras(palavras_enviada);
                       System.out.println(palavras_enviada);

                 
                try {
                    JFileChooser arquivo = new JFileChooser();
                    arquivo.setMultiSelectionEnabled(true);
                    //FileNameExtensionFilter filtroPDF = new FileNameExtensionFilter("Arquivos PDF", "pdf");
                    //arquivo.addChoosableFileFilter(filtroPDF);
                    arquivo.setAcceptAllFileFilterUsed(true);
                    if (arquivo.showOpenDialog(Cliente_machine.this) == JFileChooser.APPROVE_OPTION) {
                        palavra.setText(arquivo.getSelectedFile().getAbsolutePath());
                    }
                    //palavra.setText("");

                    //StringBuilder gambs = new StringBuilder();
                    File[] files = arquivo.getSelectedFiles();
                    ArrayList<String> arq = new ArrayList<>();
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(files[i]);
                        arq.add(files[i].toString());
                    }

                    //Compactador.compactarParaZip("C:\\Users\\pasid\\Documents\\zipado.zip", arquivo.getSelectedFile().getAbsolutePath());
                    Compactador.compactarParaZip(caminho, arq);
                    //Compactador.descompactador(caminho);
                    // );
                    confirmacao.setText("Arquivos Compactados");
                } catch (IOException ex) {
                    Logger.getLogger(Cliente_machine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
         */

        //tela.add(confirmacao);
        //confirmacao.setBounds(40, 280, 200, 30);

        /*
        tela.add(enviar);
        enviar.setBounds(40, 350, 350, 40);
        enviar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //if (!"".equals(texto.getText()) && !"".equals(palavras_adicionadas.getText())) {
                //Resultado result = new Resultado();

                //Modelo modelo1 = new Modelo();
                texto_enviar = texto.getText();
                //modelo1.setTexto_enviar(texto_enviar);
                //System.out.println("aaa");
                try {
                    //enviar_arquivo_gson();

                    envio_Arquivos();
                } catch (IOException ex) {
                    Logger.getLogger(Cliente_machine.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Resultado rst = new Resultado();
                texto.setText("");
                palavras_adicionadas.setText("");

                //waitForClient();
                //ABAIXO, ENVIAR ARQUIVO PARA MASTER
            }
        }
        );

        /*
        add(tela);
        setVisible(true);
        setSize(440, 500);
        setLocation(440, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         */
    }

    public void enviar_arquivo_gson() {

        Thread tsensor3 = new Thread(new Runnable() {
            @Override
            public void run() {
                codigo = 1;
                porta = 9002;
                //criando o modelo para transformar em Json
                Modelo modelo1 = new Modelo(codigo, palavras_enviada, texto_enviar, 9002);
                String json = "";
                //transformando o modelo em Json
                json = gson.toJson(modelo1);
                //System.out.println("Modelo transformado em Json: " + json);
                System.out.println("Arquivo pronto para ser enviado!");
                try {

                    Socket cliente = new Socket("127.0.0.1", 9000);
                    System.out.println("Enviando...");
                    PrintStream saida = new PrintStream(cliente.getOutputStream());

                    saida.print(json);

                    saida.close();
                    cliente.close();
                    System.out.println("Arquivo enviado!!!");

                } catch (IOException ex) {
                    Logger.getLogger(Cliente_machine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tsensor3.start();

    }

    public void waitForClient() {
        // Checa se a transferencia foi completada com sucesso
        OutputStream socketOut = null;
        ServerSocket servsock = null;
        FileInputStream fileIn = null;

        try {
            // Abrindo porta para conexao de clients
            servsock = new ServerSocket(13267);
            System.out.println("Porta de conexao aberta 13267");

            // Cliente conectado
            Socket sock = servsock.accept();
            System.out.println("Conexao recebida pelo cliente");

            // Criando tamanho de leitura
            byte[] cbuffer = new byte[1024];
            int bytesRead;

            // Criando arquivo que sera transferido pelo servidor
            //C:\Users\pasid\Music
            //File file = new File("C:\\Users\\pasid\\Music\\zipado.zip");
            File file = new File("zipado.zip");
            fileIn = new FileInputStream(file);
            System.out.println("Lendo arquivo...");

            // Criando canal de transferencia
            socketOut = sock.getOutputStream();

            // Lendo arquivo criado e enviado para o canal de transferencia
            System.out.println("Enviando Arquivo...");
            while ((bytesRead = fileIn.read(cbuffer)) != -1) {
                socketOut.write(cbuffer, 0, bytesRead);
                socketOut.flush();
            }

            System.out.println("Arquivo Enviado!");
        } catch (Exception e) {
            // Mostra erro no console
            e.printStackTrace();
        } finally {
            if (socketOut != null) {
                try {
                    socketOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (servsock != null) {
                try {
                    servsock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void envio_Arquivos(String IP) throws FileNotFoundException, IOException {
        File f = new File("zipado.zip");
        System.out.println("Lendo Arquivo...");
        FileInputStream in = new FileInputStream(f);
        Socket socket = new Socket(IP, 9000);
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter writer = new BufferedWriter(osw);
        writer.write(f.getName() + "\n");
        writer.flush();
        int c;
        int tamanho = 99999999; // buffer de 4KB  
        byte[] buffer = new byte[tamanho];
        int lidos = -1;
        System.out.println("Enviando Arquivo...");
        while ((lidos = in.read(buffer, 0, tamanho)) != -1) {
            out.write(buffer, 0, lidos);
        }
        System.out.println("Arquivo Enviado!");
    }

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./properties/dados.properties");
        props.load(file);
        return props;

    }

    public static void main(String[] args) throws IOException {

        //new Cliente_machine();
        //new Cliente_machine();
        //Resultado rst = new Resultado();
        //Properties prop = getProp();
        ArrayList<String> array = new ArrayList<>();
        //String arquivo = prop.getProperty("arquivo_compactacao");
        array.add("ArquivoGerado.txt");
        Compactador compac = new Compactador();
        compac.compactarParaZip("zipado.zip", array);
        Cliente_machine cliente = new Cliente_machine();

        cliente.envio_Arquivos(args[0]);
    }

}
