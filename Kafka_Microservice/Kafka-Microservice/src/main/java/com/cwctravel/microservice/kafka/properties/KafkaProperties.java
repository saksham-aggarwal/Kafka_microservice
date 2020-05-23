package com.cwctravel.microservice.kafka.properties;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;

import com.cwctravel.microservice.kafka.constants.KafkaConstants;
import com.cwctravel.microservice.kafka.serdes.RentalCarEventDetailsDeserializer;
import com.cwctravel.microservice.kafka.serdes.RentalCarEventDetailsSerde;
import com.cwctravel.microservice.kafka.serdes.RentalCarEventDetailsSerializer;

/*
 * Contains the producer, consumer and streams configurations. Additional properties could be added to the configuration
 * according to the needs.
 */

public class KafkaProperties {

	/*
	 * Creates a Properties object which contains all the configuration information for a Kafka Producer.
	 */
	public static Properties configProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.getBootstrapServers());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, RentalCarEventDetailsSerializer.class);

		return props;
	}

	/*
	 * Creates a Properties object which contains all the configuration information for a Kafka Consumer.
	 */
	public static Properties configConsumer() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.getBootstrapServers());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.getGroupId());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, KafkaConstants.getAutoCommit());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConstants.getOffsetReset());
		props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, KafkaConstants.getHeartbeatIntervalMs());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, RentalCarEventDetailsDeserializer.class);

		return props;
	}

	/*
	 * Creates a Properties object which contains all the configuration information for a Kafka Streams.
	 */
	public static Properties configStream() {
		Properties props = new Properties();
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.getBootstrapServers());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.getGroupId());
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, KafkaConstants.getApplicationId());
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, RentalCarEventDetailsSerde.class);

		return props;
	}
}
