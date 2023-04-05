# Chromium Samples


Sample applications that use and integrate **Equo** Chromium

The samples allow you to run applications that use different toolkits:

- *SWT* (Standart Widget Toolkit)
- *Standalone* (plain Java application as Standalone browser wihout UI depdencies)
- *Windowless* (without GUI window)
- *Swing* (Swing Toolkit)

## JxBrowser replacement

You can run SWT and Swing examples with JxBrowser code using **Equo Chromium JX** provided inside the ***jx*** folder. Read more about it in [Equo documentation](https://docs.equo.dev/chromium/95.x/getting-started/migrating-from-jxbrowser.html) where you will find everything you need to migrate from JxBrowser quickly.

## Run Equo Chromium samples

### Maven applications

To run a *Maven* application(maven-samples folder), you need to go into the project folder(swt, standalone, swing, jx/swt or jx/swing) and run the command:

```
mvn verify
```

The *SWT* sample has a *Windowless* mode which you can test by running the command:

```
mvn verify -Dwindowless
```

### Gradle applications

To run a *Gradle* application(gradle-samples folder), you need to go into the project folder(swt, standalone or swing, jx/swt or jx/swing) and run the command:

```
./gradlew run
```

The *SWT* sample has a *Windowless* mode which you can test by running the command:

```
./gradlew run --args=windowless
```


These basic applications were built as simple samples but you can make use of the [Equo documentation](https://docs.equo.dev/main/getting-started/introduction.html) where you will find everything you need for your development.

