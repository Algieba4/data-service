# data-service

The purpose of this project is to service as an example on how to use various new
Java and Spring Boot / Spring Framework features as new version come out. It is also
intended to be used as a guideline on how to embed reliability into your codebase 
while developing new features.

## Installation

To set up the mysql container, you can either open this project in your favorite IDE 
and click on the green arrow within /dependencies/mysql. Or you can run the commands 
below within a terminal.

```shell
cd $folderpath
cd data-service/dependencies/mysql
docker compose up -f docker-compose.yml
```

## Usage

See [HELP.md](HELP.md) for more detailed examples.

### API Version Control

TBA

### JsonMapper

### Micrometer

TBA

### OpenTelemetry 

Spring Boot 4 comes with its own OpenTelemetry dependency

```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-opentelemetry -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-opentelemetry</artifactId>
</dependency>
```

### RestTestClient

TBA

### Swagger

TBA

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update the following as necessary:
- test cases
- README.md
- HELP.md

## License

None
