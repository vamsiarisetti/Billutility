package com.org.utility.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductCatalogRequest
{
	private String productName;
	private String productCategory;
	private Long   unitCost;
	private String lastModifiedBy;
}
