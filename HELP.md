# How to Run the Transaction API Project

This guide will walk you through the steps required to set up and run the Transaction API project on your local machine. The project is built using Java and Spring Boot framework.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

1. **Java Development Kit (JDK)**: Download and install the JDK appropriate for your operating system from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Apache Maven**: Maven is used for building and managing the project dependencies. Download and install Maven from the [official website](https://maven.apache.org/download.cgi).

3. **PostgreSQL Database**: The project uses PostgreSQL as the database. You can download and install PostgreSQL from the [official website](https://www.postgresql.org/download/), or use a cloud-based PostgreSQL service like Amazon RDS.

## Clone the Repository

1. Open your terminal or command prompt.

2. Clone the repository to your local machine using the following command:

    ```
    git clone git@github.com:Brenolima03/spring-boot-transaction-api.git
    ```

    Replace `your-username` with your actual GitHub username.

## Configuration

1. **Database Configuration**:
   
    - Open the `application.properties` file located in the `src/main/resources` directory.
    
    - Configure the PostgreSQL database connection properties such as `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` according to your PostgreSQL setup.

2. **Build the Project**:
   
    - Navigate to the project directory in your terminal.
    
    - Run the following Maven command to build the project:
    
        ```
        mvn clean install
        ```

## Run the Application

Once the project is built successfully, you can run the Transaction API application using the following steps:

1. Navigate to the project directory in your terminal.

2. Run the following command to start the application:

    ```
    mvn spring-boot:run
    ```

3. The application will start, and you should see log messages indicating that the server is running.

## Accessing the API

Once the application is running, you can access the API endpoints using a tool like Postman or curl. The base URL for the API is `http://localhost:8080`.

## Accessing the Swagger Documentation

The Transaction API project includes Swagger for API documentation. Swagger provides an interactive interface to explore and test the API endpoints.

Ensure the application is running.

Open your web browser and navigate to the following URL:

bash
Copiar código
http://localhost:8080/swagger-ui.html
You will see the Swagger UI interface, where you can explore the available API endpoints and test them directly from the browser.

## Contributing

If you encounter any issues or have suggestions for improvements, feel free to open an issue or submit a pull request on the GitHub repository.

Happy coding!
