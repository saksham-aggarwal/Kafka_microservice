D:
cd kafka_2.12-2.3.0/bin/windows
call kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic cwct-processed-events-test-1 --from-beginning