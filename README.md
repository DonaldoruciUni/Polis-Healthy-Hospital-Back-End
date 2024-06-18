# Hospital app

## How to run
`mvnw clean package && java -jar target/com.hospitalapp-2.3.0.jar`

## Routes

### localhost:8080/api

### GET api/department

Returns list of departments ( optional query parameter api/department?search={{ search }})

#### POST api/department 
creates a department and returns it

Insert body:
`{ "name": "test", "code": "1234" }` -- creates a new department

Update body:

`{ "name": "test", "code": "1234", id: 1234 }` - updates the department with id 1234

### PUT api/department/{{ departmentId }}

updates a department with department id

body:
`{ "name": "test", "code": "1234" }`