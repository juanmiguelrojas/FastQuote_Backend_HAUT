package eci.haut.proyect.fastQuote.Model.DTO;

public class StatDTO {
    private String label; // Puede ser el nombre del zapato o la categoría
    private Integer count; // Cantidad de veces que aparece

    public StatDTO(String label, Integer count) {
        this.label = label;
        this.count = count;
    }

    // Getters y Setters
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
}