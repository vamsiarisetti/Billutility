package com.org.utility.model;

import com.org.utility.data.ProductCatalog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCatalogResponse
{
	//    private List<ProductCatalog> data;

	private Long          productId;
	private String        productName;
	private String        productCategory;
	private Long          unitCost;
	private LocalDateTime lastModifiedDate;
	private String        lastModifiedBy;

}
