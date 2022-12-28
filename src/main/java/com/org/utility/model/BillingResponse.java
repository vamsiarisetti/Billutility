package com.org.utility.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillingResponse
{
	//	public BillingResponse (BillingModel data)
	//	{
	//		this.data = data;
	//	}

	private BillingModel data;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String       message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String       errorMessage;
}
