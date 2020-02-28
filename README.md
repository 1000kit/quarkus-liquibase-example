# quarkus-liquibase

### Build native application

To create native application run this command (GraalVM is required)
```shell script
mvn clean package -Pnative
```
or you can run native build inside docker image use this command
```shell script
mvn clean package -Pnative -Dquarkus.native.container-build=true
```

### Build docker image
```shell script
docker build -t tkit/quarkus-liquibase-example -f src/main/docker/Dockerfile .
```
### Docker compose dev environment

Start the database
```shell script
docker-compose up liquibase-postgres
```

Start the application
```shell script
docker-compose up quarkus-liquibase-example
```

Output logs
```shell script
Attaching to quarkus-liquibase-example
quarkus-liquibase-example    | Liquibase: Update has been successful.
quarkus-liquibase-example    | 2020-02-28 11:54:42,886 INFO  [io.quarkus] (main) quarkus-liquibase-example 1.0.0-SNAPSHOT (running on Quarkus 999-SNAPSHOT) started in 0.159s. Listening on: http://0.0.0.0:8080
quarkus-liquibase-example    | 2020-02-28 11:54:42,887 INFO  [io.quarkus] (main) Profile prod activated. 
quarkus-liquibase-example    | 2020-02-28 11:54:42,887 INFO  [io.quarkus] (main) Installed features: [agroal, cdi, hibernate-orm, jdbc-postgresql, liquibase, narayana-jta, resteasy, resteasy-jackson]
```

### Quarkus liquibase example

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
