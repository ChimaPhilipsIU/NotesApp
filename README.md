# NotesApp

A full-stack personal productivity web application built with Spring Boot. Allows authenticated users to create, manage, search, sort, and share notes and reminders through a dark-themed editorial interface.

---

## Features

**Notes**
- Create, edit, and delete notes
- Search notes by title using a live hybrid search (frontend calls Spring backend as you type)
- Sort by latest, oldest, or A-Z
- Assign notes to folders via drag and drop
- Share notes via Gmail compose with full note content pre-filled
- Notes are grouped into collapsible folders that persist across sessions via localStorage

**Reminders**
- Create, edit, and delete reminders with optional due dates
- Reminders past their due date are automatically filtered out on login
- A startup popup alerts the user to any active upcoming reminders
- Same search, sort, folder, and sharing features as notes
- Sort by due date in addition to the standard options

**Folders**
- Create named folders for both notes and reminders
- Drag notes and reminders into folders
- Drag items out of folders back to the main grid
- Empty folders persist across page loads via localStorage
- Deleting a folder moves its contents back to the main grid

**General**
- Secure login with Spring Security and BCrypt password hashing
- CSRF protection on all POST requests including fetch calls
- Live clock and date display in the header
- Dark editorial interface with Cormorant Garamond and EB Garamond typography
- Fully containerised with Docker

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot 4, Spring MVC, Spring Security |
| Persistence | Spring Data JDBC, H2 embedded database |
| Frontend | Thymeleaf, HTML, CSS, Vanilla JavaScript |
| Build | Gradle |
| Container | Docker |

---

## Setup and Running

### Prerequisites

- Java 25
- Gradle (or use the included `gradlew` wrapper)
- Docker (optional, for containerised deployment)

### Running Locally

**1. Clone the repository**
```bash
git clone <your-repo-url>
cd notes-app
```

**2. Build the project**
```bash
./gradlew build
```

**3. Run the application**
```bash
./gradlew bootRun
```

**4. Open in your browser**
```
http://localhost:8081
```

**Default credentials**
```
Username: Chima
Password: Password1@
```

---

## Running with Docker

**1. Build the jar**
```bash
./gradlew build
```

**2. Build the Docker image**
```bash
docker build -t notes-app .
```

**3. Run the container**
```bash
docker run -p 8081:8081 notes-app
```

**4. Open in your browser**
```
http://localhost:8081
```

> **Note:** The app uses an H2 file database stored at `notesdb.mv.db` in the project root. When running inside Docker, this file lives inside the container and will not persist after the container stops. For persistent data across container restarts, mount a volume or migrate to an external database.

---

## Project Structure

```
src/
├── main/
│   ├── java/com/example/notes_app/
│   │   ├── controller/       # NoteController, ReminderController, SecurityConfig
│   │   ├── model/            # Note, Reminder (domain models)
│   │   ├── repository/       # NoteRepository, ReminderRepository (Spring Data)
│   │   └── service/          # NoteService, ReminderService (business logic)
│   └── resources/
│       ├── templates/        # Thymeleaf HTML templates
│       └── schema.sql        # Database schema
└── test/
    └── java/com/example/notes_app/
        └── service/          # NoteServiceTest, ReminderServiceTest (JUnit + Mockito)
```

---

## Running Tests

```bash
./gradlew test
```

Test reports are generated at `build/reports/tests/test/index.html`.
