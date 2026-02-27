package com.energy.demo.service;

import com.energy.demo.repository.EnergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
//Centralizo las consultas de sql
@Service
public class EnergyService {

    @Autowired
    private EnergyRepository energyRepository;

    public List<Map<String, Object>> getProductionBySource() {
        return energyRepository.getProductionBySource();
    }

    public List<Map<String, Object>> getRenewableShareByRegion() {
        return energyRepository.getRenewableShareByRegion();
    }

    public List<Map<String, Object>> getColombiaSolarTrend() {
        return energyRepository.getColombiaSolarTrend();
    }

    public List<Map<String, Object>> getTopWindProducers() {
        return energyRepository.getTopWindProducers();
    }
}