## WIKI
[lien vers le wiki](https://github.com/fanfansama/microservices/wiki)


## Pre-requis

* JDK 1.8 or later
* Maven 3.2+

## How-To 
### Les scripts

Script | Description
------------ | -------------
build-all.sh | compile et package les modules du projet
start.sh | demarre un microservice ex: start.sh *simple-service*
stop.sh | stop un microservice ex: start.sh *simple-service*
start-all.sh | demarre tout les microservices
stop-all.sh | stop les microservices
restart-all.sh | stop-all.sh + build-all.sh + start-all.sh

### Les Branches
Script | Description
------------ | -------------
master | HEAD
M1_basic | Les features de bases de spring-boot [wiki](https://github.com/fanfansama/microservices/wiki/simple)
M2_db | integration de spring-data-jpa & Flyway [wiki](https://github.com/fanfansama/microservices/wiki/db)
M3_cloud | Spring Cloud [wiki](https://github.com/fanfansama/microservices/wiki/cloud)
M4_oauth2 | Spring Cloud & Oauth Server [wiki](https://github.com/fanfansama/microservices/wiki/cloud)

### Comment démarrer ?

* ./restart-all.sh
* http://localhost:8080/data-service/
* login: user - mdp: password

### TODO LIST
- [x] Initialiser le projet
- [x] spring-boot - Présenter les features de bases de spring-boot
- [x] spring-boot - Avoir un exemple complet
- [x] spring-boot - Démontrer la simplicité d'intégration d'autres features avancés (data-jpa & Flyway)
- [x] spring-boot - Avoir un exemple complet de features avancés
- [ ] spring-boot - Avoir une UI
- [ ] spring-cloud - Presenter Eureka+Zuul
- [x] spring-cloud - Code Eureka+Zuul
- [ ] spring-cloud - Presenter Oauth2
- [x] spring-cloud - Code Oauth2
- [ ] spring-cloud - use Oauth2 + external account
- [ ] integration docker
- [x] add git info
- [ ] find a best way to provide META-INF informations


