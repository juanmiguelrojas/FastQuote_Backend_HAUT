package eci.haut.proyect.fastQuote.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection = "Cotizacion") // Debe ser igual al nombre en Mongo Compass
public class Cotizacion {

    @Id
    private String id;

    // En algunos docs se llama "correo", en otros "email".
    // Mapeamos "correo" que es el que traen los registros completos.
    private String correo;

    // ESTO ES LO IMPORTANTE: La lista de zapatos
    private List<ProductoDetalle> productos;

    public Cotizacion() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public List<ProductoDetalle> getProductos() { return productos; }
    public void setProductos(List<ProductoDetalle> productos) { this.productos = productos; }

    // Clase interna para mapear los objetos dentro del array "productos"
    public static class ProductoDetalle {
        @Field("nombre") // Mapea exactamente la clave "nombre" de tu JSON
        private String nombre;

        @Field("precio")
        private Double precio;

        @Field("stock")
        private Integer stock;

        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public Double getPrecio() { return precio; }
        public void setPrecio(Double precio) { this.precio = precio; }
        public Integer getStock() { return stock; }
        public void setStock(Integer stock) { this.stock = stock; }
    }
}