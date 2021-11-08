package com.netpay.KafkaStreamMicroService.helper;

import com.netpay.KafkaStreamMicroService.model.Category;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper Class to deal with CSV File Value and Map into POJO Object.
 */
public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Categories", "Value"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    /**
     * Parse CSV File and set values in Category POJO.
     *
     */
    public static List<Category> csvToCategory(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Category> categories = new ArrayList<Category>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Category category = new Category(
                        csvRecord.get("Categories"),
                        Integer.valueOf(csvRecord.get("Value"))
                );
               categories.add(category);
            }

            return categories;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
