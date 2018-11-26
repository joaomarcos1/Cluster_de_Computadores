/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author pasid
 */
public class cliente_envioArquivo {

    String caminho = "C:\\Users\\pasid\\Documents\\zipado.zip";

    public static void main(String[] args) throws IOException {

        int filesize = 6022386;
        long start = System.currentTimeMillis();
        int bytesRead;
        int current = 0;
        Socket sock = new Socket("127.0.0.1", 13267);
        // recebendo o arquivo
        byte[] mybytearray = new byte[filesize];
        InputStream is = sock.getInputStream();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\pasid\\Documents\\zipado.zip");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(mybytearray, 0, mybytearray.length);
        current = bytesRead;
        do {
            bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
            if (bytesRead >= 0) {
                current += bytesRead;
            }
        } while (bytesRead > -1);
        bos.write(mybytearray, 0, current);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        bos.close();
        sock.close();

        
        
        
        
        
        System.out.println("Arquivo Enviado!");
    }

}
