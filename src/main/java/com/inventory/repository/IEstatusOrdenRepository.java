package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.EstatusOrden;

@Repository
public interface IEstatusOrdenRepository extends JpaRepository<EstatusOrden, Long>{

}
