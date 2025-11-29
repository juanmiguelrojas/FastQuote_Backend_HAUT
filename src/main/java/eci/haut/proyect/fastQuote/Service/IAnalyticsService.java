package eci.haut.proyect.fastQuote.Service;

import eci.haut.proyect.fastQuote.Model.DTO.StatDTO;
import java.util.List;

public interface IAnalyticsService {

    /**
     * Obtiene el top de referencias de zapatos más buscados.
     * @return Lista de StatDTO con nombre y cantidad.
     */
    List<StatDTO> getTopShoes();

    /**
     * Obtiene la distribución de búsquedas por categoría.
     * @return Lista de StatDTO con categoría y cantidad.
     */
    List<StatDTO> getCategoryStats();
}