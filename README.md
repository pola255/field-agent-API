# field-agent-API 
Is a Rest services API using Java Spring Framework to manage an secret agency. Their User Interface using React and Bootstrap is available on : https://github.com/pola255/field-agent-ui
## To start the proyect run: 
App located in main folder
# Field agent endpoints

## Get agency
### GET http://localhost:8080/api/agency

## Get agency by Id
### GET http://localhost:8080/api/agency/agencyId

## Create an agency
### POST  http://localhost:8080/api/agency  
``` Content-Type: application/json
{
    "shortName": "CIA",
    "longName": "Central Intelligence Agency",
    "locations": [],
    "agents": []
}
```
## Delete an agency
### DELETE http://localhost:8080/api/agency/agencyId 

## Create agency agent relationship

### POST http://localhost:8080/api/agency/agent 

``` Content-Type: application/json
{
    "agencyId": 1,
    "identifier": "007",
    "activationDate": "2010-04-04",
    "securityClearance": {
        "securityClearanceId": 1
    },
    "agent": {
        "agentId": 2
    }
}
```
## Update agency with agent

## PUT http://localhost:8080/api/agency/agent 
``` Content-Type: application/json
{
    "agencyId": 1,
    "identifier": "agent x",
    "activationDate": "2010-04-04",
    "securityClearance": {
        "securityClearanceId": 1
    },
    "agent": {
        "agentId": 1
    }
}
```
## Delete an agency agent relationship
### DELETE http://localhost:8080/api/agency/agent/agencyId/agentId 

## Get agents
### GET http://localhost:8080/api/agent 

## Get agent by Id
### GET http://localhost:8080/api/agent/agentId 

## Update agent
### PUT http://localhost:8080/api/agent/agentId 

```Content-Type: application/json
{
    "agentId": 2,
    "firstName": "Claudian",
    "middleName": "C",
    "lastName": "O'Lynn",
    "dob": "1956-11-09",
    "heightInInches": 41,
    "agencies": []
}
```
## Delete an agent
### DELETE http://localhost:8080/api/agent/agentId 

## Create an alias
### POST http://localhost:8080/api/alias 

```Content-Type: application/json
{
    "name": "Sherlock",
    "persona": "Holmes",
    "agentId": 1
}
```
## Update alias
### POST http://localhost:8080/api/alias/aliasId  
```Content-Type: application/json

{

    "name": "Sherlock",
    "persona": null,
    "aliasId": 1
}
```
## Delete an alias
### DELETE http://localhost:8080/api/alias/aliasId  

## Get location of agency
### GET http://localhost:8080/api/location/agencyId 

## Create location for agency
### POST http://localhost:8080/api/location 
```Content-Type: application/json
{
    "name": "Safe House",
    "address": "212 The Parkway",
    "city": "Arkansaw",
    "region": "WI",
    "countryCode": "USA",
    "postalCode": "55555",
    "agencyId": 1
}
```
## Get security clearances
### GET http://localhost:8080/api/security-clearance

## Get security clearance by Id
### GET http://localhost:8080/api/security-clearance/securityClearanceId 

## Create security clearance
### POST  http://localhost:8080/api/security-clearance/securityClearanceId 
```Content-Type: application/json
{
   "name": "CIA"
}
```
## Update secutity clearance
### PUT http://localhost:8080/api/security-clearance/securityClearanceId 
```Content-Type: application/json
{
    "name": "Test",
    "securityClearanceId": 1
}
```
## Delete security clearance
### DELETE http://localhost:8080/api/security-clearance/securityClearanceId 
