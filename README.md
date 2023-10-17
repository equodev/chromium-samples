# Chromium Samples


Sample applications that use and integrate **Equo** Chromium

The samples allow you to run applications that use different toolkits:

- *SWT* (Standart Widget Toolkit)
- *Standalone* (plain Java application as Standalone browser wihout UI depdencies)
- *Windowless* (without GUI window)
- *Swing* (Swing Toolkit)

> **Swing based apps** require **Java 11** or later versions in order to run.

## Run Equo Chromium samples

### Maven applications

To run a *Maven* application(maven-samples folder), you need to go into the project folder(swt, standalone, swing) and run the command:

```
mvn verify
```

The *SWT* sample has a *Windowless* mode which you can test by running the command:

```
mvn verify -Dwindowless
```

### Gradle applications

To run a *Gradle* application(gradle-samples folder), you need to go into the project folder(swt, standalone or swing) and run the command:

```
./gradlew run
```

The *SWT* sample has a *Windowless* mode which you can test by running the command:

```
./gradlew run --args=windowless
```


These basic applications were built as simple samples but you can make use of the [Equo documentation](https://docs.equo.dev/main/getting-started/introduction.html) where you will find everything you need for your development.

