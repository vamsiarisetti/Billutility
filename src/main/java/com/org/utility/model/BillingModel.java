package com.org.utility.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class BillingModel //implements Comparable<BillingModel>
{
	//    List<BillingDates> billingDates;
	// total cost

	String     reportCategoryName;
	String     reportCategoryValue;
	String     reportCategoryFormat;
	BigDecimal totalExpense;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Map<String, BigDecimal> categories;    // in other weekly, yearly and monthly
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<Product>           products; // only daily

/*	LocalDate     date;
	String        weekDay;
	BigDecimal    dailyExpense;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<Category> categories;	// in other weekly, yearly and monthly
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<Product> products; // only daily

	@Override
	public int compareTo (BillingModel billingModel)
	{
		if (billingModel.getDate().isAfter(this.getDate()))
		{
			return 1;
		}
		else if (billingModel.getDate().isBefore(this.getDate()))
		{
			return -1;
		}
		return 0;
	}*/
}
