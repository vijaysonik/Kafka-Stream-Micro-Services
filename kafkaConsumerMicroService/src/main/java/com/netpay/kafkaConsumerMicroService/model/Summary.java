package com.netpay.kafkaConsumerMicroService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Entity Class for Summary.
 *
 * @author : Vijay Soni
 * @since : November 21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Summary {

    @Id
    /*@GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Integer id;*/
    private String summaryOfCategory;
    private Integer totalValue;

}