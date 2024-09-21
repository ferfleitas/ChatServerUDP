package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class clientUDP {


    public static void main(String[] args ) throws Exception{

        int PUERTO_SERVIDOR = 5000;
        byte buffer[] = new byte[1024];
        String USUARIO = "";
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        try{
            //Paso 1: el cliente envia su nombre y puerto al server para identificarse
            //creamos el socketUDP
            DatagramSocket socketUDP = new DatagramSocket();
            //asignamos la direccion del server
            InetAddress DIRECCION_SERVIDOR = InetAddress.getByName("localhost");
            System.out.println("Ingrese su Nombre de Usuario: ");
            USUARIO = inFromUser.readLine();
            buffer = USUARIO.getBytes();
            DatagramPacket packetUDP = new DatagramPacket(buffer, buffer.length, DIRECCION_SERVIDOR, PUERTO_SERVIDOR);
            socketUDP.send(packetUDP);

            //Paso 2: se queda escuchando por los mensajes para imprimirlos
            while(true){
                //limpiamos el buffer
                buffer = new byte[1024];
                //packet de escucha
                packetUDP = new DatagramPacket(buffer, buffer.length);
                //el socket escucha y recibe en el packet
                socketUDP.receive(packetUDP);
                String mensajeRecibido = new String(packetUDP.getData(),0,packetUDP.getLength());
                System.out.println(mensajeRecibido);
            }

        }catch (SocketException ex) {
            throw new RuntimeException(ex);
        }

    }
}
