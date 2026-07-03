import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable

def requestPayload = [
	policyNumber: "POL-IFG-E2E-001",
	customerName: "IFG Assurance QA",
	product: "IFG Protection",
	premium: 350000,
	userId: 12
]

def producerResponse = WS.sendRequest(findTestObject("REST/Post Create Policy", [
	("baseUrl")      : GlobalVariable.restBaseUrl,
	("policyNumber") : requestPayload.policyNumber,
	("customerName") : requestPayload.customerName,
	("product")      : requestPayload.product,
	("premium")      : requestPayload.premium,
	("userId")       : requestPayload.userId
]))

WS.verifyResponseStatusCode(producerResponse, 201, FailureHandling.STOP_ON_FAILURE)

def produced = new JsonSlurper().parseText(producerResponse.getResponseBodyContent())
def producedDetail = new JsonSlurper().parseText(produced.body)

assert produced.title == requestPayload.policyNumber
assert produced.userId == requestPayload.userId
assert producedDetail.customerName == requestPayload.customerName
assert producedDetail.product == requestPayload.product
assert producedDetail.premium == requestPayload.premium

def consumerResponse = WS.sendRequest(findTestObject("REST/Get Policy", [
	("baseUrl") : GlobalVariable.restBaseUrl,
	("policyId") : GlobalVariable.consumerPolicyId
]))

WS.verifyResponseStatusCode(consumerResponse, 200, FailureHandling.STOP_ON_FAILURE)

def consumed = new JsonSlurper().parseText(consumerResponse.getResponseBodyContent())
assert consumed.id == GlobalVariable.consumerPolicyId
assert consumed.title
assert consumed.body

println "REST producer/consumer flow validated. Produced policy=${requestPayload.policyNumber}; consumed sample id=${consumed.id}"
