# kafka-Spring

## reactor-kafka spring boot producer + spring kafka consumer

### Execution

* Execute kafka, zookeeper with docker

```bash
docker-compose up -d
```

* Execute spring boot producer and consumer

```bash
mvn -pl reactor-producer spring-boot:run
```

```bash
mvn -pl consumer spring-boot:run
```

* When I call it from restapi, the consumer works and keeps a log.
```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "name":"insung",
  "number":10
}' \
 'http://localhost:10100/api/producer'

```