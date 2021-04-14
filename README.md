# kafka-test-app

Modules:-
1. Kafka Producer.
2. Kafka consumer.

Setup steps:-
1. Run kafka.
2. Do change the IP:PORT of kafka bootstrap server in porperties file. (if other than localhost:9092)
3. Start the application.


To produce data call below curl:-
curl -X POST "http://localhost:7003/testController" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"data\": \"5\"}"
