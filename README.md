# ishtech-validations-java

Custom Java Bean Validation constraints based on Jakarta Validation.

## Tech stack

- JDK 25 (default)
- Other supported JDK versions:
  - JDK 21
  - JDK 17

Which library version to use for your JDK version: [JDK-VERSIONS.md](JDK-VERSIONS.md).

## Usage

- Note: in `pom.xml` / `build.gradle` put required version number

### Maven

```xml
<dependency>
	<groupId>fi.ishtech.common</groupId>
	<artifactId>ishtech-validations</artifactId>
	<version>${ishtech-validations.version}</version>
</dependency>

```

### Gradle

```
implementation("fi.ishtech.common:ishtech-validations:${ishtechValidationsVersion}")
```

### Code Samples

#### `@MaxCurrentYear`

Validates that a numeric value does not exceed the current calendar year.

- Supported types: `short`, `int`, `long` and their wrapper types.
- `null` values are considered valid; use `@NotNull` alongside it if a value is required.
- `inclusive` (default `true`) - when `true`, the current year itself is a valid value; when `false`, only years strictly before the current year are valid.

Example, on a POJO field:

```java
public class Book {

	@NotNull
	@Min(1900)
	@MaxCurrentYear
	private Integer publicationYear;

	@MaxCurrentYear(inclusive = false)
	private Integer copyrightYear;

}
```

## Build

This is a library; it **does not run** as a standalone application.

### Maven

#### Local Maven Build

- Build without tests

```sh
./mvnw clean install -DskipTests
```

- Build with Junit tests

```sh
./mvnw clean install
```

## Publish to Maven Central

- Deploy to Sonatype Central

  ```sh
  ./mvnw clean deploy -P gpg -P central-publishing
  ```

## Known Issues

See [KNOWN-ISSUES.md](KNOWN-ISSUES.md).
