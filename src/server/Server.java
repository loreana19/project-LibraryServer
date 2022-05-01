/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import controller.Controller;
import domain.Librarian;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ps.view.FrmMain;

/**
 *
 * @author LORA
 */
public class Server {
    
    public static void main(String[] args) {
        new FrmMain().setVisible(true);
    }
  /*  public static void main(String[] args) throws Exception {
        Server server=new Server();
        try {
            server.startServer();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void startServer() throws Exception {
         ServerSocket serverSocket=new ServerSocket(9000);
         System.out.println("Waiting for client");
         Socket socket=serverSocket.accept();
         System.out.println("Client is connected with server");
         
         handleClient(socket);
     }
    
    private void handleClient(Socket socket) throws Exception{
        while(true){
            try {
                //ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
                //Request request=(Request) in.readObject();
                Request request=(Request) new Receiver(socket).receive();
                Response response=null;
                
                switch(request.getOperation()){
                    case Operations.LOGIN:
                        response=login(request);
                        break;
                    default:
                }
                //ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
                //out.writeObject(response);
                //out.flush();
                new Sender(socket).send(response);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Response login(Request request) {
        Response response=new Response();
        Librarian requestLibrarian=(Librarian) request.getArgument();
        
        try {
            Librarian librarian=Controller.getInstance().Login(requestLibrarian.getUsername(), requestLibrarian.getPassword());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(librarian);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
            return response;
        }
        return response;
    }

   */
}
