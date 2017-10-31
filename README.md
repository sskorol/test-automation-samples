# PlanetaKino
Example of console application which submits the functions for booking cinema tickets.

## Getting Started

### Deployment

1. Clone the repository into your IDE;
2. Create jar file with next command:
+ ./gradlew clean jar (for Mac/Linux);
+ gradlew clean jar (for Windows).

3. Run jar file:
``
$Project\build\libs>java -jar $created_jar_file.jar
``
## Running

After starting jar file, Menu should be displayed in the console:

 1. Log in.
 2. Show all films in the cinema.
 3. Show all dates for the selected film.
 4. Show halls for the selected date.
 5. Show time of sessions in the selected hall.
 6. Show available places for the session with selected time.
 7. Book place.
 8. Exit.

## Testing

Please use next test data for testing the app:

1 -
``
 Use phone number: 380501234567 AND password: 1234
``

3 -
``
Select any film title from console output after performing Menu item 2.
``

4 -
``
Select any date from console output after performing Menu item 3.
``

5 -
``
Select hall type from console output after performing Menu item 4.
``

6 -
``
Select session time from console output after performing Menu item 5.
``

7 -
``
Select row and number of available place from console output after performing Menu item 6.
``

## Built With

 * [Facade](https://dzone.com/articles/design-patterns-uncovered-1) - Used for model building
 * [Gradle](https://gradle.org/) - Dependency Management
 * [StreamEx](https://mvnrepository.com/artifact/one.util/streamex) - Used to generate Streams
 * [Vavr.io](http://www.vavr.io/vavr-docs/) - Functional library for Java 8+
 * [Commons-lang3](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.6) - A package of Java utility classes 
 
 ## Versioning
 
Current version is 0.0.1

## Authors

* **Sergey Korol** - *Basic work* - [SergeyKorol](https://github.com/sskorol)
* **Elena Milshina** - *Initial work* - [ElenaMilshina](https://github.com/emilshina)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details