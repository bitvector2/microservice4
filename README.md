# microservice4
Dockerized Spring Boot / Maven starter kit

$ export SPRING_APPLICATION_JSON='{"post_service_url":"http://jsonplaceholder.typicode.com/posts"}'  

$ mvn clean package  

$ docker run -it --rm \\  
-p 8080:8080 \\  
-e SPRING_APPLICATION_JSON='{"post_service_url":"http://jsonplaceholder.typicode.com/posts"}' \\  
bitvector/microservice4:latest

-=-=-=-=-=-=-

In Postman do:

GET 127.0.0.1:8080/posts/meta

PATCH 127.0.0.1:8080/posts/4 with payload:

{
    "title": "1800Flowers",
    "body": "1800Flowers"
}

-=-=-=-=-=-=-

Using the following JSON feed:

http://jsonplaceholder.typicode.com/posts

Create REST endpoints that reads the above JSON feed using HTTP.  The service should perform the following:

Count endpoint
Tally the number of unique user Ids in the JSON and return in a JSON response.

Updated User List endpoint
Modify the 4th JSON array item, changing the title and body of the object to "1800Flowers".

Return the modified JSON object to the main Java class.

Return the modified JSON in the endpoint response.

Unit tests

Should be runnable via "mvn test"

Should be written to test all aspects of the application (include mock of the feed).

 

Requirements

Use Java 1.8+

Use Spring Boot

Use Maven

Use JUnit and Mockito or other mock testing framework

All dependencies should be publicly available or properly included with the project and referenced within the POM

Be creative, have fun and may the force be with you

