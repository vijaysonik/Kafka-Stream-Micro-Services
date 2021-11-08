package com.netpay.KafkaStreamMicroService.controller;

import com.netpay.KafkaStreamMicroService.helper.CSVHelper;
import com.netpay.KafkaStreamMicroService.model.Category;
import com.netpay.KafkaStreamMicroService.model.ResponseMessage;
import com.netpay.KafkaStreamMicroService.service.CSVService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for CSV File Upload.
 */

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    CSVService csvService;

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        String status = "Failed";
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            status = csvService.parseCSV(file,model);

        }

        return status;
    }

}
