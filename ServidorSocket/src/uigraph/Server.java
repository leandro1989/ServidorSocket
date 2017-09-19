/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uigraph;

/**
 *
 * @author Leandro
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Executando");
        ServerSocket server = new ServerSocket(7002);
        Maps mapa = new Maps(200,20);
        Server client = new Server();
        
        while (true) {     
            Socket connection = server.accept();
            client.sending(mapa,connection);
        }
    }
    
    public void receive (Socket connection) throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(connection.getInputStream())){
        }
    }
    
    public void sending (Serializable object, Socket connection) throws IOException{
        try (ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream())) {
                output.flush();
                output.writeObject(object);
                output.flush();   
        }
    }  
}