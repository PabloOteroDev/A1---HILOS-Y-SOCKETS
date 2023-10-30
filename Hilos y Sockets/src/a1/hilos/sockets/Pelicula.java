package a1.hilos.sockets;

class Pelicula {
    int id;
    String titulo;
    String director;
    double precio;

    public Pelicula(int id, String titulo, String director, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Titulo: " + titulo + ", Director: " + director + ", Precio: " + precio;
    }
}
