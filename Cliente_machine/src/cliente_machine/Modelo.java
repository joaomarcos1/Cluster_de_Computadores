/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.ArrayList;

/**
 *
 * @author Tacio Moreira
 */
public class Modelo {
    
    private int               codigo;
    private ArrayList<String> palavras;
    private String            texto;
    private ArrayList<String> vezesPalavra;
    //private ArrayList<Int> valores;

 
    //private String            quantidade;
    private int               portaCliente;
    
    public Modelo(){}
    
    public Modelo(int Codigo, ArrayList<String> Palavras, String Texto_enviar, int Porta){
        this.codigo   = Codigo;
        this.palavras = Palavras;
        this.texto = Texto_enviar;
        this.portaCliente = Porta;
    }

    Modelo(ArrayList<String> palavras, ArrayList<String> vezesPalavra) {
       
        this.palavras = this.palavras;
        this.vezesPalavra = vezesPalavra;
    }

    public ArrayList<String> getvezesPalavra() {        
        return vezesPalavra;
    }
    
    public void setvezesPalavra(ArrayList<String> vezesPalavra) {
        this.vezesPalavra = vezesPalavra;
    }
   
    Modelo(ArrayList<String> palavras) {
        this.palavras = this.palavras;
    }
    
     public ArrayList<String> getpalavras() {        
        return palavras;
    }
    
    public void setpalavras(ArrayList<String> palavras) {
        this.palavras = palavras;
    }
    
    public int getCodigo(){
        return codigo;
    }
    public void setCodigo(int Codigo) {
        this.codigo = Codigo;
    }
    public int getPorta(){
        return portaCliente;
    }
    public void setPorta(int Porta) {
        this.portaCliente = Porta;
    }
    
   
    public String getTexto_enviar(){
        return texto;
    }
    public void setTexto_enviar(String Texto_enviar) {
        this.texto = Texto_enviar;
    }
   

    

   
}