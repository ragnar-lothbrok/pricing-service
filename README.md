# pricing-service
This service contains pricing related APIs.

# Swagger Spec
https://ragnar-lothbrok.github.io/pricing-swagger/

### List of available APIs

API | Description
--- | ---
`GET /actuator/health` |  A default API used for detailed healthcheck.
`GET /lms/v1/price/course/1` | An API which is used to fetch Pricing details of a Course.


APIs can be accessed via curl command: 
- `curl -X GET "http://localhost:8080/lms/v1/price/course/1" -H "accept: application/json" -H "currency: INR"`
- `curl -X GET "http://localhost:8080/lms/v1/price/course/1" -H "accept: application/json" -H "currency: USD"`
- `curl -X GET "http://localhost:8080/lms/v1/price/course/1?inline=true" -H "accept: application/json" -H "currency: INR"`
- `curl -X GET "http://localhost:8080/lms/v1/price/course/1?inline=true" -H "accept: application/json" -H "currency: USD"`
- `curl -X GET "http://localhost:8080/actuator/health" -H "accept: application/json"`

### List of available Error Codes
Error Code | Http Status Code | Description
--- | --- | ---
`11200` |  400 | When any required parameter is missing from Request.
`11201` | 500 | When some unknown error occurrs in application.
`11202` | 404 | When requested resource is not present in Application.
`11203` | 404 | When Course Pricing for given Course is not Present.
`11204` | 500 | There is an error when communicating with Database.


### What is not covered
- Unit Test Cases
    - Mockito, JUnit
- Integration Test Cases
    - Cucumber, Gherkin, Rest Template
- Postman Collections
    - Postman
- Load Testing Scripts
    - JMeter, Postman Runner
- Monitoring
    - Micrometer Integration (Prometheus, Grafana)
- Logging
    - Convert logging to Json to integrate with Splunk
- Security
    - Database SSL Mode needs to ne enabled in Stage/Production Environment.
    - Sonar Integration
    - Access-Control-Allow-Origin needs to be set with actual value wherever application will be deployed.
    - Adding database credentials to vault (Separate service which will store all credentials.)
- CI/CD
    - Jenkins
    - Unit Test Cases
    - Sonar Integrations
    - Integration T%est Cases
    - Docker Image building and publish to artifactory
    
### Running Postgres in Docker
docker run --rm   --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 postgres
    
### Running Application
- Install Docker
- Start Postgres Docker Image using above command
- JDK 11
- Maven
- Clone repository (git clone https://github.com/ragnar-lothbrok/pricing-service.git)
- Go to directory pricing-service
- First Approach
    - mvn spring-boot:run
- Second Approach
    - mvn clean install
    - java -jar target/pricing-service-0.0.1-SNAPSHOT.jar
- Use either Curl or Swagger    

### Database Schema
https://github.com/ragnar-lothbrok/pricing-service/tree/master/src/main/resources/db/migration

### Docker Commands
- docker ps (Listing Containers)
- docker container stop <id> (Stopping container)
- docker container rm <id> (Removing Container)

# For Debugging
- Request Id is added as Part of Response in APIs. Which can be used to check the logs of specific API call.

# Terminologies
- Sale Price : Price on which we are selling Course. (Which will be shows on Description Page)
- Price : Actual Price of Course (Base Price)
- Total Price : Sale Price + Taxes (Which will be shows on Checkout Page)







    
