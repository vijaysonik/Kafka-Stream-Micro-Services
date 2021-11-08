package com.netpay.kafkaConsumerMicroService.service;

import com.netpay.kafkaConsumerMicroService.model.Summary;
import com.netpay.kafkaConsumerMicroService.repository.SummaryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for Summary of Category.
 *
 * @author : Vijay Soni
 * @since  : November 21.
 */
@Service
public class SummaryCategoryService {

    @Autowired
    private SummaryCategoryRepository repository;


    public boolean saveSummaryData(Summary summary){
        System.out.println("Inserting/Updating table with Data of Category "+summary.getSummaryOfCategory());
         this.repository.save(summary);
         return true;
    }

}
