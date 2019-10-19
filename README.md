
# Cashier Tech Test 


We would like you to develop a RESTful Web Service in Java. The goal of this exercise is to get an idea of the code you would produce if you were working at Gamesys, therefore **your solution should be of production quality**. 

## Requirements: 


1. ​Must use Java. 
2. Should use a build & dependency management tool e.g. Maven or Gradle. 
3. ​We should be able to compile and run your tests e.g. mvn clean verify 
4. ​Please include instructions for running the application. 


## Problem: 

Develop a Registration Service that implements a [**/register​**] endpoint taking a JSON body.

### Required data & validation:


- Username - alphanumeric, no spaces
- ​Password – min length 4, at least one upper case letter & number
- DoB (Date of Birth) - ISO 8601 format
- Payment Card Number – between 15 and 19 digits 

### Expected responses:


- If the request body fails to conform to any of the basic validation checks return HTTP Status code: 400
- Reject registrations if the user is under the age of 18 and return HTTP Status code: 403
- If the username has already been used reject the request and return HTTP Status code: 409
- A successful registration should return HTTP Status code: 201
 
### Example request: 
```sh
curl -X POST \ [http://127.0.0.1:8080/register](http://127.0.0.1:8080/register)  
-H 'Content-Type: application/json' \ -d '{ "username": "BobFrench", "password": "Password1", "dob": "1980-02-21", "paymentCardNumber": "349293081054422" }' 
```
#### Optional extra: 
On start-up allow a list of blocked payment issuer identification numbers to be provided. The issuer identification number (IIN) is the first 6 digits of the payment card’s number. If the IIN is blocked registration should fail returning HTTP Status code: 406

#Instructions to run and test this service

####Build service
```sh
mvn clean package
```

####Verify Test Cases
```sh
mvn verify
```
####Start Service with Default Profile
Logs are appended to console when service is started with **default** profile. Using _Console-Appender_ for non prod.
```sh
mvn spring-boot:run
```

####Start Service with Prod Profile
Logs are appended to **registration.log** file when service is started with **Prod** profile. Using _Rolling-File-Appender"_ for Prod.
```sh
mvn spring-boot:run -Dspring-boot.run.profiles=foo
```

mvn spring-boot:run -Dspring-boot.run.profiles=foo


##TODO:
- User service test cases
- Coverage atleast 90%
- Initial loading of IIN numbers
- Error messages from properties file
- Cleanup pom.xml
- Analyze-Inspect
- Run with coverage
- spring security