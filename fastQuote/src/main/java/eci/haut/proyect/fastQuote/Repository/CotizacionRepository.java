package eci.haut.proyect.fastQuote.Repository;

import eci.haut.proyect.fastQuote.Model.Cotizacion;
import eci.haut.proyect.fastQuote.Model.DTO.StatDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CotizacionRepository extends MongoRepository<Cotizacion, String> {

    // GRÁFICA 1: Top Zapatos Más Vendidos (Por Cantidad)
    @Aggregation(pipeline = {
            // 1. FILTRO DE SEGURIDAD: Solo tomar docs que tengan el array "productos" y no esté vacío
            "{ '$match': { 'productos': { '$exists': true, '$not': { '$size': 0 } } } }",

            // 2. DESGLOSAR: Convertir el array en documentos individuales
            "{ '$unwind': '$productos' }",

            // 3. AGRUPAR: Contar cuántas veces aparece cada nombre de zapato
            "{ '$group': { '_id': '$productos.nombre', 'count': { '$sum': 1 } } }",

            // 4. ORDENAR: De mayor a menor
            "{ '$sort': { 'count': -1 } }",

            // 5. PROYECTAR: Darle formato para el DTO
            "{ '$project': { 'label': '$_id', 'count': 1, '_id': 0 } }"
    })
    List<StatDTO> findTopRequestedShoes();


    // GRÁFICA 2: Top Zapatos por Ingresos Potenciales (Suma de precios)
    // Usamos esto porque tus JSON actuales NO tienen "categoria", pero SI tienen "precio".
    @Aggregation(pipeline = {
            "{ '$match': { 'productos': { '$exists': true, '$not': { '$size': 0 } } } }",
            "{ '$unwind': '$productos' }",
            "{ '$group': { '_id': '$productos.nombre', 'totalRevenue': { '$sum': '$productos.precio' } } }",
            "{ '$sort': { 'totalRevenue': -1 } }",
            "{ '$limit': 5 }",
            "{ '$project': { 'label': '$_id', 'count': '$totalRevenue', '_id': 0 } }"
    })
    List<StatDTO> findTopRevenueShoes();
}