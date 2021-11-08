package com.netpay.KafkaStreamMicroService.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Entity Class for Category (Data from CSV).
 *
 * @author : Vijay Soni
 * @since : November 21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category implements Serializable {

    @CsvBindByName
    private String categories;

    @CsvBindByName
    private Integer value;
}
