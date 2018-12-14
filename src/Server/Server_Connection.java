package Server;
//code for server

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server_Connection {

    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    private String pw;
    private final static String pre = "++--++_";

    public Server_Connection(String pw) {
        this.pw = pw;
        try {
            System.out.println("Server Started");
            ss = new ServerSocket(55355);
            waitForClient();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void waitForClient() {
        try {
            s = ss.accept();
            System.out.println(s);
            System.out.println("CLIENT CONNECTED");
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            waitForCommand();
        } catch (IOException ex) {
            Logger.getLogger(Server_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void waitForCommand() {
        try {
            String str = dis.readUTF();
            System.out.println("Client Message:" + str);

            if (str.contains(pre)) {
                String[] args = str.split("_");
                switch (args[1]) {
                    case "password":
                        String client_pw = "";
                        if(args.length != 2)
                        {
                            client_pw = args[2];
                        }
                            
                        if (client_pw.equals(pw)) {
                            sendCommand(pre + "connected");
                        } else {
                            sendCommand(pre + "failed");
                        }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendCommand(String command) {
        try {
            dos.writeUTF(command);
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String as[]) {
        new Server_Connection("");
    }

    public void ServerChat() throws IOException {
        String str, s1;
        do {
            str = dis.readUTF();
            System.out.println("Client Message:" + str);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s1 = br.readLine();
            dos.writeUTF(s1);
            dos.flush();
        } while (!s1.equals("bye"));
    }
}
