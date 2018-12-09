# functional-template
![apache licensed](https://img.shields.io/badge/License-Apache_2.0-d94c32.svg)
![Java](https://img.shields.io/badge/Language-Java-f88909.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/jp.spring-boot-reference/functional-template/badge.svg)](https://maven-badges.herokuapp.com/maven-central/jp.spring-boot-reference/functional-template)

https://search.maven.org/artifact/jp.spring-boot-reference/functional-template/1.1/jar
https://oss.sonatype.org/content/repositories/releases/jp/spring-boot-reference/functional-template/

functiona-template is __rabbitTemplate__ Wrapper that make it easy to set callBack-function on message Recognition.



## Usage
You can use `functionaTemplate` as you use __RabbitTemplate__ . 
Set callbackMethod.

```Java
FunctionalTemplate functionalTemplate = new FunctionalTemplate();
// set property as you do on rabbitTemplate
functionalTemplate.setMessageConverter(jackson2JsonMessageConverter()); 
 // set callback method on message-ack. 
functionalTemplate.setACKMethod(human,human.getClass().getMethod("getName"));
// publish message 
functionalTemplate.convertAndSend("messageQueue",human); // publish. 
```

## Dependency
**Maven**
```xml
<dependency>
	<groupId>jp.spring-boot-reference</groupId>
	<artifactId>functional-template</artifactId>
	<version>1.1</version>
</dependency>
```

**Gradle**
```gradle
compile 'jp.spring-boot-reference:functional-template:1.1'
```





