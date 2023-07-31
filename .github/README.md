<div align="center">
  
# :airplane: Milhas API 

![Docker](https://img.shields.io/badge/-Docker-000?&logo=Docker)
![Linux](https://img.shields.io/badge/-Linux-000?&logo=Linux)
![Spring](https://img.shields.io/badge/-Spring-000?&logo=Spring)
![MySQL](https://img.shields.io/badge/-MySQL-000?&logo=MySQL)
![Git](https://img.shields.io/badge/Git-000?&logo=Git)
![VSCode](https://img.shields.io/badge/VSCode-000?&logo=visualstudiocode)

:construction: <font color="yellow">in developement</font>

</div>

<a href="https://www.alura.com.br/challenges/back-end-7/">
    <img src="https://www.alura.com.br/assets/img/challenges/logos/logo-challenges-back-end.1680020826.svg" alt="Aimeos logo" title="Alura Challenges" align="left" height="60px"/>
</a>

The goal is to provide information and resource related to possible travel destinations, showing photos, texts and also resources about testimonials from other travelers. In addition to integrating AI into your application.

-------------

<br/>

## :mag: Table Of Content

- [About](#scroll-about)
  - [Used Technologies](#used-technologies)  
- [The Challenge](#dart-the-challenge)
    - [Project Steps](#project-steps)
- [Walkthrough](#footprints-walkthrough)
- [API Doc](#scroll-api-doc)

## :scroll: About

API in development to the [7th Back-end Challenge](https://www.alura.com.br/challenges/back-end-7/) of Alura.

### Used Technologies:
- Language: Java
- Framework: Spring Boot 
- Migration Tool: Flywaydb (migrations)
- Documentation: SpringDoc
- Other libraries: Lombok, DataFaker
- Database: MySQL
- Docker (development enviroment)
- VS Code

## :dart: The Challenge

> In this 7th Backend challenge, we are going to develop an API that will be integrated with Frontend. Our challenge is to provide information and resources from the database related to possible travel destinations, displaying photos and eye-catching text that encourages the user to want to visit that destination.
>
> In addition, we will also provide resources on testimonials from other travelers and, finally, we will integrate AI into our application.

### Project steps

- **<font color="green">First Week</font>**
    - **Testimonial CRUD** Create `/depoimentos` endpoint to perform CRUD operations. 
    - **Random Testimonials**: Create `depoimentos-home` endpoint to show 3 random testimonials
    - **CORS** Enable CORS request from any origin on the development phase.
    - **Test** Verify the status code of GET, POST, PUT, DELETE of `/depoimentos` endpoint 

- **<font color="green">Second Week</font>**
    - **Destination CRUD** Create `/destinos` endpoint to perform CRUD operations.
    - **Search Destination** by name.
    - **Test** `destinos` endpoint

- **Third and Fourth Week**
    ...

## :footprints: Walkthrough

Check my steps to develop this project [here](DEV_WALKTHROUGH.md)

## :scroll: API Doc 

This projects uses [SpringDoc](https://springdoc.org/) to generate API documentation. You can check it by runing the project and accessing [swagger-ui](localhost:8080/swagger-ui/index.html)

<!-- ------------


| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Milhas API**
| :label: Tecnologias | Java Spring Boot, Docker, MySQL, Flyway, OpenAPI Swagger 
| :rocket: URL         | https://github.com/ecureuill/milhasapi
| :fire: Desafio     | https://www.alura.com.br/challenges/back-end-7/

Inserir imagem com a #vitrinedev ao final do link -->
