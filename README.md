# Chromium Samples


Sample applications that use and integrate **Equo** Chromium

The samples allow you to run applications that use different toolkits:

- *SWT* (Standart Widget Toolkit)
- *Standalone* (plain Java application as Standalone browser wihout UI depdencies)
- *Windowless* (without GUI window)
- *RCP* (Eclipse RCP application)

> Our **Chromium RCP application** requires **Java 17** and **Maven 3.9** or later versions in order to run.

## Run Equo Chromium samples

### Maven applications

To run a *Maven* application(maven-samples folder), you need to go into the project folder(swt or standalone) and run the command:

```
mvn verify
```

The *SWT* sample has a *Windowless* mode which you can test by running the command:

```
mvn verify -Dwindowless
```

To build the *RCP* sample:

```
mvn clean verify
```

This will build a product called **ChromiumRCP** for the current OS. To run the *RCP* sample:

Linux:

```
./sample.product/target/products/ChromiumRCP/linux/gtk/x86_64/ChromiumRCP
```

Mac:

```
./sample.product/target/products/ChromiumRCP/macosx/cocoa/x86_64/Eclipse.app/Contents/MacOS/ChromiumRCP
```

Windows:

```
./sample.product/target/products/ChromiumRCP/win32/win32/x86_64/ChromiumRCP.exe
```

You can also run the *RCP* sample from **Eclipse**:

Import the **RCP** folder as an **Existing project from folder or archive** -> Open the target file **sample.rcp.target**, wait for the dependencies to be downloaded and click on **Set as Active Target Platform** -> Open the product file **sample.rcp.product** inside folder **sample.product**, and click on **Launch an Eclipse RCP Application** to run the sample.

### Gradle applications

To run a *Gradle* application(gradle-samples folder), you need to go into the project folder(swt or standalone) and run the command:

```
./gradlew run
```

The *SWT* sample has a *Windowless* mode which you can test by running the command:

```
./gradlew run --args=windowless
```


These basic applications were built as simple samples but you can make use of the [Equo documentation](https://docs.equo.dev/main/getting-started/introduction.html) where you will find everything you need for your development.

