package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class senderUDP {
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
            System.out.println("Informacion enviada al servidor!");
            //Paso 2: envia mensajes
            while(true){
                buffer = new byte[1024];
                System.out.println("Ingrese su Mensaje de la siguiente forma: ");
                System.out.println("USUARIODESTINO:MENSAJE");
                String MENSAJE = inFromUser.readLine();
                buffer = MENSAJE.getBytes();
                packetUDP = new DatagramPacket(buffer, buffer.length, DIRECCION_SERVIDOR, PUERTO_SERVIDOR);
                socketUDP.send(packetUDP);


            }

        }catch (SocketException ex) {
            throw new RuntimeException(ex);
        }

    }
}
