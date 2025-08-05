<h1> Ситсема расчета страховки для путешествий </h1>

## Используемые технологии:
- Java
- Spring, Spring Boot
- Spring Data Jpa, Hibernate, PostgreSQL, H2, Liquibase
- JavaScript, Thymeleaf
- Gradle
- JUnit, Mockito
- Logback

  ## Описание проекта
 Данный проект является Rest + Web приложением которое может
 рассчитывать страховку для одного или нескольких людей, и для разного количества рисков.
 Есть две версии интерфейсов взаимодействия с приложением. Первая поддерживает расчёт для одного человека, а вторая для нескольких.
 Endpoints:
 <img width="1089" height="366" alt="image" src="https://github.com/user-attachments/assets/80495ec5-9a09-484a-83d4-41eb5061e952" />

 Прмиер Json для Rest:
 Для первой версии

{
"personFirstName" : "Хамит",
"personLastName" : "dd",
"agreementDateFrom" : "25.02.2026",
"agreementDateTo" : "30.02.2026",
"personCode":"code1",
"country":"Россия",
"medicalLimit":1,
"birthday":"22.02.2003",
"selectedRisks":["Медицинский риск"]
}


Для второй, ставте даты в будущем

{
"personList":[{
"personFirstName" : "Хамит",
"personLastName" : "dd",
"birthday":"22.02.2003",
"personCode":"code2"
},{
"personFirstName" : "Хамит",
"personLastName" : "dd3",
"birthday":"22.02.2001"
"personCode":"code3"
}]
"agreementDateFrom" : "25.02.2026",
"agreementDateTo" : "30.02.2026",
"country":"Россия",
"medicalLimit":1,
"selectedRisks":["Медицинский риск","Отмена поездки"]
}

 Пример веб 2 версии
 <img width="815" height="825" alt="image" src="https://github.com/user-attachments/assets/07690e23-f00d-4e69-9ce5-7037e57aa7cd" />

 ## Запуск приложения
 Есть два способа запуска приложения.
 1. Установить PostgreSql Server с нужными пользователем и базой данных.
    А потом уже запустить приложение и Liquibase сам всё создаст и заполнить.
2. Использовать h2 in memory базу данных. Для этого в application.property раскомментировать настройки для h2 и закоментировать для Postgre.

