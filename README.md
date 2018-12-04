# functional-template
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/jp.spring-boot-reference/functional-template/badge.svg)](https://maven-badges.herokuapp.com/maven-central/jp.spring-boot-reference/functional-template)

https://oss.sonatype.org/content/repositories/releases/jp/spring-boot-reference/functional-template/

functiona-template is __rabbitTemplate__ Wrapper that make it easy to set callBack-function on message Recognition.

Adding pom.xml this dependency 
```xml
<dependency>
	<groupId>jp.spring-boot-reference</groupId>
	<artifactId>functional-template</artifactId>
	<version>1.1</version>
</dependency>
```


You can use `functionaTemplate` as you use __RabbitTemplate__ . 
Set callbackMethod.

```java:example
functionalTemplate.setMessageConverter(jackson2JsonMessageConverter()); // set property as you do on rabbitTemplate
functionalTemplate.setACKMethod(human,human.getClass().getMethod("getName")); // set callback method on message-ack.
functionalTemplate.convertAndSend("messageQueue",human); // publish. 
```
