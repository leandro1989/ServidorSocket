/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uigraph;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class serverThread implements Runnable{
    private Socket socket;
    private Serializable object;
    
    public serverThread(Socket socket, Serializable object){
        this.socket = socket;
        this.object = object;
    }

    @Override
    public void run() {
        serverThread serve = new serverThread(socket, object);
        try {
            serve.sending(object, socket);
            serve.receive();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Thread th = new Thread(serve);
        
    }
    
    public Serializable receive () throws IOException {
        ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream());        
        return (Serializable) input;
    }
    
    public void sending (Serializable object, Socket connection) throws IOException{
        try (ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream())) {
                output.flush();
                output.writeObject(object);
                output.flush();   
        }
    }
}
