package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

public class serverUDP {
//    static class user {
//        private final String username;
//        private final String ipPort;
//
//        public user(String username, String ipPort) {
//            this.username = username;
//            this.ipPort = ipPort;
//        }
//        public String getUsername() {
//            return username;
//        }
//        public String getIpPort() {
//            return ipPort;
//        }
//    }
    //hashmap para guardar IP:PUERTO , USUARIO
    static HashMap<String, String> clients1 = new HashMap<>();
    static HashMap<String, String> clients2 = new HashMap<>();
    public static void main(String[] args) throws SocketException {


        int PUERTO = 5000; //puerto en donde se le enviara los socketsUDP al server
        byte[] buffer = new byte[1024]; //datos que contendra el packetUDP

        try{
            //socket recibidor
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            System.out.println("Servidor conectado en el puerto " + PUERTO);

            while(true) {
                //limpiamos el buffer
                buffer = new byte[1024];
                //packet de escucha
                DatagramPacket packetUDP = new DatagramPacket(buffer, buffer.length);
                //el socket escucha y recibe el paquete
                socketUDP.receive(packetUDP);

                InetAddress ip = packetUDP.getAddress();
                int port = packetUDP.getPort();
                String ipPort = ip.getHostAddress() + ":" + port;
                String message = new String(packetUDP.getData(), 0, packetUDP.getLength()).trim();

                //si no se encuentra en la tabla, significa que es un nuevo cliente, entonces lo unico
                //que recibimos de mensaje es su nombre
                if(!clients1.containsKey(ipPort)) {

                    clients1.put(ipPort, message);
                    clients2.put(message, ipPort);
                    System.out.println("Cliente nuevo: " + ipPort + " " + message);
                    continue;
                }
                //si esta entre los clientes se procede a enviar su mensaje
                sendMessage(packetUDP, socketUDP);


            }
        }catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private static void sendMessage(DatagramPacket packetUDP, DatagramSocket socketUDP) throws IOException {
        //obtener la ip y el puerto del origen
        InetAddress ip = packetUDP.getAddress();
        int port = packetUDP.getPort();
        String ipPort = ip.getHostAddress() + ":" + port;

        //obtener los datos del paquete recibido
        String dataReceived = new String(packetUDP.getData(), 0, packetUDP.getLength());
        System.out.println(dataReceived);
        //extraer el destinatario y el mensaje
        String destinationClientName = dataReceived.split(":")[0];
        String message = dataReceived.split(":")[1].trim();


        //buscaar la IP y el puerto del destinatario en la tabla
        String destinationIpPort = clients2.get(destinationClientName);
        System.out.println(destinationIpPort);
        if (destinationIpPort == null) {
            System.out.println("El destinatario " + destinationClientName + " no está conectado.");
            return;
        }

        //separar IP y puerto del destinatario
        String[] destinationParts = destinationIpPort.split(":");
        if (destinationParts.length != 2) {
            System.out.println("Dirección del cliente incorrecta.");
            return;
        }
        InetAddress destinationIp = InetAddress.getByName(destinationParts[0].trim().replace("/", ""));
        int destinationPort = Integer.parseInt(destinationParts[1]);

        //crear el mensaje a enviar
        String fullMessage = clients1.get(ipPort) + ":" + message;
        byte[] messageBytes = fullMessage.getBytes();

        //crear el paquete UDP para enviar al destinatario
        DatagramPacket sendPacket = new DatagramPacket(messageBytes, messageBytes.length, destinationIp, destinationPort);

        //enviar el paquete
        socketUDP.send(sendPacket);

        System.out.println("Mensaje enviado de " + ipPort + " a " + destinationClientName + ": " + message);
    }

}
