# microservices

## Introduction

TODO: mais qu'est ce qu'un microservice ?

Si en 2016, vous n'avez pas encore essayé le framework Spring Boot, il est grand temps de vous y mettre...
![Frameworks Web en 2016](https://zeroturnaround.com/wp-content/uploads/2016/07/microservices-vs-frameworks.png)

## Pre-requis

* JDK 1.8 or later
* Maven 3.2+

## Simple

Je dois admettre que Spring Boot est aujourd’hui une évidence pour moi! C’est le projet qui a rendu à Spring sa simplicité d’origine (et sans XML s’il vous plait)

**le pom.xml**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.springframework</groupId>
    <artifactId>gs-spring-boot</artifactId>
    <version>0.1.0</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.1.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <properties>
        <java.version>1.8</java.version>
    </properties>


    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
Il contient:
* le BOM de *spring-boot-starter-parent* définissant l'ensemble des versions des modules de *spring-boot*.
* le *starter web*, c'est l'emsemble des fonctionnalités pour démarrer un server embarqué Tomcat, l'API REST et JSON   
* Le plugin *spring-boot* permettant en autres de rentre le jar executable.

**La classe Principale**
```java
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

**Le controller REST**
```java
@RestController
@RequestMapping("/api")
public class RestServices {

	@RequestMapping(method = RequestMethod.GET)
	public
	@ResponseBody
	String ping() {
    	return "{\"status\":\"OK\",\"timestamp\":\"" + System.currentTimeMillis() + "\"}";
	}

}
```

**Le fichier de configuration application.yml**
```
server:
  port: 9090
```
  
Cette nouvelle configuration est prise en compte lors du prochain démarrage :
```
mvn clean package && java -jar target/simple-service-0.1.0.jar
```

```
2016-10-01 16:39:32.236  INFO 51056 --- [main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 9090 (http)
2016-10-01 16:39:32.426  INFO 51056 --- [main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2016-10-01 16:39:32.428  INFO 51056 --- [main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.0.20
```

