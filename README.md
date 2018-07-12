# MUTANT - DETECTOR
Magneto wants to recruit as many mutants as possible to fight against the X-Men.
He has hired you to develop a project that detects if a Human is mutant based on its DNA sequence.


## Environment setup
 - Install Java 8.
 - Install Mysql v5.x
 - Install Maven
 
 
## Development
- Clone the code in github
- After clone, access project archives and alter the user and password of database in the file application.yml
- In your mysql, create database " mutant "
- Use 8080 port
- In terminal, access the project place and execute the command " mvn clean install "

OBS.: This project uses FlyWay dbMigration, if you want to see the db creation script, please view the following file resources/development/db/migration/V1__Init.sql


## API URL
URL local: http://localhost:8080/

URL in Amazon: Coming soon


## SERVICES
Verify mutant DNA
Request:

POST /mutants
Request body (exemple mutant DNA):
  ```json
  {"dna":["ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]}
```
Response:
  200 OK

Request body (exemple human DNA):
```json
  {"dna":["AATACT", "CCCAGA", "GGGATT", "AATTCC", "AGAGCG", "TCACTG"]}
  ```
Response:
  403 Forbidden


Statistics
Request:

GET /stats
Response: 200 (application/json)
```json
{
    "count_mutant_dna": 3,
    "count_human_dna": 2,
    "ratio": 1.5
}
```
OBS.: See all the services available in this API in a simplified way using Swagger, just access the "/swagger-ui.html" of this application, ex: http://localhost:8080/swagger-ui.html


## Project creator
Developer [Flavio Augusto Teixeira](https://github.com/flaviofat).