package com.org.utility.controller;

import com.org.utility.data.ProductCatalog;
import com.org.utility.model.ProductCatalogRequest;
import com.org.utility.model.ProductCatalogResponse;
import com.org.utility.services.ProductCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.org.utility.model.HelloModel;
import com.org.utility.model.HelloResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(value = "Product catalog", protocols = "http")
public class ProductCatalogController
{
	@Autowired
	ProductCatalogService productCatalogService;

	@GetMapping(value = "sayHello/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Say Hello Endpoint", response = String.class)
	public HelloResponse sayHello(@PathVariable("name") String name)
	{
		Integer    status = 200;
		HelloModel data   = new HelloModel("Hello " + name);
		return new HelloResponse(data, status);
	}

	@GetMapping(value = "/getAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Fetch All Products", response = List.class)
	public List<ProductCatalogResponse> fetchAllProducts()
	{
		return productCatalogService.fetchProductCatalog();
	}

	@PostMapping(value = "/productCatalog", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Save Product catalog", response = ProductCatalogResponse.class)
	public ProductCatalogResponse saveToProductCatalog(
			@Validated @RequestBody ProductCatalogRequest productCatalogRequest)
	{
		String productName     = productCatalogRequest.getProductName();
		String productCategory = productCatalogRequest.getProductCategory();
		String lastModifiedBy  = productCatalogRequest.getLastModifiedBy();
		Long   unitCost        = productCatalogRequest.getUnitCost();

		ProductCatalog productCatalog = new ProductCatalog(productName, productCategory, unitCost, LocalDateTime.now(),
				lastModifiedBy);
		return productCatalogService.saveProductToCatalog(productCatalog);
	}

	@PutMapping(value = "/productCatalog/{id}/{unitCost}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Unit Cost for product", response = String.class)
	public String updateUnitCostForProduct(@PathVariable("id") Long id, @PathVariable("unitCost") Long unitCost)
	{
		return productCatalogService.updateCostOfProduct(id, unitCost);
	}

	@DeleteMapping(value = "/productCatalog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete Product By Id")
	public void deleteProductById(@PathVariable("id") Long id)
	{
		productCatalogService.deleteProductCatalogById(id);
	}
}
