package com.netpay.kafkaConsumerMicroService.listener;

import com.netpay.kafkaConsumerMicroService.model.Category;
import com.netpay.kafkaConsumerMicroService.model.Summary;
import com.netpay.kafkaConsumerMicroService.service.SummaryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class KafkaConsumer {

    @Autowired
    private SummaryCategoryService service;

    private ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();

    @KafkaListener(topics="category_topic",groupId = "group_id",containerFactory = "kafkaListenerContainerFactory")
    public void consume(Category category){
        System.out.println("Consumed Message from KAFKA topic "+ category);


        if(map.containsKey(category.getCategories())){
            Integer newValue  = map.get(category.getCategories()) + category.getValue();
            map.put(category.getCategories(),newValue);
        }else{
            map.put(category.getCategories(),category.getValue());
        }

        System.out.println(map);


        map.entrySet().forEach(m -> {
            Summary summary = new Summary();
            summary.setSummaryOfCategory(m.getKey());
            summary.setTotalValue(m.getValue());
            service.saveSummaryData(summary);
        });
    }
}
