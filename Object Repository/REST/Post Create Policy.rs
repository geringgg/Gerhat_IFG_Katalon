<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>POST request used by Katalon as REST producer to create a policy-like resource.</description>
   <name>Post Create Policy</name>
   <tag></tag>
   <elementGuidId>4a5dd85d-3f9b-49ef-87c8-66f98fd76f57</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <httpBody>{
  "title": "${policyNumber}",
  "body": "{\"customerName\":\"${customerName}\",\"product\":\"${product}\",\"premium\":${premium}}",
  "userId": ${userId}
}</httpBody>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${baseUrl}/posts</restUrl>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>3a134e8d-5f92-496f-a978-b8f952a030b8</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>f5789f30-34a2-44e6-a746-d2527c607c62</webElementGuid>
   </httpHeaderProperties>
   <variables>
      <defaultValue>GlobalVariable.restBaseUrl</defaultValue>
      <description></description>
      <id>f3d4a81b-c242-4745-ac1f-a0793b619fb0</id>
      <masked>false</masked>
      <name>baseUrl</name>
   </variables>
   <variables>
      <defaultValue>'POL-IFG-001'</defaultValue>
      <description></description>
      <id>f3565b09-2f9b-4677-9ae6-8c5cd2f145d6</id>
      <masked>false</masked>
      <name>policyNumber</name>
   </variables>
   <variables>
      <defaultValue>'Gerhat QA'</defaultValue>
      <description></description>
      <id>0a758957-92d5-4834-940c-e9d3a2fe08c7</id>
      <masked>false</masked>
      <name>customerName</name>
   </variables>
   <variables>
      <defaultValue>'IFG LifeSAVER'</defaultValue>
      <description></description>
      <id>9857768f-5aa2-49dd-984d-ad8f58fdbcf0</id>
      <masked>false</masked>
      <name>product</name>
   </variables>
   <variables>
      <defaultValue>250000</defaultValue>
      <description></description>
      <id>1d32b88f-c80d-448d-b823-fd417282c81d</id>
      <masked>false</masked>
      <name>premium</name>
   </variables>
   <variables>
      <defaultValue>11</defaultValue>
      <description></description>
      <id>4bcdbdbd-9164-442a-9129-f7ba27a91f6d</id>
      <masked>false</masked>
      <name>userId</name>
   </variables>
</WebServiceRequestEntity>
