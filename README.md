# 🍃 Spring

## ![💻 Project 1](https://github.com/Delphington/SpringProjects/tree/master/Library)

**Задание:** В местной библиотеке хотят перейти на цифровой учет книг. Вам
необходимо реализовать веб-приложение для них. Библиотекари
должны иметь возможность регистрировать читателей, выдавать им
книги и освобождать книги (после того, как читатель возвращает
книгу обратно в библиотеку).

### 🔩 Стек

![REST](https://img.shields.io/badge/REST-API-brightgreen)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-logo-316192?logo=postgresql)](https://www.postgresql.org/)
![JDBC](https://img.shields.io/badge/JDBC-Template-red)
[![Spring Web](https://img.shields.io/badge/Spring%20Web-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-framework)
[![Spring Core](https://img.shields.io/badge/Spring%20Core-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-framework)
[![Spring MVC](https://img.shields.io/badge/Spring%20MVC-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-framework)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-logo-C71A36?logo=apache-maven)](https://maven.apache.org/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-logo-005C00?logo=thymeleaf)](https://www.thymeleaf.org/)
[![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-logo-F8DC75?logo=apache-tomcat)](https://tomcat.apache.org/)
[![Lombok](https://img.shields.io/badge/Lombok-logo-47a1c6?logo=lombok)](https://projectlombok.org/)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-logo-C71A36?logo=apache-maven)](https://maven.apache.org/)


**Описание:**
- Проект сконфигурирован на Spring 6 без использования Spring Boot.
- Подключена СУБД PostgreSQL и созданы две таблицы:
- Для работы с базой данных используется `jdbcTemplate`.
- Для отображения шаблонов используется **Thymeleaf**.
- Реализован REST API с поддержкой методов:
    - `GET`
    - `POST`
    - `PATCH`
    - `DELETE`
- Используется валидация с помощью аннотации `@Valid`.

----

## ![💻 Project 2](https://github.com/Delphington/SpringProjects/tree/master/Library2)

**Задание:** Переписать Проект 1 с использованием Hibernate и Spring Data JPA. В вашем
проекте не должно быть ни одного SQL запроса. Должны быть реализованы
сущности (@Entity) Книга и Человек, репозитории и сервисы. PersonDAO и
BookDAO должны быть пустыми и не должны использоваться, вся работа с БД
через сервисы.


### 🔩 Стек

![REST](https://img.shields.io/badge/REST-API-brightgreen)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-logo-316192?logo=postgresql)](https://www.postgresql.org/)
[![Hibernate](https://img.shields.io/badge/Hibernate-logo-59666C?logo=hibernate)](https://hibernate.org/)
[![Spring JPA](https://img.shields.io/badge/Spring%20JPA-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-data-jpa)
[![Docker](https://img.shields.io/badge/Docker-logo-2496ED?logo=docker)](https://www.docker.com/)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-logo-C71A36?logo=apache-maven)](https://maven.apache.org/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-logo-005C00?logo=thymeleaf)](https://www.thymeleaf.org/)
[![Lombok](https://img.shields.io/badge/Lombok-logo-47a1c6?logo=lombok)](https://projectlombok.org/)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-logo-C71A36?logo=apache-maven)](https://maven.apache.org/)

**Описание:**
- Конфигурация: `Spring Web`, `Core → Spring Boot 3`
- Миграция с `JDBCTemplate` → `Hibernate` + `Spring Data JPA`
- Архитектура: Controller-Service-Repository
- Дополнительные фичи:
    - Пагинация и сортировка книг
    - Поиск по начальным буквам названия
    - Автоматическая проверка просрочки возврата (на основе LocalDateTime)

----

## ![💻 Project 3](https://github.com/Delphington/SpringProjects/tree/master/app)

**Задание:** Представьте, что вы приобрели метеорологический датчик (для простоты дальше будем называть его просто "сенсор"). Этот датчик
измеряет температуру окружающего воздуха и может определять, идет дождь или нет. Так как реального сенсора у нас нет, в качестве сенсора будет выступать наш собственный компьютер! То есть на нашем компьютере будет работать сервер со Spring
REST API приложением, и наш же компьютер будет слать HTTP запросы к Spring приложению так, как будто он и есть "сенсор". Для того, чтобы отправлять запросы к REST API приложению, мы
будем использовать знакомый нам RestTemplate, который мы уже использовали в этом курсе для того, чтобы слать запросы к сторонним REST API сервисам.

### 🔩 Стек

![REST](https://img.shields.io/badge/REST-API-brightgreen)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-logo-316192?logo=postgresql)](https://www.postgresql.org/)
[![Hibernate](https://img.shields.io/badge/Hibernate-logo-59666C?logo=hibernate)](https://hibernate.org/)
[![Spring JPA](https://img.shields.io/badge/Spring%20JPA-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-data-jpa)
[![Postman](https://img.shields.io/badge/Postman-logo-FF6C37?logo=postman)](https://www.postman.com/)
[![Jackson](https://img.shields.io/badge/Jackson-logo-black?logo=jackson)](https://github.com/FasterXML/jackson)
[![Docker](https://img.shields.io/badge/Docker-logo-2496ED?logo=docker)](https://www.docker.com/)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-logo-C71A36?logo=apache-maven)](https://maven.apache.org/)
[![Lombok](https://img.shields.io/badge/Lombok-logo-47a1c6?logo=lombok)](https://projectlombok.org/)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-logo-C71A36?logo=apache-maven)](https://maven.apache.org/)

-----

## ![🌱 Другие учбеные проекты](https://github.com/Delphington/StudySpring)
