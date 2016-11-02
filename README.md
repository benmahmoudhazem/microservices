# microservices


## Introduction

![Framework Web en 2016](https://zeroturnaround.com/wp-content/uploads/2016/07/microservices-vs-frameworks.png)

## Simple

Je dois admettre que Spring Boot est aujourd’hui une évidence pour moi! C’est le projet qui a rendu à Spring sa simplicité d’origine (et sans XML s’il vous plait)

La classe Principale
```javascript
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

Le controller REST
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

Le fichier de configuration .yml
```
server:
  port: 9090
```
  
Cette nouvelle configuration est prise en compte lors du prochain démarrage :

```
2015-05-27 16:39:32.236  INFO 51056 --- [main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): <strong>9090</strong> (http)
2015-05-27 16:39:32.426  INFO 51056 --- [main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2015-05-27 16:39:32.428  INFO 51056 --- [main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.0.20
```