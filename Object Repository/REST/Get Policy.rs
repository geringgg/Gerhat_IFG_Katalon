<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>GET request used by Katalon as REST consumer to read a policy-like resource.</description>
   <name>Get Policy</name>
   <tag></tag>
   <elementGuidId>555f1f45-d0b6-46a2-988d-c15de102f4de</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <httpBody></httpBody>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${baseUrl}/posts/${policyId}</restUrl>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>8b24714d-e454-407f-b5f8-ccf858f0ec02</webElementGuid>
   </httpHeaderProperties>
   <variables>
      <defaultValue>GlobalVariable.restBaseUrl</defaultValue>
      <description></description>
      <id>e05c1a1e-71f4-4c41-b310-895b5305ece3</id>
      <masked>false</masked>
      <name>baseUrl</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.consumerPolicyId</defaultValue>
      <description></description>
      <id>8f553f52-02e9-4034-840c-b09a4c150840</id>
      <masked>false</masked>
      <name>policyId</name>
   </variables>
</WebServiceRequestEntity>
