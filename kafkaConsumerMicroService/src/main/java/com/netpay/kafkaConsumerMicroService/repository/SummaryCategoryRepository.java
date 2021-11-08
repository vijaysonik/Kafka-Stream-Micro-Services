package com.netpay.kafkaConsumerMicroService.repository;

import com.netpay.kafkaConsumerMicroService.model.Summary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Repository for Saving/Updating Summary of Category.
 *
 * @author : Vijay Soni
 * @since  : November 21
 */
@Repository
@Transactional
public interface SummaryCategoryRepository extends CrudRepository<Summary,Integer> {

}
