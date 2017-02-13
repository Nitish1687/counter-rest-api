Project setup.

1. Clone the repository.
2. go to the root folder of project and run gradle build.
3. to start project on local machine run gradle bootRun. 
    or you can start from CounterApplication.java File

This project support two verb POST and GET.

POST : 
  Below Endpoint is for ADMIN, can use to update a data in backend.
1. http://localhost:8080/counter-api/search  end point.
2. Basic authentication is enabled uwith username : nitish and password: shukla 
3. set Authorization header  with value :Basic bml0aXNoOnNodWtsYQ==
4. set Content-Type : application/json 

Note: here i have not written code to update the data in file. can be done in future.


GET:

   Below Endpoint is used by anyone and can search word with there top count.
1. http://localhost:8080/counter-api/top/{count}
2. Basic authentication is enabled uwith username : nitish and password: shukla 
3. set Authorization header  with value :Basic bml0aXNoOnNodWtsYQ==
4. set Content-Type : application/json 

Note : Document full of words, which will be look to search the text given by user must be present on location(our system) at 
        /Users/nsm1211/Downloads/code
       If you want to replace this path with your machine path please make sure to change the same on  application.yml 
       

Technologies stack used :
1. Java 8
2. Spring Boot
3. Gradle
4. Junit 4
5. Check-style
