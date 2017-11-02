# Traveling

Application model which describes the behavior of buying a tour for vacation

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

After starting jar file from console, you should see next:

1. Show accessible tours for determined date;
2. Show tours on floor price;
3. Choose tour by id and to look up detailed information;
4. Select class of room;
5. Book tour;
6. Buy tour;
7. Exit;

## Testing

Please use next rules for test the app:

1 -
``
2017/5/1
``

2 -
``
10000
``

3 -
``
You can see and choose id in the console
``

4 -
``
You can selecte type of room of available
``

5 -
``
Maksimov Maksim
``

6 -
``
Maksim; Maksimov; 111111111111; 06/20; 111
``

## Built With

* [Facade](https://dzone.com/articles/design-patterns-uncovered-1) - Used to All model
* [Gradle](https://gradle.org/) - Dependency Management
* [StreamEx](https://mvnrepository.com/artifact/one.util/streamex) - Used to generate Streams
* [Vavr.io](http://www.vavr.io/vavr-docs/) - Functional library for Java 8+

## Versioning

We use manual mode for versioning. Current version is 1.0.1 

## Authors

* **Sergey Korol** - *Basic work* - [SergeyKorol](https://github.com/sskorol)
* **Maksim Gerasimenko** - *Initial work* - [MaksimGerasimenko](https://github.com/maksimgerasimenko)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details