```markdown
# Doctor Office Management Web App

This is a Spring Boot application designed to manage the operations of a doctor's office. The application features separate interfaces for patients and doctors, utilizing a combination of modern web technologies and frameworks.

## Features

- **Patient Interface**: Allows patients to book appointments and view them displayed in a calendar.
- **Doctor Interface**: Enables doctors to manage appointments, access patient records, and provide consultation notes.
- **Authentication & Authorization**: Secure login for both patients and doctors.
- **Responsive Design**: Ensures usability on both desktop and mobile devices.

## Technologies Used

### Backend
- **Spring Boot**: Core framework for the application.
- **Thymeleaf**: Template engine for server-side rendering of web pages.
- **Hibernate JPA**: ORM tool for database interactions.
- **Oracle Database**: Robust and scalable database for storing application data.

### Frontend
- **HTML/CSS**: Structure and styling of web pages.
- **JavaScript**: Dynamic content and interactivity.
- **Frameworks**: Bootstrap for responsive design and jQuery for easier DOM manipulation.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- Java 17 or higher
- Maven
- Oracle Database
- Git

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/mariabdj/DoctorOffice.git
   cd DoctorOffice
   ```

2. **Configure the database:**
   Update the `application.properties` file with your Oracle database credentials.
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/yourDB
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

### Running the Frontend

The frontend is integrated within the Spring Boot application. Ensure you have the necessary static resources in the `src/main/resources/static` directory.

## Usage

- **Access the application:**
  - Patient Interface: `http://localhost:60500/patient`
  - Doctor Interface: `http://localhost:60500/doctor`

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Project Documentation : 
In the future, we will upload documentation of the back end.
You will learn:
1. The basics of Spring Boot (Controllers, Repositories, Services, and Entities).
2. Basics of Thymeleaf to achieve a dynamic web app.
3. How the whole app functions.
