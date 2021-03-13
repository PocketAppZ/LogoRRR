# LogoRRR

![Screenshot](screenshot.png)

LogoRRR is a simple utility to display a logfile and visualise certain classes of events. 

For example, ERROR events are visualized as red rectangles, TRACE events as grey rectangles, INFO events as green ones etc. 

You can give it a try by downloading a [prebuilt binary for LogoRRR](https://github.com/rladstaetter/LogoRRR/releases/tag/21.1.3) from the releases page.

## Usage

You can start LogoRRR via double click from the Desktop. You can select a log file to display via drag'n drop. 

Alternatively, you can start LogoRRR via command line and provide the path to the log file as command line parameter. 


## Motivation

This project serves as a vehicle to test and learn about various aspects of JavaFX GraalVM compilation. [This blog post](https://ladstatt.blogspot.com/2020/10/compile-scala-javafx-application-with.html) was written to accompany the initial public release of this github repository, describing challenges performing AOT with GraalVM and the surrounding tooling. 

## Prerequisites

You have to download a recent GraalVM installation package (I've tested it with 21.0.0.r11) and a maven distribution (everything starting from 3.6.3 should work).

Currently this application can analyze log files which were generated by `java.util.logging`. The log output is dependent on your locale, you can override settings by providing `-Duser.language=en` as JVM parameter for your application.

## Building

Given you have followed the instructions for [maven-client-plugin](https://github.com/gluonhq/client-maven-plugin) you only have to provide the path to your GraalVM installation in the main pom.xml found at the root folder of the project.


### MacOs X

     mvn clean package

Should create a binary executable which can be run without a JVM:

    ./target/client/x86_64-darwin/net.ladstatt.logorrr.logorrr

### Windows    

You have to enter following command in a Visual Studio Developer console:

     mvn clean package

A binary is available here after some minutes:
    
    target\client\x86_64-windows\logorrr.exe

If you want to execute this exe on another computer you have to make sure that the Visual Studio runtime environment is present there (redistributables).


## Running from IntelliJ

Running JavaFX applications in IntelliJ needs a little bit more work if you work with modularized Java. There are builds of Java which incorporate JavaFX (from Azul for example) where following steps are not necessary. However, if you use GraalVM or AdoptOpenJDK builds, you have to provide special VM Parameters in order to start a JavaFX application from your IDE. 

As such, you have to provide following parameters:

    --module-path <path to your javafx sdk lib directory> --add-modules javafx.controls,javafx.fxml

For me following settings work:

    --module-path /Users/lad/gh/javafx-sdk-16/lib --add-modules javafx.controls,javafx.fxml

Note that you have to download this javafx-sdk separately and install it once on your computer, you can get it on [Gluon's Download Page for JavaFX](https://gluonhq.com/products/javafx/). 

A comprehensive tutorial how to get started with developing JavaFX can be found on [openjfx.io](https://openjfx.io). 

## Configuring GraalVM native-image 

Recently a new feature for client maven plugin was introduced to help configure the underlying native-image technology. You have to provide configuration for reflection for example, or native code which is to be called by the application. 

Configuring those aspects may swiftly become a challenge. Luckily client-maven-plugin has a target which makes those configuration steps easier, at least you get a good starting point. Just issue following maven command on your command line:

    mvn client:runagent

It will start LogoRRR. Now you have to execute all possible click paths - in your code repository configuration files will appear in `src/main/resources/META-INF/native-image/`. Those files are different depending on which OS you execute the application. 

Currently, those files are generated / checked in under src/main/native-image/<os>/, and via a maven configuration they are placed in the right place needed for native-image. Like this the project can be build on multiple platforms via 

    mvn package 

Implicitly, maven package will call `client:build` and create a binary executable. (On Windows the binary will be packed via [UPX](https://upx.github.io) to reduce the file size considerably.)


## Features

- drag and drop log files to application to visualize/view them
- handle multiple log files in parallel
- filter log files for entries interactively


## License

This software is licensed under Apache-2 License.
