D:
cd kafka_2.12-2.3.0/bin/windows
call kafka-topics.bat --bootstrap-server localhost:9092 --create --topic cwct-all-events-no-key-test-1 --replication-factor 1 --partitions 1
call kafka-topics.bat --bootstrap-server localhost:9092 --create --topic cwct-all-events-rekey-test-1 --replication-factor 1 --partitions 1
call kafka-topics.bat --bootstrap-server localhost:9092 --create --topic cwct-processed-events-test-1 --replication-factor 1 --partitions 1
call kafka-topics.bat --bootstrap-server localhost:9092 --create --topic cwct-cart-items-test-1 --replication-factor 1 --partitions 1
PAUSE