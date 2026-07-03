# ishtech-validations-java

Reusable Java Bean Validation (Jakarta Validation) constraints for IshTech projects.

## Constraints

- `@MaxCurrentYear` - validates that a numeric value (`short`/`int`/`long` or their wrapper types) does not exceed the current calendar year. `null` is considered valid.

## Usage

- Note: in pom.xml / build.gradle put required version number

### Maven

```
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

```
./mvnw clean deploy -P gpg -P central-publishing
```
