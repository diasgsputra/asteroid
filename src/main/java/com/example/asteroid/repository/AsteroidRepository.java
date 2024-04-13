package com.example.asteroid.repository;

import com.example.asteroid.entity.AsteroidMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AsteroidRepository extends JpaRepository<AsteroidMapping,Long> {
  @Query(value = "SELECT * FROM asteroids WHERE `distance` <= :distance ORDER BY 'distance' ASC", nativeQuery = true)
  List<AsteroidMapping> getAsteroidByDistance(Double distance);
  @Query(value = "SELECT COUNT(*) FROM asteroids WHERE `distance` <= :distance", nativeQuery = true)
  Long countAsteroidByDistance(Double distance);
}
