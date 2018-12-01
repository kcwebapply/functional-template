# functional-template

functiona-template is __rabbitTemplate__ Wrapper that make it easy to set callBack-function on message Recognition.

Adding pom.xml this dependency 
```xml
<dependency>
		<groupId>func.spring</groupId>
		<artifactId>rabbit</artifactId>
		<version>1.0</version>
</dependency>
.
.
.
<repositories>
	<repository>
		<id>adapter</id>
		<url>https://raw.github.com/wadakc/functional-template/mvn-repo/</url>
	</repository>
</repositories>
```


You can use `functionaTemplate` as you use __RabbitTemplate__ . 
Set callbackMethod.

```java:example
functionalTemplate.setMessageConverter(jackson2JsonMessageConverter()); // set property as you do on rabbitTemplate
functionalTemplate.setACKMethod(human,human.getClass().getMethod("getName")); // set callback method on message-ack.
functionalTemplate.convertAndSend("messageQueue",human); // publish. 
```
