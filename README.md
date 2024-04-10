# hr-services

# HR Management System

The HR Management System is a comprehensive software solution designed to streamline human resources processes within organizations. It offers a range of functionalities to manage employee data, salaries, attendance and leave management, training sessions, and more.

## Features

- **Employee Management:** Add, view, update, and delete employee information.
- **Salary Management:** Manage employee salaries, including CRUD operations.
- **Vacations Tracking:** Record and manage employee vacation requests.
- **Job History Management:** Review employee's job hsitory.
- **RESTful API:** Provides a RESTful API for interacting with the HR system programmatically.
- **SOAP Web Services:** Supports SOAP web services for integrating with other systems.

## Technologies Used

- Java
- Jakarta EE (formerly Java EE)
- JAX-RS for RESTful services
- JAX-WS for SOAP services
- Maven for project management
- Git for version control

## Prerequisites

**Java 17:** You can download it from here. Set JAVA_HOME to your Java path and add path/to/java/bin to your PATH environment variable. Verify the installation by running java -version in your terminal.  
**MYSQL Database v8.0.32:** You can download it from here. Make sure you choose to install the Sakila Sample database while installing.  
**Apache Tomcat 10.1.7:** You can download it from here. Download the Windows Zip file then extract it. Set CATALINA_HOME to your Tomcat path and add path/to/tomcat/bin to your PATH environment variable. Start the server by running catalina start in your terminal.  
**Apache Maven:** You can download it from here. Download the Binary Zip Archive. Add path/to/maven/bin to your PATH environment variable. Verify the installation by running mvn -version in your terminal. 


## Getting Started

To get started with the HR Management System, follow these steps:

1. Ensure you have Apache Tomcat installed. If not, you can download it from [here](http://tomcat.apache.org/) and follow the installation instructions provided.

2. Clone the repository:
```https://github.com/chamehleon/hr-services.git```
3. Build the project using Maven
```mvn clean install```
4. Deploy the built WAR file to your Tomcat server. You can do this by copying the WAR file from the target directory to the webapps directory in your Tomcat installation.

Start your Tomcat server:
``` bash
cd /path/to/tomcat/bin
./startup.sh   # for Unix/Linux
./startup.bat  # for Windows
```

5.Access the application at ```http://127.0.0.1:{Tomcat PORT}/api/rest/{required end point}``` For REST-API.

or Access the application at ```http://127.0.0.1:{Tomcat PORT}/api/soap/{required end point}``` For SOAP-API.

If you encounter any issues during the installation or deployment process, please refer to the Tomcat documentation or reach out for support.
```https://tomcat.apache.org/```



<a href="https://documenter.getpostman.com/view/18986316/2sA3BhctNE" target="_blank">REST</a>
<br/>
<a href="https://documenter.getpostman.com/view/18986316/2sA3BhctSd" target="_blank">SOAP</a>











