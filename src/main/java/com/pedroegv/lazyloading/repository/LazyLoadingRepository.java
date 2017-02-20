package com.pedroegv.lazyloading.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedroegv.lazyloading.domain.LazyLoading;

@Repository
public interface LazyLoadingRepository extends
		CrudRepository<LazyLoading, Long> {

}
