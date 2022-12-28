package com.org.utility.repositories;

import com.org.utility.data.BillingCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillingCycleRepository extends JpaRepository<BillingCycle, Long>
{
	@Query(nativeQuery = true, value = "SELECT * FROM billing_cycle WHERE bill_date BETWEEN :fromDate AND :toDate")
	List<BillingCycle> findBillBetweenDates(LocalDate fromDate, LocalDate toDate);

	//	@Query(nativeQuery = true, value = "")
	//	List<BillingCycle> updateBillDetailsByDate();
}
