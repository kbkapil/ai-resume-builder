# AI Resume Builder

A Spring Boot web application that generates professional resumes using AI-powered templates. Users can input their information, select from various resume templates, and download their resume as a PDF.

## Features

- **AI-Powered Resume Generation**: Create professional resumes with intelligent formatting
- **Multiple Templates**: Choose from various professionally designed resume templates
- **PDF Export**: Download your resume as a high-quality PDF
- **Web Interface**: User-friendly web interface for easy resume creation
- **Responsive Design**: Works on desktop and mobile devices

## Technology Stack

- **Backend**: Spring Boot 3.x with Java 17
- **Frontend**: Thymeleaf templates with HTML/CSS/JavaScript
- **Build Tool**: Maven
- **PDF Generation**: iText library
- **Database**: H2 (in-memory) for development

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd ai-resume-builder
```

### 2. Build the Application

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Access the Application

Open your browser and navigate to `http://localhost:8080`

## Usage

1. **Home Page**: Start by visiting the home page
2. **Resume Form**: Fill in your personal information, work experience, education, and skills
3. **Template Selection**: Choose from available resume templates
4. **Preview**: Review your resume before downloading
5. **Download**: Export your resume as a PDF

## Project Structure

```
ai-resume-builder/
├── src/
│   ├── main/
│   │   ├── java/com/airesumebuilder/
│   │   │   ├── AiResumeBuilderApplication.java    # Main application class
│   │   │   ├── ResumeController.java             # REST controller
│   │   │   ├── ResumeService.java                # Business logic
│   │   │   └── Resume.java                       # Resume model
│   │   └── resources/
│   │       ├── application.properties            # Configuration
│   │       └── templates/                        # HTML templates
│   │           ├── index.html                    # Home page
│   │           ├── resume-form.html              # Resume input form
│   │           ├── template-selection.html       # Template chooser
│   │           ├── resume-display.html           # Resume preview
│   │           └── pdf-download.html             # PDF download page
├── pom.xml                                       # Maven configuration
├── README.md                                     # This file
└── TODO.md                                       # Development tasks
```

## API Endpoints

- `GET /` - Home page
- `GET /resume/form` - Resume creation form
- `POST /resume/generate` - Generate resume
- `GET /resume/templates` - Template selection
- `GET /resume/preview/{id}` - Preview resume
- `GET /resume/download/{id}` - Download PDF

## Configuration

The application uses the following configuration in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration (H2 for development)
spring.datasource.url=jdbc:h2:mem:resumedb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (for development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Thymeleaf Configuration
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```

## Development

### Adding New Templates

1. Create a new HTML template in `src/main/resources/templates/`
2. Add template metadata to the `ResumeService.java`
3. Update the template selection page

### Customizing Styles

- CSS files are embedded in the HTML templates
- Modify the `<style>` sections in the template files
- Or create separate CSS files and link them in the templates

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support, please contact [kapilbisen7231@gmail.com](mailto:kapilbisen7231@gmail.com)

## Acknowledgments

- Built with Spring Boot
- PDF generation powered by iText
- Icons and design inspiration from various sources
