# kafka-Spring

## reactor-kafka spring boot producer + spring kafka consumer

### Execution

* Execute kafka, zookeeper, kafka-ui, consul with docker

```bash
docker-compose up -d
```

* Execute spring boot producer and consumer

```bash
./gradlew -p reactor-producer bootRun
```

```bash
./gradlew -p consumer bootRun
```

* You can turn the switch on and off with an API call.

```
curl -i -X GET \
  'http://localhost:18080/on'

```

```
curl -i -X GET \
  'http://localhost:18080/off'

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