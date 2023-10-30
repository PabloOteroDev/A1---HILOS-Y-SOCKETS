package a1.hilos.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorPelis {
    public static List<Pelicula> peliculas = new ArrayList<>(); //pense en usar un array pero la arraylist era mas sencilla
    public static int indicadorId = 1; //para empezar por la id 1

    public static void main(String[] args) {
        // Aqui va la lista de pelis
    	peliculas.add(new Pelicula(indicadorId++, "El Señor de los Anillos", "Peter Jackson", 20.05));
    	peliculas.add(new Pelicula(indicadorId++, "Blade Runner", "Ridley Scott", 20.49));
    	peliculas.add(new Pelicula(indicadorId++, "Pulp Fiction", "Quentin Tarantino", 5.00));
    	peliculas.add(new Pelicula(indicadorId++, "Dunkerque", "Christopher Nolan", 19.17));
    	peliculas.add(new Pelicula(indicadorId++, "El Laberinto del Fauno", "Guillermo del Toro", 19.39));

        try (ServerSocket serverSocket = new ServerSocket(2023)) {
            System.out.println("Servidor iniciado en el puerto 2023");
            while (true) { //tal como vimos en clase, mantenemos el servidor siempre "escuchando"
                Socket socket = serverSocket.accept();
                ClienteHandler cliente = new ClienteHandler(socket);
                cliente.start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

