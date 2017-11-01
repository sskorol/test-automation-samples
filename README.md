# Online Library

Application model  describes the behavior of browsing  books and adding it to favourite list

## Getting Started

### Deployment

1. Clone this repository on your development environment;
2. Create jar file with next command:
``
$Project>gradle fatJar
``
3. Run it:
``
$Project\build\libs>java -jar $created_jar_file.jar
``
## Running

After starting jar file from console, you'll see :

1. Login
2. Browse books by category
3. Show all books
4. Search book (criteria)
5. Show favourite books
6. Add to favourite
7. Exit

## Testing

Please use next cases for testing:

1 -
``
dan123
``
``
123456789
``

2 -
``
ADVENTURE
``

3 -
``
List of all books is displayed
``

4 -
``
Henry
``

5 -
``
List of favourite books is displayed
``

6 -
``
Henry IV, Part Two (Henry IV, Part II)
``

## Built With

* [Facade](https://en.wikipedia.org/wiki/Facade_pattern) - Used for project
* [Gradle](https://gradle.org/) - Build Tool
* [StreamEx](https://mvnrepository.com/artifact/one.util/streamex) - Used  Streams functionality
* [Vavr.io](http://www.vavr.io/vavr-docs/) - Functional library for Java 8+

## Versioning

Current version is 0.0.1

## Authors

* **Sergey Korol** - *Basic work* - [SergeyKorol](https://github.com/sskorol)
* **Natali Gorb** - *Initial work* - [Natali Gorb](https://github.com/Natali-QA)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details