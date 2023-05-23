package com.example.huiswerktechiteasycontrollerspringboot.repositories;

import com.example.huiswerktechiteasycontrollerspringboot.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long> {

    List<Television> findByBrandIgnoreCase(String brand);
    List<Television> findAllByOrderByBrand();
    List<Television> findAllByOrderByPrice();

}
