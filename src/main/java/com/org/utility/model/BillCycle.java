package com.org.utility.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BillCycle
{
	private List<BillingModel> billingModel;
	private BigDecimal         totalExpense; // sum of daily expenses
}
