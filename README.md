# Myschool Registration Demo

Author: Jose Roberto Filho (jrcdsf@gmail.com)

## Project setup

- Clone Github

`$ git clone https://github.com/jrcdsf/myschool.git`

- Open project into IntelliJ IDE

- Run Docker Compose to start up the local services

`$ docker-compose up -d`

- Run MyschoolApplication on IntelliJ to start the application

- Run tests using Insomnia or Postman using the Endpoints below

- Stop MyschoolApplication on Intellij to stop the application

- Run Docker Compose to stop the local services

`$ docker-compose down`

## Endpoints

### Course Module

- Create course

POST http://localhost:8080/courses

BODY {
"name": "COURSE 001",
"description": "long course description"
}

- Update course

PATCH http://localhost:8080/courses

BODY {
"id": 1,
"name": "COURSE 001",
"description": "long course description edited"
}

- Delete course

DELETE http://localhost:8080/courses/1

- Find / list courses

GET http://localhost:8080/courses

GET http://localhost:8080/courses/1

GET http://localhost:8080/courses?name=COURSE%20002

### Student Module

- Create student

POST http://localhost:8080/students

BODY {
"name": "Student 2",
"birth": "25/03/2003",
"gender": "F"
}

- Update student

PATCH http://localhost:8080/students

BODY {
"id": 2,
"name": "Student 2 renamed",
"birth": "25/03/1975",
"gender": "M"
}

- Delete student

DELETE http://localhost:8080/students/1

- Find / list students

GET http://localhost:8080/students

GET http://localhost:8080/students/1

GET http://localhost:8080/students?name=Student%202

### Reports Module

- Filter all students with a specific course

GET http://localhost:8080/reports/students?course=COURSE%20002

- Filter all students without any courses

GET http://localhost:8080/reports/students?course=none

- Filter all courses for a specific student

GET http://localhost:8080/reports/courses?student_id=2

- Filter all courses without any students

GET http://localhost:8080/reports/courses?student_id=-1

## Technologies

Java 11, Spring Boot, Lombok, MySQL, Mapstruct, Gradle, Docker, Junit


