# smartpark
Notes: I'm having issues on lombok, thus make a generated getters, setters and constructors which compromised code clean and readibility

1. you must first register a vehicle and parking lot
   endpoints:
     [POST] http://localhost:8080/smartpark/register/vehicle
     [POST] http://localhost:8080/smartpark/register/parkinglot
   requestbody:
     parkinglot: location, capacity, occupiedSpaces
     vehicle: licensePlate, type, ownerName
   sample:
   <img width="623" alt="image" src="https://github.com/user-attachments/assets/0fa19275-e658-41ca-9bba-975d3b2f0030" />
   <img width="593" alt="image" src="https://github.com/user-attachments/assets/cf7537e1-d700-465b-96cf-d12f10cd53aa" />

2. you can check all parking lot and available parking lot for certain location
   endpoints:
     [GET] http://localhost:8080/smartpark/all
     [GET] http://localhost:8080/smartpark/available?location=<String>
   sample:
   <img width="587" alt="image" src="https://github.com/user-attachments/assets/ef9233a8-145e-4f0d-8a85-ac82618db8d5" />
   <img width="515" alt="image" src="https://github.com/user-attachments/assets/8d6069dd-9603-42b8-835b-11950f692a86" />

3. you can check in a vehicle for specific parking lot if the lot is not fully occupied and check out the specific vehicle
   endpoints:
     [PUT] http://localhost:8080/smartpark/checkin/vehicle?licensePlate=XPQ-111&parkingLocation=ABC
     [PUT] http://localhost:8080/smartpark/checkout/vehicle?licensePlate=XPQ-111&parkingLocation=ABC
   sample:
   <img width="701" alt="image" src="https://github.com/user-attachments/assets/7cab7319-7cb7-40b3-9b9f-6a25daca1b15" />
   <img width="733" alt="image" src="https://github.com/user-attachments/assets/4f8ea64a-2945-4b5b-b773-2d0dc2affca4" />



