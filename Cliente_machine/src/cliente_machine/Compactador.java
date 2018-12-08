/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Compactador {

    //Constantes
    static final int TAMANHO_BUFFER = 4096; // 4kb

    //método para compactar arquivo
    /*
    public static void compactarParaZip(String arqSaida, String arqEntrada)
            throws IOException {
        int cont;
        byte[] dados = new byte[TAMANHO_BUFFER];

        BufferedInputStream origem = null;
        FileInputStream streamDeEntrada = null;
        FileOutputStream destino = null;
        ZipOutputStream saida = null;
        ZipEntry entry = null;

        try {
            destino = new FileOutputStream(new File(arqSaida));
            saida = new ZipOutputStream(new BufferedOutputStream(destino));
            File file = new File(arqEntrada);
            streamDeEntrada = new FileInputStream(file);
            origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
            entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);

            while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                saida.write(dados, 0, cont);
            }
            origem.close();
            saida.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
     */
    //public static void compactarParaZip(final String arqSaida, final String... arqEntradas) throws IOException {
    public void compactarParaZip(final String arqSaida, final ArrayList<String>... arqEntradas) throws IOException {
        int cont;
        final byte[] dados = new byte[TAMANHO_BUFFER];

        final FileOutputStream destino = new FileOutputStream(new File(arqSaida));
        final ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));

        int i = 0;
        for (final String arqEntrada : arqEntradas[i]) {
            final File file = new File(arqEntrada);
            final FileInputStream streamDeEntrada = new FileInputStream(file);
            final BufferedInputStream origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
            final ZipEntry entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);

            while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                saida.write(dados, 0, cont);
            }
            origem.close();
            i++;
        }

        saida.close();
    }

    public static void descompactador(String caminho) {
        byte[] buffer = new byte[1024];

        try {

            // Cria o input do arquivo ZIP
            ZipInputStream zinstream = new ZipInputStream(new FileInputStream("C:\\Users\\pasid\\Documents\\zipado.zip"));

            // Pega a proxima entrada do arquivo
            ZipEntry zentry = zinstream.getNextEntry();

            // Enquanto existir entradas no ZIP
            while (zentry != null) {
                // Pega o nome da entrada
                String entryName = zentry.getName();

                // Cria o output do arquivo , Sera extraido onde esta rodando a classe
                FileOutputStream outstream = new FileOutputStream(entryName);
                int n;

                // Escreve no arquivo
                while ((n = zinstream.read(buffer)) > -1) {
                    outstream.write(buffer, 0, n);

                }

                // Fecha arquivo
                outstream.close();

                // Fecha entrada e tenta pegar a proxima
                zinstream.closeEntry();
                zentry = zinstream.getNextEntry();
            }

            // Fecha o zip como um todo
            zinstream.close();

            System.out.println("Descompactado!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
