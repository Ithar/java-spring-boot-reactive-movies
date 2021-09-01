# java-spring-boot-reactive-movies

Reactive web app for movies with CRUD and mongo DB.

#### Features
- Reactive Webflux
- Spring-Data
- Mongo DB

##

## Application Stack

Stack  | version |
--- | --- |  
*Java* | 11
*SpringBoot* | 2.5.3
*DB* | MongoDB 0.54 (docker)
*DB Client* | MongoDB Compass
*Build Tool* | Maven
*CI* | n/a
*Code Coverage* | n/a

##

## Application Build

## Application Run


## Application URL
curl -v http://localhost:8080/api/v1/movies
curl -v http://localhost:8080/api/v1/movies/612f8ba650390a0ba69a09d2
curl -v http://localhost:8080/api/v1/movies/612f8ba650390a0ba69a09d2/events

## Notes
* Mono - publisher with 0 or 1 elements
* Flux - publisher with 0 or many elements
* Web Client - Asynchronous, non-blocking whereby request are queued; perfect for Mono & Flux types 


## Further enhancements 

