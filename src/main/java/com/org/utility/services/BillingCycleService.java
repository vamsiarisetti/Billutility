package com.org.utility.services;

import com.org.utility.data.BillingCycle;
import com.org.utility.enums.ReportCategoryEnum;
import com.org.utility.model.BillingModel;
import com.org.utility.model.BillingRequest;
import com.org.utility.model.BillingResponse;
import com.org.utility.model.Product;
import com.org.utility.repositories.BillingCycleRepository;
import com.org.utility.utils.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * This service class will have the logic to Save, Update, Fetch and Deleting Bill Details
 */
@Service
public class BillingCycleService
{
	@Autowired
	BillingCycleRepository billingCycleRepository;

	Logger logger = LoggerFactory.getLogger(BillingCycleService.class);

	/**
	 * Saving Bill Details
	 *
	 * @param billingRequest
	 * @return
	 */
	public BillingResponse saveBillingCycle(BillingRequest billingRequest)
	{
		AtomicReference<LocalDate> billDate = new AtomicReference<>(LocalDate.now());
		// build billing Cycle from billing request object
		List<BillingCycle> billingCycles = billingRequest.getProducts().stream().map(product -> {
			billDate.set(billingRequest.getBillDate());
			BillingCycle billingCycle = new BillingCycle();
			billingCycle.setBillDate(billingRequest.getBillDate());
			billingCycle.setProductCategory(product.getCategory());
			billingCycle.setProductName(product.getProductName());
			billingCycle.setQuantity(product.getQuantity());
			billingCycle.setUnitPrice(product.getUnitPrice());
			billingCycle.setTotalPrice(product.getTotalPrice());
			billingCycle.setLastModifiedDate(LocalDateTime.now());
			billingCycle.setLastModifiedBy("");
			return billingCycle;
		}).collect(Collectors.toList());

		List<BillingCycle> billingCycleList = billingCycleRepository.saveAll(billingCycles);

		return getBillingResponse(billingCycleList, ReportCategoryEnum.DAILY.name(), String.valueOf(billDate.get()));
	}

	/**
	 * update billingdetails based on Date need to write new query to do this
	 *
	 * @param billingCycle
	 * @return List<BillingCycle>
	 */
	public List<BillingCycle> updateBillingCycle(List<BillingCycle> billingCycle)
	{
		return billingCycleRepository.saveAll(billingCycle);
	}

	/**
	 * Fetch BillingDetails for a day or week or month or year
	 *
	 * @return BillingResponse
	 */
	public BillingResponse fetchBillingCycle(String reportCategoryName, String reportCategoryValue)
	{
		LocalDate fromDate;
		LocalDate toDate;

		if (ReportCategoryEnum.DAILY.name().equalsIgnoreCase(reportCategoryName))
		{
			fromDate = DateUtility.getCurrentDate();
			toDate   = fromDate;
		} else if (ReportCategoryEnum.WEEK.name().equalsIgnoreCase(reportCategoryName))
		{
			// write logic for week
			List<LocalDate> currentWeek = DateUtility.getCurrentWeek();
			fromDate = currentWeek.get(0);
			toDate   = currentWeek.get(1);
		} else if (ReportCategoryEnum.YEAR.name().equalsIgnoreCase(reportCategoryName))
		{
			List<LocalDate> currentYear = DateUtility.getCurrentYear();
			fromDate = currentYear.get(0);
			toDate   = currentYear.get(1);
		} else
		{
			List<LocalDate> currentMonth = DateUtility.getCurrentMonth();

			fromDate = currentMonth.get(0);
			toDate   = currentMonth.get(1);
		}
		// find records between fromDate and toDate
		logger.info("fetching Bill details from {} to {}", fromDate, toDate);
		List<BillingCycle> billBetweenDates = billingCycleRepository.findBillBetweenDates(fromDate, toDate);

		return getBillingResponse(billBetweenDates, reportCategoryName, reportCategoryValue);
	}

	public void deleteBillingCycle(Long id) throws SQLException
	{
		billingCycleRepository.deleteById(id);
	}

	/**
	 * Converts billingCycleList to BillingResponse Object
	 *
	 * @param billingCycleList
	 * @return BillingResponse
	 */
	private BillingResponse getBillingResponse(List<BillingCycle> billingCycleList, String reportCategoryName,
			String reportCategoryValue)
	{
		List<Product>           products   = null;
		Map<String, BigDecimal> categories = null;
		BigDecimal              totalExpense;

		if (ReportCategoryEnum.DAILY.name().equalsIgnoreCase(reportCategoryName))
		{
			// build products here
			products     = billingCycleList.stream().map(bill -> new Product().fromBillingCycle(bill))
					.collect(Collectors.toList());
			totalExpense = products.stream().map(Product::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else
		{
			categories   = billingCycleList.stream().collect(groupingBy(BillingCycle::getProductCategory,
					Collectors.reducing(BigDecimal.ZERO, BillingCycle::getTotalPrice, BigDecimal::add)));
			totalExpense = categories.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		}

		BillingModel billingModel = new BillingModel(reportCategoryName, reportCategoryValue, null, totalExpense,
				categories, products);
		return new BillingResponse(billingModel, null, null);
	}
}
