package com.energy.demo.service;

import com.energy.demo.repository.EnergyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EnergyService {

    private final EnergyRepository energyRepository;

    public EnergyService(EnergyRepository energyRepository) {
        this.energyRepository = energyRepository;
    }

    public List<Map<String, Object>> getProductionBySource(int year) {
        return energyRepository.getProductionBySource(year);
    }

    public List<Map<String, Object>> getRenewableShareByRegion(int year) {
        return energyRepository.getRenewableShareByRegion(year);
    }

    public List<Map<String, Object>> getCountrySourceTrend(String country, String source) {
        return energyRepository.getCountrySourceTrend(country, source);
    }

    public List<Map<String, Object>> getTopProducersBySource(String source, int year, int limit) {
        return energyRepository.getTopProducersBySource(source, year, limit);
    }

    public List<Map<String, Object>> globalShareBySource(int year) {
        return energyRepository.globalShareBySource(year);
    }
}