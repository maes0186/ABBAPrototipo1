package com.conexia.saludcoop.common.entity.maestro;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface I18nable<T,S extends Serializable> extends PagingAndSortingRepository<T, S> {

}
