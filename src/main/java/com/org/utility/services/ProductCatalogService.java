package com.org.utility.services;

import com.org.utility.data.ProductCatalog;
import com.org.utility.model.ProductCatalogResponse;
import com.org.utility.repositories.ProductCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCatalogService
{

	@Autowired
	ProductCatalogRepository productCatalogRepository;

	public List<ProductCatalogResponse> fetchProductCatalog()
	{
		return productCatalogRepository.findAll().stream().map(p -> new ProductCatalog().toResponse(p))
				.collect(Collectors.toList());
	}

	public ProductCatalogResponse saveProductToCatalog(ProductCatalog productCatalog)
	{
		return productCatalog.toResponse(productCatalogRepository.save(productCatalog));
	}

	public void deleteProductCatalogById(Long id)
	{
		productCatalogRepository.deleteById(id);
	}

	public String updateCostOfProduct(Long id, Long unitCost)
	{
		try
		{
			productCatalogRepository.updateUnitCost(id, unitCost);
			return String.format("Updated cost for product Id : %d", id);
		} catch (Exception exception)
		{
			return String.format("Unable to update cost for product id : %d : error caught : %s", id,
					exception.getMessage());
		}
	}
}
