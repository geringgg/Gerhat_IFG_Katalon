import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable

def policyNumber = "POL-IFG-001"
def customerName = "Gerhat QA"
def product = "IFG LifeSAVER"
def premium = 250000
def userId = 11

def response = WS.sendRequest(findTestObject("REST/Post Create Policy", [
	("baseUrl")      : GlobalVariable.restBaseUrl,
	("policyNumber") : policyNumber,
	("customerName") : customerName,
	("product")      : product,
	("premium")      : premium,
	("userId")       : userId
]))

WS.verifyResponseStatusCode(response, 201, FailureHandling.STOP_ON_FAILURE)
WS.verifyElementPropertyValue(response, "title", policyNumber)
WS.verifyElementPropertyValue(response, "userId", userId)

def body = new JsonSlurper().parseText(response.getResponseBodyContent())
def detail = new JsonSlurper().parseText(body.body)

assert body.id != null : "Response must contain generated id"
assert detail.customerName == customerName
assert detail.product == product
assert detail.premium == premium

println "REST producer created policy simulation with id=${body.id}, policyNumber=${policyNumber}"
