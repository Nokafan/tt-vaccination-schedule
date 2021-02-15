"# Vaccination-Schedule"
API для відображення виконаних та майбутніх щеплень з можливістю внесення нових даних.

In project has been used:

JDK 14 java version 11

MySql 8.0 DB

Tomcat 9.0.37

Spring Boot 2.4.2

Lombok

Log4j

Spring security

JWT authentication and authorization based on auth0 bearer token

Jackson jason mapping

Spring boot cache

Maven checkstyle plugin


Before start make sure that you have MySql DB and MySql Workbench on your pc.

For the beginning download project from github, open it in your IDE and change “username” and “password” in “application.properties” file to your values that you are using for connection to your DB.

Use intit_db.sql in “resources” directory, copy containing data, and run in Workbench to create schema, check is it was created, it should be named as “vaccination_schedule”.

Download and install Tomcat.

Download and add Lombok plugin if you do not have it installed yet.
Annotation processor in our IDE should be enabled (Lombok plugin require it).

Download and install Postman, for testing api use data from file in resource directory named vaccination.postman_collection.json, you can import it directly to postman.

