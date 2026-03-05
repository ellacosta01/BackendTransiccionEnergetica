package com.energy.demo.controller;

import com.energy.demo.service.EnergyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final EnergyService energyService;

    public EnergyController(EnergyService energyService) {
        this.energyService = energyService;
    }

    // 1) Producción total por fuente agrupada por región (año dinámico)
    @GetMapping("/production-by-source")
    public ResponseEntity<List<Map<String, Object>>> productionBySource(
            @RequestParam(defaultValue = "2021") int year
    ) {
        return ResponseEntity.ok(energyService.getProductionBySource(year));
    }

    // 2) % renovables vs fósil por región (año dinámico)
    @GetMapping("/renewable-share-by-region")
    public ResponseEntity<List<Map<String, Object>>> renewableShareByRegion(
            @RequestParam(defaultValue = "2021") int year
    ) {
        return ResponseEntity.ok(energyService.getRenewableShareByRegion(year));
    }

    // 3) Evolución de una fuente en un país (country + source dinámicos)
    @GetMapping("/trend")
    public ResponseEntity<List<Map<String, Object>>> countrySourceTrend(
            @RequestParam(defaultValue = "Colombia") String country,
            @RequestParam(defaultValue = "Solar") String source
    ) {
        return ResponseEntity.ok(energyService.getCountrySourceTrend(country, source));
    }

    // 4) Top N países productores por fuente y año (source + year + limit dinámicos)
    @GetMapping("/top-producers")
    public ResponseEntity<List<Map<String, Object>>> topProducersBySource(
            @RequestParam(defaultValue = "Wind") String source,
            @RequestParam(defaultValue = "2021") int year,
            @RequestParam(defaultValue = "10") int limit
    ) {
        // limit defensivo para que nadie pida 50000 y reviente tu BD
        int safeLimit = Math.max(1, Math.min(limit, 100));
        return ResponseEntity.ok(energyService.getTopProducersBySource(source, year, safeLimit));
    }

    // 5) Participación global por fuente (año dinámico)
    @GetMapping("/global-share")
    public ResponseEntity<List<Map<String, Object>>> globalShareBySource(
            @RequestParam(defaultValue = "2021") int year
    ) {
        return ResponseEntity.ok(energyService.globalShareBySource(year));
    }
}