/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import domain.Librarian;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LORA
 */
public class ServerThread extends Thread{

    private ServerSocket serverSocket;
    private List<HandleClientThread> clients;
    
    public ServerThread() throws IOException {
        serverSocket=new ServerSocket(9000);
        clients=new ArrayList<>();
    }
    
    
    @Override
    public void run() {
        while(!serverSocket.isClosed()){
        
         try {
            System.out.println("Waiting for client...");
            Socket socket=serverSocket.accept();
            HandleClientThread thread=new HandleClientThread(socket);
            thread.start();
            clients.add(thread);
            System.out.println("Client connected...");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        stopAllThreads();
    }
    
    private void stopAllThreads(){
        for (HandleClientThread client : clients) {
            try {
                client.getSocket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public  List<Librarian> getAllLibrarians(){
        List<Librarian> librarians=new ArrayList<>();
        for (HandleClientThread client : clients) {
            librarians.add(client.getLibrarian());
           
        }
        return librarians;
        
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
}
