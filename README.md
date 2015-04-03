# FNAG Result Analyser

Xebia Coding Test - Java 8

## Build

The project uses Maven (version 3.0.5 or higher) to handle its dependencies and its build process.

```
mvn clean install
```

The build produces a jar file in the target directory.

## Usage

The FNAG Result Analyser analyses a global results file. The path of the file is provided as an argument of the program.

```
java -jar target/fnag-1.0.0-SNAPSHOT.jar --resultPath=/path/to/results/file
```
