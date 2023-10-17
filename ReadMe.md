# Help With My Tasks Application
This is a web based application that allows a Help Seeker to create a task and a Helper to help the Help Seeker by
booking a task and completing it.

## Technologies Used:
This application is built using the following technologies:
1. Java 17
2. Spring Boot 3.1.4
3. Spring Data JPA / Hibernate
4. MySQL
5. Maven Build Tool

## Overview:
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
* Upon ending a booking a Help Seeker can rate the Helper and vice versa.
* The rating gets reflected on the Helper's or Help Seeker's corresponding profile.

## Sample Requests:
### Help Seeker
```json
{
"firstName" : "Albus",
"lastName" : "Dumbledore",
"age" : 689,
"gender" : "MALE",
"email" : "old_magician@gmail.com",
"phoneNumber" : "1234567890",
"address" : "Hogwarts, Scotland, Earth"
}
```
### Helper
```json
{
    "firstName" : "Harry",
    "lastName" : "Potter",
    "age" : 15,
    "email" : "new_magician@gmail.com",
    "gender" : "MALE",
    "phoneNumber" : "1020304050",
    "address" : "4 Privet Drive, Little Whinging, Surrey",
    "helperStatus" : "AVAILABLE"
}
```
### Task
```json
{
    "taskName" : "Defeat Voldemort",
    "taskDescription" : "Defeat Voldemort and save the world",
    "taskStatus" : "OPEN",
    "taskBudget" : 100,
    "taskDueDate" : "2021-10-20"
}
```
### End Booking
```json
{
    "ratingFromHelpSeekerToHelper" : 2,
    "ratingFromHelperToHelpSeeker" : 7
}
```

## Sample APIs and Responses:

### Help Seeker
1. Create Help Seeker
```http request
POST http://localhost:8090/helpseeker
```
###### Response:
```json
{
  "helpSeekerId": 1,
  "firstName": "Albus",
  "lastName": "Dumbledore",
  "gender": "MALE",
  "email": "old_magician@gmail.com",
  "age": 689,
  "phoneNumber": "1234567890",
  "address": "Hogwarts, Scotland, Earth",
  "rating": null
}
```

2. Get Help Seeker by Id
```http request
GET http://localhost:8090/helpseeker/{helpSeekerId}
```
###### Response:
```json
{
    "helpSeekerId": 1,
    "firstName": "Albus",
    "lastName": "Dumbledore",
    "gender": "MALE",
    "email": "old_magician@gmail.com",
    "age": 689,
    "phoneNumber": "1234567890",
    "address": "Hogwarts, Scotland, Earth",
    "rating": 4.666666666666667
}
```

3. Get All Help Seekers
```http request
GET http://localhost:8090/helpseeker
```
###### Response:
```json
[
    {
        "helpSeekerId": 1,
        "firstName": "Albus",
        "lastName": "Dumbledore",
        "gender": "MALE",
        "email": "old_magician@gmail.com",
        "age": 689,
        "phoneNumber": "1234567890",
        "address": "Hogwarts, Scotland, Earth",
        "rating": null
    },
    {
        "helpSeekerId": 2,
        "firstName": "Severus",
        "lastName": "Snape",
        "gender": "MALE",
        "email": "black_magician@gmail.com",
        "age": 125,
        "phoneNumber": "6666666666",
        "address": "Hogwarts, Scotland, Earth",
        "rating": null
    }
]
```

4. Update Help Seeker
```http request
PUT http://localhost:8090/helpseeker/{helpSeekerId}
```
5. Delete Help Seeker
```http request
DELETE http://localhost:8090/helpseeker/{helpSeekerId}
```
6. Get All Booked Tasks for a Help Seeker
```http request
GET http://localhost:8090/helpseeker/{helpSeekerId}/tasks
```

###### Response:
```json
{
    "tasks": [
        {
            "taskId": 2,
            "taskTitle": "Play Quidditch",
            "taskDescription": "Get the Golden Snitch and win the game",
            "helpSeekerName": "Albus Dumbledore",
            "taskStatus": "BOOKED",
            "taskDueDate": "2021-10-25T00:00:00.000+00:00",
            "taskBudget": 1000
        }
    ]
}
```

### Helper
1. Create Helper
```http request
POST http://localhost:8090/helper
```
###### Response:
```json
{
  "id": 1,
  "firstName": "Harry",
  "lastName": "Potter",
  "gender": "MALE",
  "email": "new_magician@gmail.com",
  "age": 15,
  "phoneNumber": "1020304050",
  "address": "4 Privet Drive, Little Whinging, Surrey",
  "helperStatus": "AVAILABLE",
  "rating": null
}

```

2. Get Helper by Id
```http request
GET http://localhost:8090/helper/{helperId}
```
###### Response:
```json
{
    "id": 1,
    "firstName": "Harry",
    "lastName": "Potter",
    "gender": "MALE",
    "email": "new_magician@gmail.com",
    "age": 15,
    "phoneNumber": "1020304050",
    "address": "4 Privet Drive, Little Whinging, Surrey",
    "helperStatus": "UNAVAILABLE",
    "rating": 3.0
}
```

3. Get All Helpers
```http request
GET http://localhost:8090/helper
```
###### Response:
```json
[
  {
    "id": 1,
    "firstName": "Harry",
    "lastName": "Potter",
    "gender": "MALE",
    "email": "new_magician@gmail.com",
    "age": 15,
    "phoneNumber": "1020304050",
    "address": "4 Privet Drive, Little Whinging, Surrey",
    "helperStatus": "UNAVAILABLE",
    "rating": null
  },
  {
    "id": 2,
    "firstName": "Hermione",
    "lastName": "Granger",
    "gender": "FEMALE",
    "email": "brilliant_magician@gmail.com",
    "age": 15,
    "phoneNumber": "3330000333",
    "address": "8 Heathgate, Hampstead Garden Suburb, London",
    "helperStatus": "AVAILABLE",
    "rating": null
  }
]
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

###### Response:
```json
[
  {
    "id": 1,
    "amount": 100,
    "bookingDate": "2023-10-17T07:44:19.838+00:00",
    "helperName": "Harry Potter",
    "taskName": "Defeat Voldy",
    "helpSeekerName": "Albus Dumbledore",
    "bookingStatus": "COMPLETED",
    "ratingFromHelpSeekerToHelper": 3,
    "ratingFromHelperToHelpSeeker": 4
  },
  {
    "id": 2,
    "amount": 100,
    "bookingDate": "2023-10-17T07:45:13.522+00:00",
    "helperName": "Harry Potter",
    "taskName": "Defeat Snape",
    "helpSeekerName": "Albus Dumbledore",
    "bookingStatus": "COMPLETED",
    "ratingFromHelpSeekerToHelper": 4,
    "ratingFromHelperToHelpSeeker": 3
  },
  {
    "id": 3,
    "amount": 100,
    "bookingDate": "2023-10-17T07:46:12.379+00:00",
    "helperName": "Harry Potter",
    "taskName": "Play Quidditch",
    "helpSeekerName": "Albus Dumbledore",
    "bookingStatus": "COMPLETED",
    "ratingFromHelpSeekerToHelper": 2,
    "ratingFromHelperToHelpSeeker": 7
  }
]
```

### Task
1. Create A Task For A Help Seeker
```http request
POST http://localhost:8090/tasks/{helpSeekerId}
```
```json
{
    "taskId": 1,
    "taskTitle": "Defeat Voldemort",
    "taskDescription": "Defeat Voldemort and save the world",
    "helpSeekerName": "Albus Dumbledore",
    "taskStatus": "OPEN",
    "taskDueDate": "2021-10-20T00:00:00.000+00:00",
    "taskBudget": 100
}
```

### Booking
1. Create Booking
```http request
POST http://localhost:8090/bookings/{helperId}/{taskId}
```
###### Response:
```json
{
    "id": 1,
    "amount": 100,
    "bookingDate": "2023-10-16T12:38:29.439+00:00",
    "helperName": "Harry Potter",
    "taskName": "Defeat Voldemort",
    "helpSeekerName": "Albus Dumbledore",
    "bookingStatus": "ACCEPTED"
}
```

2. End Booking
```http request
PATCH http://localhost:8090/bookings/{bookingId}
```
###### Response:
```json
{
  "id": 3,
  "amount": 100,
  "bookingDate": "2023-10-17T07:46:12.379+00:00",
  "helperName": "Harry Potter",
  "taskName": "Play Quidditch",
  "helpSeekerName": "Albus Dumbledore",
  "bookingStatus": "COMPLETED",
  "ratingFromHelpSeekerToHelper": 2,
  "ratingFromHelperToHelpSeeker": 7
}
```

## Future Scope:
1. Add security to the application, using Spring Security.
2. Add unit test cases.
3. Add integration test cases.
4. Add logging.
5. Add notification service.
6. Add payment gateway.
7. Add UI.
8. Add feature of getting all the tasks in the nearby location of the Helper.

### Note:
* The application is configured to run on port 8090.
* The database is configured to run on port 3306.
* The database name is "help_with_my_tasks_DB".
* All the APIs were tested using Postman.
* If there is any issue with the JSON requests, please refer to the corresponding DTOs.
* If there is any issue with the APIs, please refer to the corresponding Controller classes.