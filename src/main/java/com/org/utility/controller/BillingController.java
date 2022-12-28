package com.org.utility.controller;

import com.org.utility.data.BillingCycle;
import com.org.utility.model.BillingRequest;
import com.org.utility.model.BillingResponse;
import com.org.utility.services.BillingCycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Product Catalog and Billing", protocols = "http")
public class BillingController
{
	@Autowired
	BillingCycleService billingCycleService;

	// Insert a new bill details on every single day
	@PostMapping(value = "/billDetails", produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Save Billing Cycle to DataBase", response = BillingResponse.class)
	public BillingResponse saveBillCycle(@Validated @RequestBody BillingRequest billingRequest)
	{
		return billingCycleService.saveBillingCycle(billingRequest);
	}

	// Update bill details if any
	@PutMapping(value = "/billDetails", produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Bill Details", response = List.class)
	public List<BillingCycle> updateBillingCycle(@Validated @RequestBody List<BillingCycle> billingCycle)
	{
		return billingCycleService.updateBillingCycle(billingCycle);
	}

	// fetch all bill details from 1st of month to present day
	@GetMapping(value = "/billDetails/{reportCategoryName}/{reportCategoryValue}", produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Fetch Bill Details for a day, week, month and year", response = BillingResponse.class)
	public BillingResponse fetchBillDetailsForCurrentMonth(
			@ApiParam(allowableValues = "DAILY, WEEK, MONTH, YEAR") @PathVariable("reportCategoryName") String reportCategoryName,
			@PathVariable("reportCategoryValue") String reportCategoryValue)
	{
		return billingCycleService.fetchBillingCycle(reportCategoryName, reportCategoryValue);
	}

	// delete any products from the bill details
	@DeleteMapping(value = "/billDetails/{id}", produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Deletes Bill details", response = BillingResponse.class)
	public BillingResponse deleteBillDetails(@PathVariable("id") Long id)
	{
		BillingResponse billingResponse = new BillingResponse();
		try
		{
			billingCycleService.deleteBillingCycle(id);
			billingResponse.setMessage("Data deleted successfully for Id : " + id);
		}
		catch (SQLException sqlException)
		{
			billingResponse.setErrorMessage(sqlException.getMessage());
		}
		catch (Exception e)
		{
			billingResponse.setErrorMessage(e.getMessage());
			throw new RuntimeException(e);
		}
		return billingResponse;
	}
}
