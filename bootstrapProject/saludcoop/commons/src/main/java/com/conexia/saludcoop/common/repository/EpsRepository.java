package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.conexia.saludcoop.common.entity.maestro.Eps;


public interface EpsRepository extends PagingAndSortingRepository<Eps, Long> {

}
