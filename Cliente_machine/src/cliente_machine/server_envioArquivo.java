/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author pasid
 */
public class server_envioArquivo {

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(1234);

        System.out.println("Esperando envio do arquivo");
        Socket sv = servidor.accept();

        /*DataInputStream in = new DataInputStream(sv.getInputStream());
        DataOutputStream out = new DataOutputStream(sv.getOutputStream());
         
        out.writeInt(123456789);*/
        ObjectInputStream out = new ObjectInputStream(sv.getInputStream());

        FileOutputStream file = new FileOutputStream("c:/z/Arquivoqueclienteenviou.jpg");

        byte[] buf = new byte[4096];

        while (true) {
            int len = out.read(buf);
            if (len == -1) {
                break;
            }
            file.write(buf, 0, len);
        }

    }

}
