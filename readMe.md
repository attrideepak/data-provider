# Overview

This project is a Kotlin-based application that demonstrates the use of Spring Boot to create CRUD operations. These crud operations can be used to create, read, update, and delete data from a Mongo database.
We can use the endpoints exposed by the application in test automation frameworks for data driven testing..

# Technologies Used
Kotlin
Spring Boot
Mongo DB
Gradle
Docker
Mongoshell

# Setup
1. Clone the repository.
2. Install mongo db using docker `docker run -d -p 27017:27017 --name=mongo mongo:latest`
3. Install mongo sh to work with mongo db.
4. Make sure unique indexes are created where ever required.  
   check the indexes using `db.customer.getIndexes()`  
   create unique indexes using `db.collection.createIndex( { "field": 1 }, { unique: true } )`
5. Postman to execute APIs
6. Run the application and execute APIs.

# APIs Exposed
 1. Create Customer.  
      ```bash
    curl --location 'localhost:8080/v1/customer' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "Jack Dawson",
        "preferredName": "Jack",
        "email": "jack.nicolson@example.com",
        "mobile": "3234567890",
        "pincode": 123456,
        "metadata": {
            "env": "staging",
            "inUse": true,
            "tags": [
                "customerTest"
            ]
        }
    }'
    ```
2. Get Customer by Email.  
   ```bash
    curl --location 'localhost:8080/v1/customer/email?email=jack.dawson%40example.com'
    ```
3. Get Customer by Mobile.  
   ```bash
   curl --location 'localhost:8080/v1/customer/mobile?mobile=3234567890'`
   ```
4. Get Customer by Id.
    ```bash
   `curl --location 'localhost:8080/v1/customer/67167fa6bdfa1c1295ff021c'`
    ```
5. Get customers by tag.  
   ```bash
   `curl --location 'localhost:8080/v1/customer/tag?tag=customerTest'`
   ```
6. Update cusotmer by id.
   ```bash
   `curl --location --request PUT 'localhost:8080/v1/customer/67167fa6bdfa1c1295ff021c' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name": "Jack Dawson",
   "preferredName": "Jack",
   "email": "jack.nicolson@example.com",
   "mobile": "4234567890",
   "pincode": 123456,
   "metadata": {
   "env": "staging",
   "inUse": true,
   "tags": [
   "customerTest"
   ]
   }
   }'`
   ```
7. Delete customer by id. 
   ```bash
   `curl --location --request DELETE 'localhost:8080/v1/customer/67167fa6bdfa1c1295ff021c'`
   ```