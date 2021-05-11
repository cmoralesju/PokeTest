# PokeTest
PokeTest is a basic Pokemon Automation Test Suite.

## Installation
Clone the project in your workspace

```bash
git clone https://github.com/cmoralesju/PokeTest.git
```

Make sure you hace installed maven & java, and your environment variables are a configures

```bash
JAVA_HOME = <Java Directory Path>
```
```bash
M2_HOME = <Maven Directory Path>
```
Note: You can download Maven from here: https://maven.apache.org/download.cgi
Once downloaded you just need to unzip it and configure your environment value

You can verify your Java version and Maven version by opening a windows command prompt and
execuitng the following commands

```bash
javac -version
```
```bash
mvn -v
```

## Execution
Go to the PokeTest project directory and open a windows command prompt, the 
execute the following command 

```bash
mvn clean install -Dcucumber.Options="--tags @PokeTest"
```

