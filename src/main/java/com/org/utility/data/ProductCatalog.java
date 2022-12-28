package com.org.utility.data;

import com.org.utility.model.ProductCatalogResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_catalog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCatalog
{
	public ProductCatalog(String productName, String productCategory, Long unitCost, LocalDateTime lastModifiedDate,
			String lastModifiedBy)
	{
		this.productName      = productName;
		this.productCategory  = productCategory;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy   = lastModifiedBy;
		this.unitCost         = unitCost;
	}

	public ProductCatalogResponse toResponse(ProductCatalog productCatalog)
	{

		return new ProductCatalogResponse(productCatalog.getId(), productCatalog.getProductName(),
				productCatalog.getProductCategory(), productCatalog.getUnitCost(), productCatalog.getLastModifiedDate(),
				productCatalog.getLastModifiedBy());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "product_category", nullable = false)
	private String productCategory;

	@Column(name = "unit_cost", nullable = false)
	private Long unitCost;

	@Column(name = "last_modified_date")
	private LocalDateTime lastModifiedDate;

	@Column(name = "last_modified_by")
	private String lastModifiedBy;

}
