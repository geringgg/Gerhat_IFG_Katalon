package kafka

@Grab("org.apache.kafka:kafka-clients:3.7.0")
import com.kms.katalon.core.annotation.Keyword
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration

class KafkaConsumerKeyword {

	@Keyword
	static Map consumeMessage(String bootstrapServers, String topic, String groupId, String expectedKey, String expectedValueText, int timeoutSeconds) {
		Properties props = new Properties()
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "${groupId}-${System.currentTimeMillis()}")
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.name)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.name)
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)
		long deadline = System.currentTimeMillis() + (timeoutSeconds * 1000L)

		try {
			consumer.subscribe([topic])

			while (System.currentTimeMillis() < deadline) {
				def records = consumer.poll(Duration.ofMillis(500))

				for (record in records) {
					boolean keyMatches = expectedKey == null || expectedKey.trim().isEmpty() || expectedKey == record.key()
					boolean valueMatches = expectedValueText == null || expectedValueText.trim().isEmpty() || record.value().contains(expectedValueText)

					if (keyMatches && valueMatches) {
						return [
							topic: record.topic(),
							partition: record.partition(),
							offset: record.offset(),
							key: record.key(),
							value: record.value()
						]
					}
				}
			}
		} finally {
			consumer.close()
		}

		throw new AssertionError("Kafka message not found on topic '${topic}' with key='${expectedKey}' and value containing '${expectedValueText}'")
	}
}
