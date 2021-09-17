# field-agent-API 
### Is a Rest services API using Java Spring Framework to manage an secret agency. Their User Interface using React and Bootstrap is available on : https://github.com/pola255/field-agent-ui
## To start the proyect run: App located in main folder
## Field agent endpoints

## Get agency
### GET http://localhost:8080/api/agency HTTP/1.1

## Get agency by Id
### GET http://localhost:8080/api/agency/agencyId HTTP/1.1

## Create an agency
### POST  http://localhost:8080/api/agency  HTTP/1.1
``` Content-Type: application/json

{
    "shortName": "CIA",
    "longName": "Central Intelligence Agency",
    "locations": [],
    "agents": []
}
```

## Delete an agency
### DELETE http://localhost:8080/api/agency/agencyId HTTP/1.1

## Create agency agent relationship

### POST http://localhost:8080/api/agency/agent HTTP/1.1

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

## PUT http://localhost:8080/api/agency/agent HTTP/1.1
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

DELETE http://localhost:8080/api/agency/agent/agencyId/agentId HTTP/1.1

