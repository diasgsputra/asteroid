package com.example.asteroid.repository;

import com.example.asteroid.entity.AsteroidMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AsteroidRepository extends JpaRepository<AsteroidMapping,Long> {

}
