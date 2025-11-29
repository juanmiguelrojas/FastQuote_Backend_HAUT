package eci.haut.proyect.fastQuote.Controller;

import eci.haut.proyect.fastQuote.Model.DTO.StatDTO;
import eci.haut.proyect.fastQuote.Service.IAnalyticsService;
// import eci.haut.proyect.fastQuote.util.JwtUtil; // Descomenta cuando tengas tu clase JwtUtil
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend
public class AnalyticsController {

    @Autowired
    private IAnalyticsService analyticsService;

    /* @Autowired
    private JwtUtil jwtUtil; // Asumiendo que tienes tu utilidad de tokens
    */

    @GetMapping("/top-shoes")
    public ResponseEntity<List<StatDTO>> getTopShoes(@RequestHeader(value = "Authorization", required = false) String token) {
        // VALIDACIÓN DE SEGURIDAD (Descomenta cuando tengas el JwtUtil listo)
        /*
        if (token == null || !jwtUtil.validateToken(token)) {
             return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        */

        List<StatDTO> stats = analyticsService.getTopShoes();
        if (stats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<StatDTO>> getCategoryStats(@RequestHeader(value = "Authorization", required = false) String token) {
        /*
        if (token == null || !jwtUtil.validateToken(token)) {
             return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        */

        List<StatDTO> stats = analyticsService.getCategoryStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}