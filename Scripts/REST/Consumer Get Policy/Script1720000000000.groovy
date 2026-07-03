import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable

def policyId = GlobalVariable.consumerPolicyId

def response = WS.sendRequest(findTestObject("REST/Get Policy", [
	("baseUrl") : GlobalVariable.restBaseUrl,
	("policyId") : policyId
]))

WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
WS.verifyElementPropertyValue(response, "id", policyId)

def body = new JsonSlurper().parseText(response.getResponseBodyContent())

assert body.userId != null : "Consumer response must include userId"
assert body.title instanceof String && body.title.trim() : "Consumer response title must not be empty"
assert body.body instanceof String && body.body.trim() : "Consumer response body must not be empty"

println "REST consumer validated policy-like resource id=${policyId}, title=${body.title}"
