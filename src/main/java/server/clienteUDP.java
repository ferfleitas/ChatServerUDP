package server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class clienteUDP {


    public static void main(String args[] ) throws Exception{

        int PUERTO_SERVIDOR = 5000;
        byte buffer[] = new byte[1024];


        try{
            DatagramSocket socketUDP = new DatagramSocket();





        }catch (SocketException ex) {
            throw new RuntimeException(ex);
        }

    }
}
