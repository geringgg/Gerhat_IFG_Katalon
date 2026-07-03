import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper
import internal.GlobalVariable

def message = CustomKeywords."kafka.KafkaConsumerKeyword.consumeMessage"(
	GlobalVariable.kafkaBootstrapServers,
	GlobalVariable.kafkaTopic,
	GlobalVariable.kafkaConsumerGroupId,
	"POL-IFG-001",
	"POLICY_CREATED",
	30
)

KeywordUtil.logInfo("Kafka message consumed: ${message}")

def payload = new JsonSlurper().parseText(message.value as String)

assert message.key == "POL-IFG-001"
assert payload.eventType == "POLICY_CREATED"
assert payload.policyNumber == "POL-IFG-001"
assert payload.product == "IFG LifeSAVER"
assert payload.status == "ACTIVE"
assert payload.premium == 250000

println "Kafka consumer validated event ${payload.eventType} for policy ${payload.policyNumber}"
