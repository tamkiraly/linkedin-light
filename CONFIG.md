# Configuration for LinkedIn Light

<b>Development tools:</b>

- framework: Spring Boot version 2.7.3
- build: Gradle 7.5 <i>.jar</i> file
- database: MySQL
- database migration: Flyway 9.3.0
- API documentation tool: Swagger-ui 3.0.0
- version control system: Git
- CI/CD tool: GitHub Actions

<b>Evironment variables:</b>

`.env` file

DATASOURCE_URL=URL of data source (e.g.: <i>jdbc:mysql://localhost/linkedin_light?connectionTimeZone=UTC</i>)

DATASOURCE_USERNAME=username of datasource

DATASOURCE_PASSWORD=password of datasource

<b>Endpoints:</b>

Base URL: <i>localhost:8080/</i>

1. `/client` (POST)
2. `/position` (POST)
3. `/position/search` (GET)
4. `/position/{id}` (GET)

<b>API descriptions and 'tryouts':</b> http://localhost:8080/swagger-ui/