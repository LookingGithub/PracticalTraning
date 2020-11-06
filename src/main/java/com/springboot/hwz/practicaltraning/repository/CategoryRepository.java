package com.springboot.hwz.practicaltraning.repository;

import com.springboot.hwz.practicaltraning.common.E3Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<E3Result,Integer> {
}
