# Kafka-Stream-Micro-Services

# Preparation
- Create a CSV file with two data fields: Category and Value. 
- Fill up CSV file with 1000 records and make sure that you will have minimum 3 different category in the data.
- Create a database table (Summary) that contain Summary of Category and Total Value fields.

# Task
- Create a Micro Service that will read CSV file line by line and push data to one Kafka topic. 
- Once data pushed to Kafka, create consumer micro services for each category that will consume data from Kafka and aggregate the value of each category that is consumed. 
- Sum up each value and insert/update into Summary table with category. 
- Summary table should look like one record per category and total value of each category. 
- Please note that use an approach that can read file faster and reduce number of insert/update on Summary table.


# Implementation

1. KAFKA Producer Service.
   - Spring Boot Microservice - Read Catagory data from CSV file
   - Category Data (read from CSV ) will get pushed to KAFKA Topic : **category_topic**
   
3. KAFKA Consumer Service
    - Subscribe to TOPIC - **category_topic** using KAFKA Stream , consume all Category data and Process/Grouping  each category and Value .
    - each Summary category along with total Value will get insert into SUMMARY table.
    - Database used here is in memory H2 Database.
    - When starting KAFKA Consumer, H@ Database table Summary is Empty

   ![image](https://user-images.githubusercontent.com/11199674/140743055-362456ed-113d-4123-9884-c2f25eee0c9d.png)      

     - Once KAFKA Procedure push the data into Topic, Data get inserted in Summary table.

     - Load CSV File and Click on Upload and Save

      ![image](https://user-images.githubusercontent.com/11199674/140745521-861e6c84-f6c5-4291-b9b2-0bcdaca84d50.png)
      
      - It will insert Summary of Category data in Summary table.

      
      ![image](https://user-images.githubusercontent.com/11199674/140819635-008e0369-8284-4ab6-8093-f7cc66eeadda.png)




