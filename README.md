# pricing-service
This service contains pricing related APIs.

# Swagger Spec
https://ragnar-lothbrok.github.io/pricing-swagger/

# Running Postgres in Docker
docker run --rm   --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 postgres

#What is not covered
- Unit Test Cases
    - Mockito, JUnit
- Integration Test Cases
    - Cucumber, Gherkin, Rest Template
- Postman Collections
    - Postman
- Load Testing Scripts
    - JMeter, Postman Runner
- Micrometer Integration
    - Prometheus, Grafana
- Convert logging to Json
    - To integrate with Splunk
- Security - Database SSL Mode needs to ne enabled in Stage/Production Environment.
    

    
