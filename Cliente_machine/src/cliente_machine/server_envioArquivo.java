/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author pasid
 */
public class server_envioArquivo {

    public static void main(String[] args) throws IOException {

        // cria o nosso socket
        ServerSocket servsock = new ServerSocket(13267);
        System.out.println("Server Iniciado!");
        while (true) {
           

            byte[] buffer = new byte[1024];

            try {

                // Cria o input do arquivo ZIP
                //ZipInputStream zinstream = new ZipInputStream(new FileInputStream("C:\\MyFile.zip"));
                ZipInputStream zinstream = new ZipInputStream();

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

                System.out.println("Done");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
