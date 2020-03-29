# BankSystem

Java RESTful API for money transfers between customers accounts

### Technologies
- Spring Boot
- PostrgreSQL


### How to run
```
To launch classes Application.java in services customerService, accountService and commonService
```

Services customerService, billService and commonService start on localhost ports 8080, 8081 and 8082 respectively. 


### Available Services

## Сustomer Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /customer/{id} | get customer by id | 
| GET | /customer/all | get all customers | 
| POST | /customer/create | create new customer | 
| PUT | /customer/update/{id}| update customer info | 
| DELETE | /customer/{id} | delete customer by id | 

### Sample JSON for Сustomer Service
##### Create customer : 
```
{
	"name" : "Alex",
	"phone" : "+79991119191",
	"mail" : "alex@gmail.com"
}
```

## Bill Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /bill/{id} | get bill by id | 
| GET | /bill/customer{customerId} | get bill by customer id | 
| POST | /bill/create | create bill for exact customer | 
| POST | bill/delete/{id} | delete bill by id | 
| PUT | "bill/adjustment/{id} | commit adjustment for bill by id | 
| PUT | "bill/payment/{id} | commit payment for bill by id | 

### Sample JSON for Bill Service
##### Create bill : 
```
{  
  "customerId": 1,
  "balance": 1000
}
```

##### Commit adjustment : 
```
{  
  "adjustment": 500
} 
```

##### Commit payment : 
```
{  
  "payment": 500
} 
```

## Common Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /account/{customerId} | get all info about customer by id | 
| PUT | /account/transfer-one-customer | commit cash transfer between two bills belonged to one customer  | 
| PUT | /account/transfer-two-customer | commit cash transfer between two bills belonged to two customers  | 

### Sample JSON for Common Service
##### Commit transfer for one customer : 
```
{
	"customerId" : 1,
	"firstBillId" : 1,
	"secondBillId" : 2,
	"transaction" : 500
}
```

##### Commit transfer for two customers : 
```
{
	"customerFirstId" : 1,
	"firstBillId" : 1,
	"customerSecondId" : 2,
	"secondBillId" : 2,
	"transaction" : 1000
}
```
