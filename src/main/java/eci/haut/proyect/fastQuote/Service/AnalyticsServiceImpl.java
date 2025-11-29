package eci.haut.proyect.fastQuote.Service;

import eci.haut.proyect.fastQuote.Model.DTO.StatDTO;
import eci.haut.proyect.fastQuote.Repository.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnalyticsServiceImpl implements IAnalyticsService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Override
    public List<StatDTO> getTopShoes() {
        return cotizacionRepository.findTopRequestedShoes();
    }

    @Override
    public List<StatDTO> getCategoryStats() {
        // Retornamos la gráfica de "Ingresos por Zapato" ya que no hay categorías en el JSON
        return cotizacionRepository.findTopRevenueShoes();
    }
}