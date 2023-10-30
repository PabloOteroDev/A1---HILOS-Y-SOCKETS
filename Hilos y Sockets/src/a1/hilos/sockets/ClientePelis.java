package a1.hilos.sockets;

import java.io.*;
import java.net.*;

public class ClientePelis {
    private static final String IP_SERVER = "localhost";
    private static final int PUERTO = 2023;

    public static void main(String[] args) {
        try (Socket socket = new Socket(IP_SERVER, PUERTO);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
        	 System.out.println("Bienvenido a EL PLANETA DE LOS VÍDEOS");
        	
            String userInput;
            while (true) {
            	System.out.println("___APLICACIÓN DE VIDEOCLUB___");
                System.out.println("1. Consultar película por ID");
                System.out.println("2. Consultar película por título");
                System.out.println("3. Consultar películas por director");
                System.out.println("4. Añadir película");
                System.out.println("5. Salir de la aplicación");
                System.out.print("Seleccione una opción: ");

                userInput = stdIn.readLine(); //distintos casos de uso para generar el menú
                switch (userInput) {
                    case "1":
                        System.out.print("Introduce el ID de la película: ");
                        String id = stdIn.readLine();
                        out.println("CONSULTAR_ID:" + id);
                        System.out.println("Respuesta del servidor: " + in.readLine());
                        break;
                    case "2":
                        System.out.print("Introduce el título de la película: ");
                        String titulo = stdIn.readLine();
                        out.println("CONSULTAR_TITULO:" + titulo);
                        System.out.println("Respuesta del servidor: " + in.readLine());
                        break;
                    case "3":
                        System.out.print("Introduce el nombre del director: ");
                        String director = stdIn.readLine();
                        out.println("CONSULTAR_DIRECTOR:" + director);
                        String respuesta;
                        while (!(respuesta = in.readLine()).isEmpty()) {
                            System.out.println(respuesta);
                        }
                        break;
                    case "4":
                        System.out.print("Introduce el título de la película: ");
                        String tituloNuevo = stdIn.readLine();
                        System.out.print("Introduce el director de la película: ");
                        String directorNuevo = stdIn.readLine();
                        System.out.print("Introduce el precio de la película: ");
                        String precioNuevo = stdIn.readLine();
                        out.println("AGREGAR:" + tituloNuevo + "," + directorNuevo + "," + precioNuevo);
                        System.out.println("Respuesta del servidor: " + in.readLine());
                        break;
                    case "5":
                        out.println("SALIR");
                        System.out.println("¡Hasta luego! Te esperamos pronto en EL PLANETA DE LOS VÍDEOS");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al comunicarse con el servidor: " + e.getMessage());
        }
    }
}


