package com.org.utility.repositories;

import com.org.utility.data.ProductCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, Long>
{
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE product_catalog SET unit_cost=:unitCost WHERE id=:id", nativeQuery = true)
	void updateUnitCost(Long id, Long unitCost);
}
