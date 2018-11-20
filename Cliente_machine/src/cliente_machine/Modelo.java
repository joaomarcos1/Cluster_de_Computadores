/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import java.util.ArrayList;

/**
 *
 * @author Tacio Moreira
 */
public class Modelo {
    
    private int               Codigo;
    private ArrayList<String> Palavras;
    private ArrayList<String> palavra;
    private String            Texto_enviar;
    private ArrayList<String> vezesPalavra;

 
    //private String            quantidade;
    private int               Porta;
    
    public Modelo(){}
    
    public Modelo(int Codigo, ArrayList<String> Palavras, String Texto_enviar, int Porta){
        this.Codigo   = Codigo;
        this.Palavras = Palavras;
        this.Texto_enviar = Texto_enviar;
        this.Porta = Porta;
    }

    Modelo(ArrayList<String> palavras, ArrayList<String> vezesPalavra) {
       
        this.Palavras = Palavras;
        this.vezesPalavra = vezesPalavra;
    }

    public ArrayList<String> getvezesPalavra() {        
        return vezesPalavra;
    }
    
    public void setvezesPalavra(ArrayList<String> vezesPalavra) {
        this.vezesPalavra = vezesPalavra;
    }
   
    Modelo(ArrayList<String> palavras) {
        this.Palavras = Palavras;
    }
    
    public int getCodigo(){
        return Codigo;
    }
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }
    public int getPorta(){
        return Porta;
    }
    public void setPorta(int Porta) {
        this.Porta = Porta;
    }
    
   public ArrayList<String> getPalavras() {
        return palavra;
    }

    public void setPalavras(ArrayList<String> palavra) {
        this.palavra = palavra;
    }

    public String getTexto_enviar(){
        return Texto_enviar;
    }
    public void setTexto_enviar(String Texto_enviar) {
        this.Texto_enviar = Texto_enviar;
    }
   

    

   
}