package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class clienteUDP {


    public static void main(String[] args ) throws Exception{

        int PUERTO_SERVIDOR = 5000;
        byte buffer[] = new byte[1024];

        try{
            DatagramSocket socketUDP = new DatagramSocket();
            InetAddress DIRECCION_SERVIDOR = InetAddress.getByName("localhost");

            String mensaje = "Holi";
            buffer = mensaje.getBytes();

            DatagramPacket packetUDP = new DatagramPacket(buffer, buffer.length, DIRECCION_SERVIDOR, PUERTO_SERVIDOR);
            socketUDP.send(packetUDP);
            socketUDP.close();

        }catch (SocketException ex) {
            throw new RuntimeException(ex);
        }

    }
}
