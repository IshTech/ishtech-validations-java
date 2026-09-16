# ishtech-validations-java

Custom Java Bean Validation constraints based on Jakarta Validation.

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

## Deploy to Sonatype Central

```sh
./mvnw clean deploy -P gpg -P central-publishing
```

## Constraints

### `@MaxCurrentYear`

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
