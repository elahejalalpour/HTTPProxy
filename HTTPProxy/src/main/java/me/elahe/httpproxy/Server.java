package me.elahe.httpproxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author elahe jalalpoor
 */
public class Server implements Runnable {

    private ArrayList<String> blocked;
    JTextArea txtLog;
    private boolean running ;
    private int port ;

    ServerSocket ss;
    public Server(JTextArea txtLog,int port ) {
        blocked = new ArrayList<>();     
        this.running = false;
        this.port = port ;
    }

    public void setBlocked(ArrayList blockedA) {
        blocked = blockedA;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(port);
            long index = 0 ;
            while (running) {
                try {
                    Socket client = ss.accept();
                    index ++ ;
                    Client c = new Client ( index, client , txtLog , blocked );
                    c.start();
                } // Now loop again, waiting for the next connection
                catch (Exception e) {
                }
            } // If anything goes wrong, print an error message
            ss.close();
        } catch (IOException ex) {
        }
    }

    void startServer(int portNumber) {
        running = true ;
        port = portNumber;
        new Thread(this).start();
    }

    void stopServer() {
        running = false ;
        try {
            ss.close();
        } catch (IOException ex) {
        }
    }
    
    public static void main(String[] args) {
        
            GUI Window;
        try {
            Window = new GUI();
            Window.setVisible(true);
            Window.setSize(1000, 1000);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    
    }
}
