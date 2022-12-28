package com.org.utility.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BillingRequest
{
	private LocalDate     billDate;
	private String        billWeekDay;
	private List<Product> products;
}
