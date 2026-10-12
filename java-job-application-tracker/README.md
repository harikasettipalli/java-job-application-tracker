# CareerTrack — Java Job Application Tracker

A beginner-friendly CRUD web application built with Java 17, Spring Boot, Spring MVC, Spring Data JPA, MySQL, Thymeleaf, HTML/CSS, and Maven.

## Features
- Add, list, edit, and delete job applications
- Track company, role, date, status, job URL, and notes
- Server-side validation
- Responsive dashboard and form UI
- MySQL persistence

## Requirements
- JDK 17
- Eclipse IDE for Enterprise Java and Web Developers (or Eclipse with Spring Tools)
- MySQL Server 8.x and MySQL Workbench (optional)
- Internet connection for Maven to download dependencies

## 1. Create the database
Open MySQL Workbench, connect to your local server, and run:

```sql
CREATE DATABASE job_tracker;
```

The application uses the `root` account by default. Update `src/main/resources/application.properties` with your MySQL username and password, or set the `DB_PASSWORD` environment variable. Replace `YOUR_MYSQL_PASSWORD`; do not commit a real password to GitHub.

## 2. Import into Eclipse
1. Extract the ZIP to a folder you can find easily, such as `Documents/java-job-application-tracker`.
2. Open Eclipse and choose **File → Import**.
3. Select **Maven → Existing Maven Projects** and click **Next**.
4. Browse to the extracted project root (the folder containing `pom.xml`).
5. Ensure the project is selected, then click **Finish**.
6. Right-click the project → **Maven → Update Project…** → select the project → **OK**.
7. Check **Window → Preferences → Java → Installed JREs** and select a JDK 17 installation. Under **Project → Properties → Java Compiler**, use 17.

## 3. Configure MySQL password
Edit `src/main/resources/application.properties` and change:

```properties
spring.datasource.password=${DB_PASSWORD:YOUR_MYSQL_PASSWORD}
```

to your local MySQL password, for example:

```properties
spring.datasource.password=your_local_password
```

This is okay for local learning only. For GitHub, use an environment variable instead and never commit a real password. If you set `DB_PASSWORD`, the value in the environment variable takes priority.

## 4. Run
1. In Eclipse, open `src/main/java/com/example/jobtracker/JobTrackerApplication.java`.
2. Right-click inside the editor → **Run As → Java Application** (or **Spring Boot App** if Spring Tools is installed).
3. Wait for the console to show that Tomcat started on port 8080.
4. Open `http://localhost:8080/applications` in your browser.
5. Add a test application, then edit and delete it to verify the CRUD workflow.

The `spring.jpa.hibernate.ddl-auto=update` setting creates/updates the table during local development. The JDBC URL also requests database creation if it does not exist, but creating `job_tracker` manually is recommended.

## Project structure

```text
src/main/java/com/example/jobtracker/
  JobTrackerApplication.java
  controller/JobApplicationController.java
  model/JobApplication.java
  repository/JobApplicationRepository.java
  service/JobApplicationService.java
src/main/resources/
  application.properties
  templates/applications/list.html
  templates/applications/form.html
  static/css/style.css
```

## Troubleshooting
- **Access denied for user**: check username/password in `application.properties`.
- **Communications link failure / connection refused**: start the MySQL service and confirm port 3306.
- **Port 8080 already in use**: set `server.port=8081` and open `http://localhost:8081/applications`.
- **Java version errors**: confirm Eclipse and Maven use JDK 17.
- **Dependencies missing/red errors**: right-click project → Maven → Update Project; verify internet access.
- **Thymeleaf template error**: confirm template paths and names exactly match `templates/applications/list.html` and `templates/applications/form.html`.

## GitHub safety
Before pushing, ensure you have not added real passwords, API keys, or personal data. Commit the code and README, not secrets.

Screenshots

Dashboard

Add Application Form
