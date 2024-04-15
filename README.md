# Backend - часть для приложения "Notes"
Технологии:
+ Java 17
+ Spring Boot
+ Maven
+ Hibernate
+ PostgreSQL

Database diagramm

![DB diagram](https://github.com/AndreyStaroverov/Notes/blob/main/Database.png)

HTML+JS+CSS. Был сделан простейший визуальный интерфейс, где мы выбираем ID пользователя, получаем заметки 
(Всегда есть минимум 1), затем можем обновлять, удалять, создавать новые.

![Visual](https://github.com/AndreyStaroverov/Notes/blob/main/Visual.png)


> В ближайшей перспективе было бы дописать более качественный front с регистрацией пользователей и добавлением картинок 
> (Модель Note бы хранили линки на картинки в бд, которые на фронте дергали бы эти картинки с другого облачного хранилища 
> типа S3, чтобы не нагружать БД)


Будущее проекта, технологии, которые будут добавляться и развивать проект:

![Future](https://github.com/AndreyStaroverov/Notes/blob/main/Future.jpg)


Чтобы испытать необходимо запустить SeverstalNoteApplication и открыть commonNew.html в браузере, базово используется
для бэкэнда: localhost:8080/ { Используемое API в контроллерах }
для фронта: http://localhost:63342/Notes/templates/parts/commonNew.html

Ввести в поле userId "1" и нажать Fetch, тогда подгрузятся все заметки, пока нет регистрации, пользователя через SQL
создаем сами на старте, это все простое решение для того, чтобы продемонстрировать работу.
