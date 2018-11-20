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
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tacio Moreira
 */
public class Resultado extends JFrame {
    
    Gson gson = new Gson();
    
    ArrayList<String> palavras = new ArrayList<>();
    ArrayList<String> vezesPalavraJSON = new ArrayList<>();
    String texto = null;
   
    int qtd;
    int codigo;
    
    JScrollPane barraRolagem ;
     
    JLabel nome_palavra = new JLabel("Endereço IP conectado");
    JTextField palavra = new JTextField();
    
    JLabel nome_resultado = new JLabel("Resultado");
    JTextArea resultado = new JTextArea();
    
    JLabel nome_texto = new JLabel("Texto");
    JTextArea txt = new JTextArea();
    
    JButton verificar = new JButton("Verificar");
    JButton adicionar = new JButton("Adicionar");
    
    private Socket cliente;
    
    JButton voltar = new JButton("Voltar");
    
    //Object [][] objeto;
    //JTable tabela;
    JScrollPane barraRolagem1;
    
   //DefaultTableModel modelo = new DefaultTableModel();
    
   // ArrayList<Modelo> tema = new ArrayList<>();
   // String [] colunas = new String[]{"Palavras", "Total de Repetições"};
    
    JPanel tela = new JPanel();
        
    public Resultado(){
    
        tela.setLayout(null);
        tela.setBackground(Color.white);
        
        tela.add(palavra);
        palavra.setBounds(50, 50, 250, 32);
        palavra.setEditable(false);

        tela.add(nome_palavra);
        nome_palavra.setBounds(50, 10, 250, 30);
        
        tela.add(nome_resultado);
        nome_resultado.setBounds(50, 80, 350, 40);

        tela.add(resultado);
        resultado.setBounds(50, 120, 200, 150);
        resultado.setBorder(new LineBorder(Color.GRAY));
        resultado.setLineWrap(true); 
        resultado.setWrapStyleWord(true);
        resultado.setEditable(false);
        barraRolagem = new JScrollPane(resultado);
        barraRolagem.setBounds(50, 120, 300, 100);
        tela.add(barraRolagem);
        
        tela.add(nome_texto);
        nome_texto.setBounds(50, 220, 350, 40);

        tela.add(txt);
        txt.setBounds(50, 260, 200, 150);
        txt.setBorder(new LineBorder(Color.GRAY));
        txt.setLineWrap(true); 
        txt.setWrapStyleWord(true);
        txt.setEditable(false);
        barraRolagem = new JScrollPane(txt);
        barraRolagem.setBounds(50, 260, 300, 150);
        tela.add(barraRolagem);
        
        
        
        tela.add(voltar);
        voltar.setBounds(150, 420, 100, 30);
        voltar.addActionListener(
            
            new ActionListener(){    
                @Override
                public void actionPerformed(ActionEvent e) {
                    //this.actionPerformed(e);
                   dispose();
                }
             }
            );
                
        //tela.add(verificar);
       //verificar.setBounds(300, 100, 90, 30);
        //verificar.addActionListener(
            
            //new ActionListener(){
                
               // @Override
               // public void actionPerformed(ActionEvent e) {
               
               
               
                   Thread tsensor3 = new Thread(new Runnable() {
                   @Override
                   public void run() {
                    
                        
                           Modelo modelo1 = new Modelo();
                           
                           try {
                               GeradorGrafico geradorGrafico = new GeradorGrafico();
                               ServerSocket servidor3 = new ServerSocket(9002);
                               System.out.println("Esperando servidor retornar a resposta: ");
                                while (true) {
                                    System.out.println("abcd \n");
                                    
                                    Socket cliente1 = servidor3.accept();
                                    
                                    System.out.println("abvscafa \n");
                                    String a = "Cliente " + cliente1.getInetAddress().getHostAddress() + " conectado...";
                                    palavra.setText(a); 
                                    System.out.println("Cliente " + cliente1.getInetAddress().getHostAddress() + " conectado...");
                                    
                                    Scanner entrada = new Scanner(cliente1.getInputStream());
                                    String ler = entrada.nextLine();
                                    System.out.println("recebendo dados do cliente");
                                    

                                   Gson gson = new Gson();

                                    Modelo modelo2 = new Modelo();
                                //RECEBENDO A STRING JSON
                                    modelo2 = gson.fromJson(ler, Modelo.class);
                                    
                                    
                                       codigo = modelo2.getCodigo();
                                       palavras = modelo2.getpalavras();
                                       vezesPalavraJSON = modelo2.getvezesPalavra();
                                       
                                    //tabela = new JTable(objeto, colunas);
                                    //barraRolagem = new JScrollPane(tabela);
                                    //barraRolagem.setBounds(20, 50, 540, 150);
      
                                    //tela.add(barraRolagem);
                                      //  texto = modelo2.getTexto_enviar();
                                        txt.setText(new Cliente_machine().getTxt());
                                        for(int i = 0 ; i < modelo2.getpalavras().size(); i++){
                                            System.out.println("Palavra: " + modelo2.getpalavras().get(i));
                                            System.out.println("Número de vezes que aparece: " + modelo2.getvezesPalavra().get(i));
                                            String imprime = "Palavras -  " + modelo2.getpalavras().get(i) + "  aparece  " + modelo2.getvezesPalavra().get(i) + " vezes";
                                            resultado.insert(imprime +"\n", resultado.getCaretPosition());
                                            
                                            resultado.append("\n");
                                            geradorGrafico.addValor(Double.parseDouble(modelo2.getvezesPalavra().get(i)), modelo2.getpalavras().get(i), modelo2.getpalavras().get(i));
                                             //modelo.addRow(new Object[]{modelo2.getPalavras().get(i), modelo2.getvezesPalavra().get(i)});
                                        }
                                        geradorGrafico.exibeGrafico();
                                  
                                }
                                

                           } catch (IOException ex) {
                               Logger.getLogger(Cliente_machine.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       

                    }
                    });
                tsensor3.start();
                //}
            //}
        //);
        
        add(tela);
        setVisible(true);
        setSize(440, 500);
        setLocation(440, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

    }
    
    
        
}

