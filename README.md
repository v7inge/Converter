1. git clone https://github.com/freshnoon/Converter
2. Зайти в клонированную папку проекта с помощью проводника, зайти в \Converter\Converter\src\main\resources. Открыть файл application.properties. Установить имя и пароль пользователя от postgresql с полными правами. При необходимости установить также верный порт.
3. Запустить pgadmin. Создать БД с именем Converter. Выбрать ее и с помощью query tool выполнить скрипт:

CREATE TABLE public.Currency
(
	id VARCHAR(10) PRIMARY KEY NOT NULL, numcode VARCHAR(3), charcode VARCHAR(3), name TEXT
);

CREATE TABLE public.Rate
(
	id SERIAL PRIMARY KEY NOT NULL, currency_id VARCHAR(10) REFERENCES Currency (id), value real NOT NULL, date date
);

CREATE TABLE public.Event
(
	id SERIAL PRIMARY KEY NOT NULL, currency_id_1 VARCHAR(10) REFERENCES Currency (id), currency_id_2 VARCHAR(10) REFERENCES Currency (id), sum real, result real, date date
);

4. cd Converter/Converter
5. mvn package
6. cd target
7. java -jar Converter-0.0.1-SNAPSHOT.war
8. Открыть в браузере http://localhost:8080. Ввести учетные данные. Имя: user, пароль: 111.