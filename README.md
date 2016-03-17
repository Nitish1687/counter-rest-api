Project setup.

1. Clone the repository.
2. go to the root folder of project and run gradle build.
3. to start project on local machine run gradle bootRun.

This project support two verb POST and GET.

POST : 

1. http://localhost:8080/counter-api/search  end point.
2. Basic authentication is enabled uwith username : nitish and password: shukla 
3. set Authorization header  with value :Basic bml0aXNoOnNodWtsYQ==
4. set Content-Type : application/json 


GET:

1. http://localhost:8080/counter-api/top/{count}
2. Basic authentication is enabled uwith username : nitish and password: shukla 
3. set Authorization header  with value :Basic bml0aXNoOnNodWtsYQ==
4. set Content-Type : application/json 


Technologies stack used :
1. Java 8
2. Spring Boot
3. Gradle
4. Junit 4
5. Check-style
