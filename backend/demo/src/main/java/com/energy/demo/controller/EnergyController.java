package com.energy.demo.controller;

import com.energy.demo.service.EnergyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//Expongo los datos sql a traves fe urls y que el front los pueda consumir seimpre va en formato Json
@RestController
@RequestMapping("/api/energy")
@Tag(name = "Controlador de Energía", description = "Endpoints para consultas estadísticas de energía global y regional")
public class EnergyController {

    @Autowired // Inyección del servicio de energía pa' manejar la data
    private EnergyService energyService;

    /**
      Aqui tenemos la produccion total x tipo de fuente en un año, agrupada por regiones
     * Este endpoint devuelve la sumatoria de TWh por fuente y region pa' el año 2021.

     */
    @Operation(summary = "Obtener producción por fuente", description = "Devuelve la sumatoria de TWh por fuente y región para el año 2021")
    @GetMapping("/production-by-source")
    public ResponseEntity<List<Map<String, Object>>> getProductionBySource() {
        return ResponseEntity.ok(energyService.getProductionBySource());
    }

    /**
     El % de energía renovable en el consumo electrico total x región
     * Aqui se muestra la participación o fraccion de % de energías limpias en las cada region del mundo.
     */
    @Operation(summary = "Participación de Renovables", description = "Muestra el porcentaje de energía renovable por regiones")
    @GetMapping("/renewable-share-regions")
    public ResponseEntity<List<Map<String, Object>>> getRenewableShareByRegion() {
        return ResponseEntity.ok(energyService.getRenewableShareByRegion());
    }

    /**
    Análisis de Transición Energética: Aqui se ve la tendencia de generación solar Y % Total de Renovables en Colombia o cuaalquier pais
     *solo se cruzan los datos de generacion (TWh) y que porcion de  % pa' lograr analizar la evolución histórica del pais que se seleccionó..
     */
    @Operation(summary = "Tendencia Solar en Colombia", description = "Analiza la evolución histórica de generación solar y renovables en Colombia")
    @GetMapping("/colombia-solar-trend")
    public ResponseEntity<List<Map<String, Object>>> getColombiaSolarTrend() {
        return ResponseEntity.ok(energyService.getColombiaSolarTrend());
    }

    /**
     Top DE LOS 10 países con mayor producción eólica
     * Aqui se devuelve un ranking de los 10 países líderes en generación de energía basada en viento pa' 2021.
     */
    @Operation(summary = "Top 10 Productores Eólicos", description = "Ranking de los 10 países líderes en generación eólica para 2021")
    @GetMapping("/top-wind-producers")
    public ResponseEntity<List<Map<String, Object>>> getTopWindProducers() {
        return ResponseEntity.ok(energyService.getTopWindProducers());
    }
}