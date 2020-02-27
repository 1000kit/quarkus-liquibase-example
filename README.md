# quarkus-liquibase

Quarkus liquibase example

Create event
```shell script
curl -s -H "Content-Type:application/json" --data '{"guid":"1","source":"s","target":"t"}' http://localhost:8080/event | jq '.'
```

```json
{
  "guid": "1",
  "source": "s",
  "target": "t"
}
```

Get all events
```shell script
curl -s http://localhost:8080/event/all | jq '.'
```

```shell script
curl -s http://localhost:8080/liquibase/ | jq '.'
```

```json
[
  {
    "changeLog": "db/create-table.xml",
    "id": "create-table-1",
    "author": "dev",
    "lastCheckSum": {
      "version": 8
    },
    "dateExecuted": 1579863712791,
    "tag": null,
    "execType": "EXECUTED",
    "description": "createTable tableName=EVENT",
    "comments": "",
    "orderExecuted": 1,
    "contextExpression": {
      "contexts": [],
      "empty": true
    },
    "labels": {
      "labels": [],
      "empty": true
    },
    "deploymentId": "9863712779"
  },
  {
    "changeLog": "db/add-column.xml",
    "id": "2",
    "author": "dev",
    "lastCheckSum": {
      "version": 8
    },
    "dateExecuted": 1579864675965,
    "tag": null,
    "execType": "EXECUTED",
    "description": "addColumn tableName=EVENT",
    "comments": "",
    "orderExecuted": 2,
    "contextExpression": {
      "contexts": [],
      "empty": true
    },
    "labels": {
      "labels": [],
      "empty": true
    },
    "deploymentId": "9864675959"
  }
]
```
