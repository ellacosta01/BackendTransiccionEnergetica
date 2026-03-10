package com.energy.demo.controller;

import com.energy.demo.service.EnergyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Controlador REST que expone consultas analíticas sobre datos de energía.
@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final EnergyService energyService;

    public EnergyController(EnergyService energyService) {
        this.energyService = energyService;
    }

    @Operation(
        summary = "Producción total de energía renovable por fuente y región",
        description = "Obtiene la producción total de energía renovable por tipo de fuente en un año específico, agrupada por regiones."
    )
    @GetMapping("/production-by-source")
    public ResponseEntity<List<Map<String, Object>>> productionBySource(
            @RequestParam(defaultValue = "2021") int year
    ) {
        return ResponseEntity.ok(energyService.getProductionBySource(year));
    }

    @Operation(
        summary = "Porcentaje de energía renovable en el consumo eléctrico por región",
        description = "Calcula el porcentaje de participación de la energía renovable frente a fuentes fósiles en cada región para un año específico."
    )
    @GetMapping("/renewable-share-by-region")
    public ResponseEntity<List<Map<String, Object>>> renewableShareByRegion(
            @RequestParam(defaultValue = "2021") int year
    ) {
        return ResponseEntity.ok(energyService.getRenewableShareByRegion(year));
    }

    @Operation(
        summary = "Tendencia de producción o capacidad por país y fuente",
        description = "Obtiene la evolución a lo largo de los años de una fuente de energía específica para un país determinado, por ejemplo energía solar en Colombia."
    )
    @GetMapping("/trend")
    public ResponseEntity<List<Map<String, Object>>> countrySourceTrend(
            @RequestParam(defaultValue = "Colombia") String country,
            @RequestParam(defaultValue = "Solar") String source
    ) {
        return ResponseEntity.ok(energyService.getCountrySourceTrend(country, source));
    }

    @Operation(
        summary = "Top países productores por fuente energética",
        description = "Obtiene el ranking de los países con mayor producción de una fuente de energía específica en un año determinado. Por defecto muestra los 10 primeros."
    )
    @GetMapping("/top-producers")
    public ResponseEntity<List<Map<String, Object>>> topProducersBySource(
            @RequestParam(defaultValue = "Wind") String source,
            @RequestParam(defaultValue = "2021") int year,
            @RequestParam(defaultValue = "10") int limit
    ) {
        int safeLimit = Math.max(1, Math.min(limit, 100));
        return ResponseEntity.ok(energyService.getTopProducersBySource(source, year, safeLimit));
    }

    @Operation(
        summary = "Participación global de las fuentes de energía",
        description = "Lista todas las fuentes de energía y su participación en el consumo o producción total a nivel global para un año específico."
    )
    @GetMapping("/global-share")
    public ResponseEntity<List<Map<String, Object>>> globalShareBySource(
            @RequestParam(defaultValue = "2021") int year
    ) {
        return ResponseEntity.ok(energyService.globalShareBySource(year));
    }
}