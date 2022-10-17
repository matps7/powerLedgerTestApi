# powerLedgerTestApi

Web Application in Spring Boot written in Java11 with Maven

Command to Build and run Test: mvn clean install

Command to Run : mvn spring-boot:run

The rest api receive a list of Machine in format JSON, an example of file is in the folder \src\test\resources\templates and these are used also for testing.

Curl examples:

- curl post http://localhost:9090/saveListBatteries
- curl get http://localhost:9090/getByPostCodeRange?start=0&end=1234
