# Agrokaszuby-Backend

### **Features**:
* **F1** - Flat reservation
* **F2** - Related tables
* **F3** - [Frontend] Weather API - Forecast for seven days
* **F4** - [Frontend] Currency exchange API - Add Price with PLN and USD
* **F5** - Reservation form validation
* **F6** - Design patterns
* **F7** - [Backend] Scheduler - sending email with all reservations each day
* **F8** - Application instruction

#### GitHub repos

------
* **Backend**: https://github.com/KatarzynaNabozny/Agrokaszuby-Backend 
------
Port: http://localhost:8090 

## 1. REST ENDPOINTS (F1, F2)

1. Get all reservations: GET http://localhost:8090/agrokaszuby/backend/reservation 
2. Create reservation: POST http://localhost:8090/agrokaszuby/backend/reservation \
  &nbsp;&nbsp;Example Body:
```
    {
        "reservationId": null,
        "startDate": [2022,10,22,10,0],
        "endDate": [2022,10,26,13,0],
        "firstName": "Katarzyna",
        "lastName": "Skelnik",
        "phoneNumber": "4564646",
        "city": "Przysieki 405",
        "postalCode": "31-207",
        "street": "Toki",
        "email": "kodilla.katarzyna.dev@yandex.ru",
        "currency": "PLN",
        "price": "200.00"
    }
```
3. Update reservation: PUT http://localhost:8090/agrokaszuby/backend/reservation 
4. Get reservation by id: GET http://localhost:8090/agrokaszuby/backend/reservation/{id} 
5. Get reservation by email: GET http://localhost:8090/agrokaszuby/backend/reservation/byemail/{email} 
6. Delete reservation by id: DELETE http://localhost:8090/agrokaszuby/backend/reservation/{id} 
7. Delete reservation by email, startDate and endDate:
DELETE http://localhost:8090/agrokaszuby/backend/reservation?email={email}&startDate={startDate}&endDate={endDate} 
```
DELETE http://localhost:8090/agrokaszuby/backend/reservation?email=kodilla.katarzyna.dev@gmail.com&startDate=2022-10-19T10:00:00&endDate=2022-10-26T13:00:00 
```
## 3. SCHEDULER (Sending all reservations report F7)
Here is how this report looks like on the mailbox:

![img.png](src/main/resources/static/scheduler_email.png)


##### To run Backend needed DB MySql schema with name : '**agrokaszuby**'. Then you can run **BackendApplication.java**

------
* Frontend: https://github.com/KatarzynaNabozny/Agrokaszuby-Frontend 
------



