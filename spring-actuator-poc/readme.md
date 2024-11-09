## Проект песочница для SpringActuator

!!! Все что связано с actuator перенесено в generic-project-poc
!!! DataSource configuration samples -> Spring Data JPA sample project: spring-data-jpa
Мониторинг различных ресурсов:
* База данных
* Компоненты приложения


Endpoint: http://localhost:8080/hello-actuator-poc/message  
Информация о приложении: http://localhost:8081/hello-actuator-management/actuator/health

Запуск Docker c базой данных: docker-compose up -d  
Остановка Docker: docker-compose down