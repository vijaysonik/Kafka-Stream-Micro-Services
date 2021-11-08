package com.netpay.KafkaStreamMicroService.service;


import com.netpay.KafkaStreamMicroService.model.Category;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Service class for CSV to Object Mapping.
 */
@Service
public class CSVService {

    public static final Logger Logger = LoggerFactory.getLogger(CSVService.class);

    @Autowired
    private KafkaTemplate<String,Category> kafkaTemplate;

    private static final String TOPIC="category_topic";

    /**
     * Parse the CSV File and map into Object fields.
     * @param file
     * @param model
     * @return
     */
    public String parseCSV(MultipartFile file, Model model) {



        String status = "FAILED";
        // parse CSV file to create a list of `User` objects
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            // create csv bean reader
            CsvToBean<Category> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Category.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of users
            List<Category> categories = csvToBean.parse();

           //Stream Category to KAFKA Topic

            categories.stream().forEach(category -> {
                Logger.info("Category => "+category.getCategories());
                kafkaTemplate.send(TOPIC,category);
            });

            // save users list on model
            model.addAttribute("users", categories);
            model.addAttribute("status", true);

            status = "SUCCESS";

        } catch (Exception ex) {
            model.addAttribute("message", "An error occurred while processing the CSV file.");
            model.addAttribute("status", false);
            //TODO - Add Logger Error
            ex.printStackTrace();
        }

        return status;
    }
}

