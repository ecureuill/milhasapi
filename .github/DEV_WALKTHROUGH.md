# Development Walkthrough

## :tada: Creating a new project

1. Create a project from `Spring Initializr`
    - Java `17`
    - Maven `3.1.2`
    - Dependecies
        - Spring Boot DevTools
        - Spring Data JPA
        - Lombok
        - Flyway Migration
        - MySQL Driver

1. Add extra dependencies

    https://github.com/ecureuill/milhasapi/blob/bce5a450ec3354e84bd6b17c8b16a6ed9b1b3ebd/pom.xml#L35-L46

1. Initial configuration

    https://github.com/ecureuill/milhasapi/blob/bce5a450ec3354e84bd6b17c8b16a6ed9b1b3ebd/src/main/resources/application.properties#L1-L3

        ℹ️ `mysqldev` is the mysql container name that is running in the same container network as my java development container

1. Create Database

    ```sql
    CREATE DATABASE milhas_api;
    ```
1. Run it :checkered_flag:
    
    Now you can run the project and access `http://javahost:8080/swagger-ui/index.html`
    
    ![Swagger-UI Page](/.github/imgs/image-1.png)

## :wrench: First Swagger configuration

https://github.com/ecureuill/milhasapi/blob/bce5a450ec3354e84bd6b17c8b16a6ed9b1b3ebd/src/main/java/ecureuill/milhasapi/infra/doc/SpringDocConfig.java#L11-L25

![Updated Swagger-UI](/.github/imgs/image-2.png)

## :woman_technologist: Testimonals

1. Create table `testimonials` through flyway migration file

    https://github.com/ecureuill/milhasapi/blob/5a2c7281d8239574a62ab3024ef3db7e17e33d53/src/main/resources/db/migration/V1__create_table_testimonials.sql#L1-L7

1. Create entity

    https://github.com/ecureuill/milhasapi/blob/50043332d3753037fab0ef3abc8fd630fd1b54bd/src/main/java/ecureuill/milhasapi/domain/testimonial/Testimonial.java#L13-L32

1. Create repository

    https://github.com/ecureuill/milhasapi/blob/5a2c7281d8239574a62ab3024ef3db7e17e33d53/src/main/java/ecureuill/milhasapi/domain/testimonial/TestimonialRepository.java#L5-L7

### :woman_technologist:  Post request

1. Create `TestimonialDetailRecord` and `TestimonialCreateRecord`

    https://github.com/ecureuill/milhasapi/blob/5a2c7281d8239574a62ab3024ef3db7e17e33d53/src/main/java/ecureuill/milhasapi/domain/testimonial/TestimonialDetailRecord.java#L3-L12

    https://github.com/ecureuill/milhasapi/blob/5a2c7281d8239574a62ab3024ef3db7e17e33d53/src/main/java/ecureuill/milhasapi/domain/testimonial/TestimonialCreateRecord.java#L5-L13

1. Create `depoimentos` endpoint and POST request

    https://github.com/ecureuill/milhasapi/blob/1b4d49114919f1ca67b1bf4c6fbd431149cde529/src/main/java/ecureuill/milhasapi/controller/TestimonialController.java#L20-L38

1. Run it :checkered_flag:

    You can check in swagger, the newly created endpoint

    ![Swagger-UI](/.github/imgs/image-3.png)

### :woman_technologist:  Get, PUT and DELETE requests

1. Create `TestimonialUpdateRecord`

    https://github.com/ecureuill/milhasapi/blob/5a2c7281d8239574a62ab3024ef3db7e17e33d53/src/main/java/ecureuill/milhasapi/domain/testimonial/TestimonialUpdateRecord.java#L5-L13

1. Update `TestimonialController`

    https://github.com/ecureuill/milhasapi/blob/5a2c7281d8239574a62ab3024ef3db7e17e33d53/src/main/java/ecureuill/milhasapi/controller/TestimonialController.java#L48-L77

1. Run it :checkered_flag:
   
    ℹ️ You can check in swagger, the newly created requests verbs

### :woman_technologist:  Create `depoimentos-home` endpoint
1. Add `findThreeTestimonials` query to `TestimonialRepository`

    https://github.com/ecureuill/milhasapi/blob/0b7c5991b53b350dd51c904c9496ed0a9a37b4b5/src/main/java/ecureuill/milhasapi/domain/testimonial/TestimonialRepository.java#L10-L15

1. Create `RandomTestimonialController`

    https://github.com/ecureuill/milhasapi/blob/0b7c5991b53b350dd51c904c9496ed0a9a37b4b5/src/main/java/ecureuill/milhasapi/controller/RandomTestimonialController.java#L15-L27

## :mag: Unit Testing Controllers

1. Add datafaker depedency
    
    https://github.com/ecureuill/milhasapi/blob/5b8313f2eb50efcbd5606cd6d55952ecb38292b8/pom.xml#L21-L25

1. Create fake data

    https://github.com/ecureuill/milhasapi/blob/5b8313f2eb50efcbd5606cd6d55952ecb38292b8/src/test/java/ecureuill/milhasapi/GenerateData.java#L10-L27

1. Create `TestimonialControllerUnitTest`

    https://github.com/ecureuill/milhasapi/blob/5b8313f2eb50efcbd5606cd6d55952ecb38292b8/src/test/java/ecureuill/milhasapi/controller/TestimonialControllerUnitTest.java#L37-L247

1. Run tests 🧪 

    ![Tests output](/.github/imgs/image-4.png)

1. Fix code :bug:

    https://github.com/ecureuill/milhasapi/blob/5b8313f2eb50efcbd5606cd6d55952ecb38292b8/src/main/java/ecureuill/milhasapi/controller/TestimonialController.java#L52-L68

        ⚠️ Check full code alteration on [commit](https://github.com/ecureuill/milhasapi/commit/5b8313f2eb50efcbd5606cd6d55952ecb38292b8)

1. Run test again 🧪

    The only failing test should be `testUpdateStatusCodeNotFound` due to all exception is being handler by Spring that returns status code `500`.

    ![Tests output](/.github/imgs/image-5.png)

## :wrench: CORS

https://github.com/ecureuill/milhasapi/blob/aa4a16f26ccd6bb6941fe9ae3f5c88c93c3c2e4f/src/main/java/ecureuill/milhasapi/infra/security/CorsConfig.java#L6-L17

<br/>

-------------


# Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#using.devtools)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web.spring-hateoas)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#howto.data-initialization.migration-tool.flyway)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

