/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

/**
 *
 * @author virtex03
 */
import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tacinho Moreira
 */
public class ModelTable extends AbstractTableModel{
    
   private final ArrayList<Modelo> tema;//CRIA ARRAYLIST TIPO TEMAS
 
   public ModelTable(ArrayList<Modelo> tema) {//CONSTRUTOR
     this.tema = tema;
   }
 
   @Override
   public int getColumnCount() {//devolve a quantidade de colunas
     return 2;
   }
 
   @Override
   public int getRowCount() {//devolve a quantidade de linhas   
     return tema.size();
   }
 
   @Override
   public Object getValueAt(int rowIndex, int columnIndex) {//dada uma linha e uma coluna devolve o valor correspondente.
     Modelo n = tema.get(rowIndex);
     
     switch (columnIndex) {
     case 0:
       return n.getpalavras();
     case 1:
       return n.getvezesPalavra();
     
     }
     return null;
   }
   public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Palavras";
                case 1:
                    return "Total de Repetições";
                
            }
            return null;
        }
}
