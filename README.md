# java_jdbc

## Overview
This project is a Java application that uses JDBC to connect to a MySQL database. It is built using Maven for dependency management and project structure.

## Requirements
- Java 17
- Maven 3.6.3 or higher
- MySQL database

## Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/java_jdbc.git
   cd java_jdbc```

2. **Configure the database connection:** Update the database connection details in your Java code `DatabaseConnection`.

3. **Build the project:**
   ```sh
    mvn clean package```
   
4. **Run the application:**
    ```sh
    java -jar target/java_jdbc-1.0-SNAPSHOT.jar```
   
5. **Follow the instructions in the console to interact with the application.**
6. **To exit the application, type `exit` and press Enter.**

### Project Structure
- `src/main/java`: Contains the Java source files.
- `src/main/resources`: Contains the resource files.
- `pom.xml`: Maven configuration file.


### Dependencies
- `mysql:mysql-connector-java:8.0.33`: MySQL JDBC driver for database connectivity.


### License
This project is licensed under the MIT License.

