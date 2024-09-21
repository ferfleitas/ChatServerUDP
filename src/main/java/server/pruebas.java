package server;

public class pruebas {

    public static void main(String[] args) {

        String m = "USUARIO:MENSAJE";
        String usuario = m.split(":")[0];
        String mensaje = m.split(":")[1];
        System.out.println(usuario);
        System.out.println(mensaje);
    }
}
