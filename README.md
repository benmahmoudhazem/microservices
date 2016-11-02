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
* le *spring-boot-starter-web*, c'est l’ensemble des librairies Tomcat permettant de lancer le serveur d’application nécessaire à l’exécution de l’application.
* Le plugin *spring-boot* permet en autres de construire un jar executable.


**La classe Principale**
```java
@SpringBootApplication
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
```yml
server:
  port: 9090
```
  
Le packaging par défaut un jar "self-contained" (jar exécutable contenant l’ensemble des dépendances de l’application).
```
mvn clean package && java -jar target/simple-service-0.1.0.jar
```

Et voilà, votre microservice a démarré.
```
2016-10-01 16:39:32.236  INFO 51056 --- [main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 9090 (http)
2016-10-01 16:39:32.426  INFO 51056 --- [main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2016-10-01 16:39:32.428  INFO 51056 --- [main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.0.20
```


## Modulable

Si toutefois votre serveur d'application préféré était *Jetty*, remplacé la dépendance de Tomcat par *spring-boot-starter-jetty*.
Si vous préferez utiliser *Jersey* plutôt que Spring-MVC, remplacé la dépendance de Tomcat par *spring-boot-starter-jersey*.

## Quelques Features indispensable

### Actuator
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
Actuator vous permet de monitorer et d'interagir avec votre application. Il ajoute : 
* des endpoints comme par exemple les informations de santé. Demarré et consulté /health (et /mappings pour la liste des autres URI disponible).
* des metriques sur les requetes HTTP et sur l'état de la JVM 
* des outils de monitoring comme *ApplicationPidFileWriter* qui créé un fichier contenant le PID de l'application.

### Les logs
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```
Spring boot vous fournira un kit complet de gestion des logs (dépendances et configuration de logback) pour aller directement en production.

### DevTools
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>


<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
            </configuration>
        </plugin>
    </plugins>
</build>
```
Vous permettra le redémmarage automatique de votre application à chacune des modifications de votre code ;-)

### Les propriétés

Par exemple, prenons la configuration par défaut suivante (application.yml) :
```
server:
    port: 9090
```    
et le configuration de test suivante (application-test.yml) :

```
server:
    port: 9292
logging:
    level:
        org.springframework: DEBUG
```        
Si nous lançons l’application sans paramètre supplémentaire, la première configuration sera appliquée : port HTTP = 9090 et level log = INFO (valeur par défaut de Spring Boot).
Par contre, si nous lançons l’application avec le paramètre suivant --spring.profiles.active=test, alors la configuration suivante sera appliqué : port HTTP = 9292 et level log = DEBUG.

L'ensemble des propriétés sont disponibles sur l'[Appendix Spring BOOT](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

### notre jar devient un bash
L'idéal serait maintenant d'avoir un script de démarrage (start/stop/status) afin d'éviter les commandes *java -jar* et *kill -1*. 
Spring a encore pensé à tout, il package votre jar avec un [lanceur](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-tools/spring-boot-loader-tools/src/main/resources/org/springframework/boot/loader/tools/launch.script) !

```
#!/usr/bin/env bash

export APP_NAME=simple-service

export MODE=service
WORKING_DIR="$(pwd)"

export JARFILE=$WORKING_DIR/$APP_NAME/target/$APP_NAME*-exec.jar
export PID_FOLDER=$WORKING_DIR/$APP_NAME/target/
export LOG_FOLDER=$WORKING_DIR/$APP_NAME/target/

$JARFILE start

```

