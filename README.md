#CS: From Zero To Hero
This is a simple web-application that allows you to purchase a ticket to the intensive course. The topics of the courses
are related to various fields of computer science, and the main goal is to provide the most useful knowledge in the shortest
term possible.

##Structure

#### This project has multi-layer architecture and consists of:
* Database layer
* DAO layer
* Service layer
* Controller layer

Communication between back-end and front-end is performed via DTO and JSON. 
## Available functionality

###User
* Register in the system
* Log in to the system
* Browse available topics
* Browse available intensive courses
* Add multiple tickets to the shopping cart
* Place an order

###Admin

* View the information about registered users
* Add new bootcamps
* Add new courses
* Add new topics
* Delete courses, topics or bootcamps
* Modify existing courses, topics or bootcamps

## Technologies

* Java 11
* Maven 
* Hibernate ORM
* PostgreSQL
* Spring MVC
* Spring Security
* Apache Tomcat

## Requirements for launching the application

1. Download and install JDK 11 or later
2. Download and install Apache Tomcat web server
3. Download and install PostgreSQL.
4. Configure the database connection properties in the **db.properties** file
* user: "your username"
* password: "your password"
* db.url=jdbc:mysql://localhost/*<your_database>*?currentSchema=*<your_schema>*
5. Run a project using the IDE