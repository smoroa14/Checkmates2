package Server;
//code for client

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_Connection {

    Socket s;
    DataInputStream din;
    DataOutputStream dout;

    public Client_Connection(String ip) {
        try {
            s = new Socket(ip, 55355);
            //s=new Socket("localhost",10);
            System.out.println(s);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ClientChat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1;
        do {
            s1 = br.readLine();
            dout.writeUTF(s1);
            dout.flush();
            System.out.println("Server Message:" + din.readUTF());
        } while (!s1.equals("stop"));
    }

    public void sendCommand(String command) {
        try {
            dout.writeUTF(command);
            dout.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String waitForCommand() {
        try {
            return din.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
