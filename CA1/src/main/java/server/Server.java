/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Server {       //Very much just the basic start of the Echo-server

    private static boolean serverRunning = true;

    public static void stopServer() {
        serverRunning = false;
    }

    public static void main(String[] args) {
        List<User> userList = new CopyOnWriteArrayList();
        String ip;
        int port;
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            return;    // End program or hardcoded values?
        }
        ServerSocket ss;
        try {
            //Logic for the logger when the server starts
            Log.setLogFile("logFile.txt", "ServerLog");
            Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "Starting the Server");
            ss = new ServerSocket();
            ss.bind(new InetSocketAddress(ip, port));
            Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "Server started!, listening on " + port + " " + ", bound to " + ip);  // Rework for logger
            while (serverRunning) {
                Socket socket = ss.accept();
                Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "New client connected"); //Add the connected client's name here
                new Thread(new ClientHandler(socket, userList)).start();
                //handleClient(socket);  // Needs multithreading
            }
        } catch (IOException ex) {
            //In case of an error this will log it
            Logger.getLogger(Log.LOG_NAME).log(Level.SEVERE, null, ex);
        } finally {
            //Closes the logger connection
            Log.closeLogger();
        }

    }

    private static void handleClient(Socket socket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void runServer(String ip, int port) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
