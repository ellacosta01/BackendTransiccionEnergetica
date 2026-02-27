package com.energy.demo.repository;

import com.energy.demo.model.AppUser; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface EnergyRepository extends JpaRepository<AppUser, Long>{

    @Query(value = "SELECT e.name AS region, s.source_name, SUM(f.value_twh) AS total_twh " +
                   "FROM fact_generation_twh f " +
                   "JOIN dim_entity e ON f.entity_id = e.entity_id " +
                   "JOIN dim_source s ON f.source_id = s.source_id " +
                   "WHERE e.entity_type = 'region' AND f.year = 2021 " +
                   "GROUP BY e.name, s.source_name " +
                   "ORDER BY e.name, total_twh DESC", nativeQuery = true)
    List<Map<String, Object>> getProductionBySource();

    @Query(value = "SELECT e.name AS region, f.year, f.renewables_pct " +
                   "FROM fact_renewables_share_electricity f " +
                   "JOIN dim_entity e ON f.entity_id = e.entity_id " +
                   "WHERE e.entity_type = 'region' AND f.year = 2021 " +
                   "ORDER BY f.renewables_pct DESC", nativeQuery = true)
    List<Map<String, Object>> getRenewableShareByRegion();

    @Query(value = "SELECT e.name AS pais, f_gen.year, f_gen.value_twh AS generacion_solar_twh, " +
                   "f_share.renewables_pct AS porcentaje_renovable_total " +
                   "FROM fact_generation_twh f_gen " +
                   "JOIN fact_renewables_share_electricity f_share ON f_gen.entity_id = f_share.entity_id AND f_gen.year = f_share.year " +
                   "JOIN dim_entity e ON f_gen.entity_id = e.entity_id " +
                   "JOIN dim_source s ON f_gen.source_id = s.source_id " +
                   "WHERE e.name = 'Colombia' AND s.source_name = 'Solar' " +
                   "ORDER BY f_gen.year ASC", nativeQuery = true)
    List<Map<String, Object>> getColombiaSolarTrend();

    @Query(value = "SELECT e.name AS pais, f.value_twh " +
                   "FROM fact_generation_twh f " +
                   "JOIN dim_entity e ON f.entity_id = e.entity_id " +
                   "JOIN dim_source s ON f.source_id = s.source_id " +
                   "WHERE s.source_name = 'Wind' AND e.entity_type = 'country' AND f.year = 2021 " +
                   "ORDER BY f.value_twh DESC LIMIT 10", nativeQuery = true)
    List<Map<String, Object>> getTopWindProducers();
}