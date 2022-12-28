package com.org.utility.model;

import com.org.utility.data.BillingCycle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product
{
	String     productName;
	String     productCode;
	BigDecimal unitPrice;
	BigDecimal totalPrice;
	BigDecimal quantity;
	String     category;

	public Product fromBillingCycle(BillingCycle billingCycle)
	{
		return new Product(billingCycle.getProductName(), billingCycle.getProductCategory(),
				billingCycle.getUnitPrice(), billingCycle.getTotalPrice(), billingCycle.getQuantity(),
				billingCycle.getProductCategory());
	}
}
