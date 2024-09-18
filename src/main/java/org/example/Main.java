package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SocketException {

        int PUERTO = 5000; //puerto en donde se le enviara los socketsUDP al server
        byte[] buffer = new byte[1024]; //datos que contendra el packetUDP
        try{
            //socket recibidor
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            while(true) {
                //packet de escucha
                DatagramPacket packetUDP = new DatagramPacket(buffer, buffer.length);
                //el socket escucha y recibe en el packet
                socketUDP.receive(packetUDP);
            }
        }catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}