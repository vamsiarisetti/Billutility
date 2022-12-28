package com.org.utility.data;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "billing_cycle")
@Data
@NoArgsConstructor
public class BillingCycle
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "bill_date", nullable = false)
	private LocalDate billDate;

	@Column(name = "product_category", nullable = false)
	private String productCategory;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "product_quantity", nullable = false)
	private BigDecimal quantity;

	@Column(name = "unitPrice", nullable = false)
	private BigDecimal unitPrice;

	@Column(name = "totalPrice", nullable = false)
	private BigDecimal totalPrice;

	@Column(name = "last_modified_date")
	private LocalDateTime lastModifiedDate;

	@Column(name = "last_modified_by")
	private String lastModifiedBy;

//	public BillingCycle(Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8)
//	{}
}