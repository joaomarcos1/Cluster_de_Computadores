/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_machine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author pasid
 */
public class cliente_envioArquivo {

    
    String caminho = "C:\\Users\\pasid\\Documents\\zipado.zip";
    
    
    
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("127.0.0.1", 1234);

        /*DataInputStream in = new DataInputStream(cliente.getInputStream());
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
         
        int intt = in.readInt();
         
        System.out.println(intt);*/
        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

        FileInputStream file = new FileInputStream("C:\\Users\\pasid\\Documents\\zipado.zip");

        byte[] buf = new byte[4096];

        while (true) {
            int len = file.read(buf);
            if (len == -1) {
                break;
            }
            out.write(buf, 0, len);
        }
        System.out.println("Arquivo Enviado!");
    }

}
