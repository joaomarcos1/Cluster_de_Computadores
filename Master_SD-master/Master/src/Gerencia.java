/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Helbert Monteiro
 */
public class Gerencia {

    private ServerSocket socketServidor;
    private Socket dispositivoCliente;

    private Scanner scanner;
    private String json, subTexto;
    private int index, codigoCliente, codigoNode = 0;
    private String[] palavras;

    private Gson gson;
    private Modelo modelo, modeloRetorno;
    private NodePalavra nodePalavra;
    private ArrayList<NodePalavra> listaNodePalavra;
    private Node cliente;

    public void run(int porta, ArrayList<Node> listaNodes) {
        codigoCliente = 0;
        codigoNode = 0;
        modeloRetorno = new Modelo();
        try {
            socketServidor = new ServerSocket(porta);
            System.out.println("Ouvindo na porta " + porta + "...");

            while (true) {
                dispositivoCliente = socketServidor.accept();

                System.out.println("\n\nCliente conectado: " + dispositivoCliente.getInetAddress().getHostAddress());

                scanner = new Scanner(dispositivoCliente.getInputStream());
                json = scanner.nextLine();

                System.out.println(json);

                gson = new Gson();
                modelo = new Modelo();
                modelo = gson.fromJson(json, Modelo.class);

                if (codigoCliente == 0) {
                    cliente = new Node(String.valueOf(modelo.getPortaCliente()), dispositivoCliente.getInetAddress().getHostAddress());
                    for (int i = 0; i < modelo.getPalavras().size(); i++) {
                        modeloRetorno.iniciaVezes("0");
                    }
                    codigoCliente = 1;
                }

                switch (modelo.getCodigo()) {

                    case 0:
                        recebe();
                        break;

                    case 1:
                        distribuir(modelo, listaNodes);
                        break;
                    case 2:
                        System.out.println("Fazendo Redução ...");
                        reduzir(modelo, cliente, listaNodes);
                        break;
                }

                dispositivoCliente.close();
            }

        } catch (IOException a) {
            System.out.println("Erro na Thread: " + a.getMessage());
        }
    }

    private void recebe() {
        Socket sockServer = null;
        FileOutputStream fos = null;
        InputStream is = null;

        try {
            // Criando conexão com o servidor
            System.out.println("Conectando com Servidor porta 13267");
            sockServer = new Socket("127.0.0.1", 13267);
            is = sockServer.getInputStream();

            // Cria arquivo local no cliente
            //fos = new FileOutputStream(new File("c:\\temp\\source-copy.zip"));
            fos = new FileOutputStream(new File("C:\\Users\\pasid\\Documents\\zipado.zip"));
            System.out.println("Arquivo Local Criado \"C:\\Users\\pasid\\Documents\\zipado.zip");

            // Prepara variaveis para transferencia
            byte[] cbuffer = new byte[1024];
            int bytesRead;

            // Copia conteudo do canal
            System.out.println("Recebendo arquivo...");
            while ((bytesRead = is.read(cbuffer)) != -1) {
                fos.write(cbuffer, 0, bytesRead);
                fos.flush();
            }

            System.out.println("Arquivo recebido!");

            //DESCOMPATAÇÃO DE ARQUIVO
            //String arquivo = ("C:\\Users\\pasid\\Documents\\zipado.zip");
            //String caminho_origem = ("C:\\Users\\pasid\\Music\\zipado.zip");
            //String caminho_saida = ("C:\\Users\\pasid\\Documents\\aa");
            //descompacta(caminho_origem, caminho_saida);
            String INPUT_ZIP_FILE = ("C:\\Users\\pasid\\Music\\zipado.zip");
            String OUTPUT_FOLDER = ("C:\\Users\\pasid\\Documents\\");
            unzipFile z = new unzipFile();

            z.unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sockServer != null) {
                try {
                    sockServer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void distribuir(Modelo modelo, ArrayList<Node> listaNodes) {
        palavras = modelo.getTexto().split(" ");
        listaNodePalavra = new ArrayList<>();
        index = 0;

        for (int i = 0; i < listaNodes.size(); i++) {
            nodePalavra = new NodePalavra(i);
            listaNodePalavra.add(nodePalavra);
        }

        for (int i = 0; i < palavras.length; i++) {
            if (index < listaNodes.size()) {
                listaNodePalavra.get(index).addPalavra(palavras[i]);
                index++;
            } else {
                index = 0;
                listaNodePalavra.get(index).addPalavra(palavras[i]);
                index++;
            }
        }

        System.out.println("Texto Inteiro: " + modelo.getTexto());
        subTexto = "";

        for (int i = 0; i < listaNodePalavra.size(); i++) {
            for (int j = 0; j < listaNodePalavra.get(i).getListaPalavras().size(); j++) {
                subTexto = subTexto + listaNodePalavra.get(i).getListaPalavras().get(j) + " ";
            }
            System.out.println("Palavras separadas para Node " + i + ": " + subTexto);

            modelo = new Modelo(modelo.getCodigo(), modelo.getPalavras(), subTexto);

            new Transmissor().enviar(modelo, listaNodes.get(i).getIp(), Integer.parseInt(listaNodes.get(i).getPorta()), false);

            subTexto = "";
        }
    }

    private void reduzir(Modelo modelo, Node cliente, ArrayList<Node> listaNodes) {
        for (int j = 0; j < modeloRetorno.getVezesPalavras().size(); j++) {
            modeloRetorno.acrescentaValorVez(j, modelo.getValorVez(j));
        }
        codigoNode++;
        if (codigoNode == listaNodes.size()) {
            modelo = new Modelo(modelo.getPalavras(), modeloRetorno.getVezesPalavras());
            new Transmissor().enviar(modelo, cliente.getIp(), Integer.parseInt(cliente.getPorta()), true);
            codigoCliente = 0;
            codigoNode = 0;
        }
    }

}
