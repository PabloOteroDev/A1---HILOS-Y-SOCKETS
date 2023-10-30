package a1.hilos.sockets;

import java.io.*;
import java.net.*;
import java.util.*;

class ClienteHandler extends Thread {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            String solicitud;
            while((solicitud = entrada.readLine()) != null) {
                String[] partes = solicitud.split(":", 2);
                String accion = partes[0];

                switch (accion) {
                    case "CONSULTAR_ID":
                        int id = Integer.parseInt(partes[1]);
                        consultarPorID(id);
                        break;
                    case "CONSULTAR_TITULO":
                        String titulo = partes[1];
                        consultarPorTitulo(titulo);
                        break;
                    case "CONSULTAR_DIRECTOR":
                        String director = partes[1];
                        consultarPorDirector(director);
                        break;
                    case "AGREGAR":
                        String[] datos = partes[1].split(",");
                        agregarPelicula(new Pelicula(ServidorPelis.indicadorId++, datos[0], datos[1], Double.parseDouble(datos[2])));
                        System.out.println("Película agregada exitosamente");
                        break;
                    case "SALIR":
                        socket.close();
                        return;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //aquí van las funciones del menú 
    
    private void consultarPorID(int id) {
        Pelicula peliculaEncontrada = null;
        for (Pelicula pelicula : ServidorPelis.peliculas) {
            if (pelicula.id == id) {
                peliculaEncontrada = pelicula;
                break;
            }
        }

        if (peliculaEncontrada != null) {
            salida.println(peliculaEncontrada.toString());
        } else {
            salida.println("No se encontró la película con el ID solicitado.");
        }
    }

    private void consultarPorTitulo(String titulo) {
        Pelicula peliculaEncontrada = null;
        for (Pelicula pelicula : ServidorPelis.peliculas) {
            if (pelicula.titulo.equalsIgnoreCase(titulo)) {
                peliculaEncontrada = pelicula;
                break;
            }
        }

        if (peliculaEncontrada != null) {
            salida.println(peliculaEncontrada.toString());
        } else {
            salida.println("No se encontró la película con el título solicitado.");
        }
    }

    private void consultarPorDirector(String director) {
        List<Pelicula> peliculasPorDirector = new ArrayList<>();
        for (Pelicula pelicula : ServidorPelis.peliculas) {
            if (pelicula.director.equalsIgnoreCase(director)) {
                peliculasPorDirector.add(pelicula);
            }
        }

        if (!peliculasPorDirector.isEmpty()) {
            peliculasPorDirector.forEach(p -> salida.println(p.toString()));
        } else {
            salida.println("No se encontraron películas para el director solicitado.");
        }
    }

    private synchronized void agregarPelicula(Pelicula pelicula) {
        ServidorPelis.peliculas.add(pelicula);
    }

}
