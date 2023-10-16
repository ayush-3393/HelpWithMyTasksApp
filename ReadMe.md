# Help With My Tasks Application
This is a web based application that allows a Help Seeker to create a task and a Helper to help the Help Seeker by
booking a task and completing it.

## Technologies Used:
This application is built using the following technologies:
1. Java 17
2. Spring Boot
3. Spring Data JPA
4. MySQL
5. Maven Build Tool

The main entities in this application:
1. Help Seeker
2. Helper
3. Task
4. Booking
5. Payment

Their data is persisted in the database.

* The Help Seeker can create a task (or multiple tasks)
* The Helper can book a task (or multiple tasks) for an amount and complete it.
* After completing the task, Booking ends and Payment is generated.

## Sample Requests:
### Help Seeker
```json
{
"firstName" : "Help Seeker Test FN 1",
"lastName" : "Help Seeker Test LN 1",
"age" : 78,
"gender" : "MALE",
"email" : "help.seeker.test.1@gmail.com",
"phoneNumber" : "1234567890",
"address" : "random help seeker address, India, Earth"
}
```
### Helper
```json
{
    "firstName" : "Helper Test FN 1",
    "lastName" : "Helper Test LN 1",
    "age" : 40,
    "email" : "helper_test_1@gmail.com",
    "gender" : "FEMALE",
    "phoneNumber" : "1020304050",
    "address" : "random helper address, India, Earth",
    "helperStatus" : "AVAILABLE"
}
```
### Task
```json
{
    "taskName" : "Task 1",
    "taskDescription" : "Task 1 Description",
    "taskStatus" : "OPEN",
    "taskDueDate" : "2021-10-20"
}
```
### Booking
```json
{
    "bookingAmount" : 1000
}
```

(Booking Response will have the details of who booked the task, for which help seeker and for which task)

## Sample APIs:

### Help Seeker
1. Create Help Seeker
```http request
POST http://localhost:8090/helpseeker
```
2. Get Help Seeker by Id
```http request
GET http://localhost:8090/helpseeker/{helpSeekerId}
```
3. Get All Help Seekers
```http request
GET http://localhost:8090/helpseeker
```
4. Update Help Seeker
```http request
PUT http://localhost:8090/helpseeker/{helpSeekerId}
```
5. Delete Help Seeker
```http request
DELETE http://localhost:8090/helpseeker/{helpSeekerId}
```
6. Get All Open Tasks for a Help Seeker
```http request
GET http://localhost:8090/helpseeker/{helpSeekerId}/tasks
```

### Helper
1. Create Helper
```http request
POST http://localhost:8090/helper
```
2. Get Helper by Id
```http request
GET http://localhost:8090/helper/{helperId}
```
3. Get All Helpers
```http request
GET http://localhost:8090/helper
```
4. Update Helper
```http request
PUT http://localhost:8090/helper/{helperId}
```
5. Delete Helper
```http request
DELETE http://localhost:8090/helper/{helperId}
```
6. Get All Bookings for a Helper
```http request
GET http://localhost:8090/helper/{helperId}/bookings
```

### Task
1. Create A Task For A Help Seeker
```http request
POST http://localhost:8090/tasks/{helpSeekerId}
```

### Booking
1. Create Booking
```http request
POST http://localhost:8090/bookings/{helperId}/{taskId}
```
2. End Booking
```http request
PATCH http://localhost:8090/bookings/{bookingId}
```


#### Note:
* The application is configured to run on port 8090.
* The database is configured to run on port 3306.
* The database name is "help_with_my_tasks_DB".
* All the APIs were tested using Postman.
* If there is any issue with the JSON requests, please refer to the corresponding DTOs.
* If there is any issue with the APIs, please refer to the corresponding Controller classes.