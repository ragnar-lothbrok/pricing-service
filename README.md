# pricing-service
This service contains pricing related APIs.

# Swagger Spec
https://ragnar-lothbrok.github.io/pricing-swagger/

# Running Postgres in Docker
docker run --rm   --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 postgres

#CURL CALLS
curl -X GET "http://localhost:8080/lms/v1/price/course/1" -H "accept: application/json" -H "locale: INR"

curl -X GET "http://localhost:8080/lms/v1/price/course/1" -H "accept: application/json" -H "locale: USD"

#What is not covered
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
- CI/CD
    - Jenkins
    - Unit Test Cases
    - Sonar Integrations
    - Integration T%est Cases
    - Docker Image building and publish to artifactory

    
