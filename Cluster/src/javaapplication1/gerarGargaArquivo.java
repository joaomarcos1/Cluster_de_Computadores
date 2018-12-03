/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import javaapplication1.GerarArquivo;

/**
 *
 * @author pasid
 */
public class gerarGargaArquivo {

    
    
    
    public static void main(String[] args) throws IOException {

        Scanner ler = new Scanner(System.in);
        String frase = null;
        int i, quantidade;
        
        System.out.println("Inserir quantidade de palavras:");
        quantidade = ler.nextInt();
        
        String article[] = {"the", "a", "one", "some", "any"};
        String noun[] = {"boy", "girl", "dog", "town", "car"};
        String verb[] = {"drove", "jumped", "ran", "walked", "skipped"};
        String preposition[] = {"to", "from", "over", "under", "on"};
        
        String conteudo;
        ArrayList<String> texto = new ArrayList<String>();
        
        for (i = 0; i < (quantidade/10); i++) {
            //tamanhos totais palavras de cada tipo 
            int articleLength = article.length;
            int nounLength = noun.length;
            int verbLength = verb.length;
            int prepositionLength = preposition.length;
            
            int randArticle = (int) (Math.random() * articleLength);
            int randNoun = (int) (Math.random() * nounLength);
            int randVerb = (int) (Math.random() * verbLength);
            int randPreposition = (int) (Math.random() * prepositionLength);
            int randArticle2 = (int) (Math.random() * articleLength);
            int randNoun2 = (int) (Math.random() * nounLength);
            
            
            frase = article[randArticle] + " " + noun[randNoun] + " " + verb[randVerb] + " " + preposition[randPreposition] + " " + article[randArticle] + " " + noun[randNoun2] + " "+ article[randArticle] + " " + noun[randNoun] + " " + verb[randVerb] + " " + preposition[randPreposition]+"." ;
            //System.out.println(frase);
           
            //conteudo = frase; 
            texto.add(frase);
            
        }
        
        System.out.println("Texto Gerado!");
        
        //Enviando varga de text para os arquivos
        GerarArquivo gerar = new GerarArquivo();

        gerar.GerarArquivo(texto);

    }

    public static String converte() { // retorna a string gerada 
        Random r = new Random(); // objeto para chamar nextGaussian()
        String resultado = new String(); // obtem a string final e a retorna.  
        char letras[] = {'a', 'e', 'i', 'o', 'u', 'b', 'c', 'd', 'f', 'g'};
        double d; // recebe o valor de nextGaussian()
        int posicao;
        while ((d = r.nextGaussian()) < 0); // só sai quando for um valor positivo
        /* valorStr recebe o nextGaussian() sem ponto numa string. Primeiro "converte" o
		 * double d para String e depois pega o q está "antes" e "depois" do ponto. 
         */
        StringTokenizer st = new StringTokenizer(String.valueOf(d), ".");
        String valorStr = st.nextToken().concat(st.nextToken());
        /* posicao vai ter o i-ésimo caractere de valorStr em forma de inteiro. Essa 
		 * variável indica o elemento do vetor letras[] que será concatenado a resultado 
		 * */
        for (int i = 0; i < valorStr.length(); i++) {
            posicao = Integer.parseInt(valorStr.substring(i, i + 1));
            resultado = resultado.concat(String.valueOf(letras[posicao]));
        }
        return resultado;

    }
}
