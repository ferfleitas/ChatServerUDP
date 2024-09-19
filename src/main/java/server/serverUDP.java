package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class serverUDP {

    public static void main(String[] args) throws SocketException {

        int PUERTO = 5000; //puerto en donde se le enviara los socketsUDP al server
        byte[] buffer = new byte[1024]; //datos que contendra el packetUDP

        try{
            //socket recibidor
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            while(true) {
                //limpiamos el buffer
                buffer = new byte[1024];
                //packet de escucha
                DatagramPacket packetUDP = new DatagramPacket(buffer, buffer.length);
                //el socket escucha y recibe en el packet
                socketUDP.receive(packetUDP);

                String mensajeRecibido = new String(packetUDP.getData());
                InetAddress direccionIP = socketUDP.getInetAddress();
                System.out.println(mensajeRecibido);
                System.out.println("De: " + direccionIP);

            }
        }catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
