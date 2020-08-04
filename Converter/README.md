git clone https://github.com/freshnoon/Converter
Зайти в клонированную папку проекта с помощью проводника, зайти в \Converter\Converter\src\main\resources. Открыть файл application.properties. Установить имя и пароль пользователя от postgresql с полными правами. При необходимости установить также верный порт.

Запустить pgadmin и с помощью query tool выполнить скрипт:



cd Converter/Converter
mvn package
cd target
java -jar Converter-0.0.1-SNAPSHOT.war