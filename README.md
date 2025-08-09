# 📋 TaskTrack

A modern task management API built with Spring Boot, Frontend with React and Vite, featuring comprehensive task organization with progress tracking and smart data mapping.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=oracle)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue?style=flat-square&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat-square&logo=docker)

## ✨ Features

- **📝 Task Management**: Create, read, update, and delete tasks with ease
- **📊 Progress Tracking**: Automatic calculation of task list completion percentages
- **🗂️ Task Organization**: Group tasks into customizable task lists
- **🔄 Smart Data Mapping**: Clean separation between API and database layers
- **⚡ Real-time Metrics**: Task count and progress statistics
- **🛡️ Data Validation**: Robust error handling and data integrity
- **📱 RESTful API**: Clean, documented endpoints for frontend integration

## 🔮 Future Plans

- **🔐 User Registration & Login**: Secure signup and login functionality  ⏳ 
  - **🛡️ JWT-Based Authentication**: Token-based access control for API endpoints  ⏳ 
  - **🔄 Password Recovery**: Allow users to reset forgotten passwords securely  ⏳ 
  - **🔒 Enhanced Security**: Implement measures like account lockout and two-factor authentication  ⏳
- **⏰ Task Reminders**: Users can set reminders for important tasks  ⏳
- **📅 Task Filtering & Sorting**: Filter and sort tasks by due date, priority, or list  ⏳
- **📊 Statistics Dashboard**: Visualize task completion progress across all lists  ⏳
- **🔍 Global Task Search**: Search for tasks across all lists with ease  ⏳
- **🏷️ Tagging System**: Add tags to tasks for better organization and filtering  ⏳  
- **🤝 Sharing Options**: Share task lists or individual tasks with other users  ⏳
- **📆 Calendar Integration**: Sync due dates and reminders with calendar apps  ⏳
- **🍅 Pomodoro Timer**: Built-in timer to help users focus and manage work sessions  ⏳
- **🎮 Gamification**: Earn points and achievements to boost motivation and consistency  ⏳ 

## 🏗️ Architecture

Built following **MVC architecture** with clean separation of concerns:

```
📁 Controllers     → Handle HTTP requests/responses
📁 Services        → Business logic and validation  
📁 Repositories    → Data access layer
📁 Entities        → Database models
📁 DTOs           → Data transfer objects for API
📁 Mappers        → Convert between entities and DTOs
```

## 🚀 Tech Stack - Backend

| Technology | Purpose | Version |
|------------|---------|---------|
| **Java** | Core language | 17+ |
| **Spring Boot** | Application framework | 3.x |
| **Spring Data JPA** | Database abstraction | 3.x |
| **PostgreSQL** | Primary database | 15+ |
| **Docker** | Containerization | Latest |
| **Maven** | Dependency management | 3.8+ |

## 🚀 Tech Stack - Frontend

| Technology   | Purpose           | Version|
|--------------|-------------------|--------|
| TypeScript   | Core language     | 5.x|
| React        | UI library        | 18.x |
| Vite         | Build tool        | 4.x |
| Tailwind CSS | Styling           | 3.x |
| ESLint       | Linting           | 8.x |

## 🛠️ Installation & Setup

### Prerequisites
- Java 17 or higher
- Docker and Docker Compose
- Maven 3.8+

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/tasktrack.git
cd tasktrack
```

### 2. Start PostgreSQL with Docker
```bash
docker-compose up -d postgres
```

### 3. Run the application
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

### 4. Verify installation
```bash
curl http://localhost:8080/api/tasklists
```

## 📡 API Endpoints

### Task Lists
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/tasklists` | Get all task lists with progress |
| `POST` | `/api/tasklists` | Create new task list |
| `GET` | `/api/tasklists/{id}` | Get specific task list |
| `PUT` | `/api/tasklists/{id}` | Update task list |
| `DELETE` | `/api/tasklists/{id}` | Delete task list |

### Tasks
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/tasks` | Get all tasks |
| `POST` | `/api/tasks` | Create new task |
| `PUT` | `/api/tasks/{id}` | Update task |
| `DELETE` | `/api/tasks/{id}` | Delete task |

## 📋 Example Usage

### Create a Task List
```bash
curl -X POST http://localhost:8080/api/tasklists \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Weekend Projects",
    "description": "Things to accomplish this weekend"
  }'
```

### Response
```json
{
  "id": 1,
  "title": "Weekend Projects", 
  "description": "Things to accomplish this weekend",
  "taskCount": 0,
  "progress": 0.0,
  "tasks": []
}
```

### Add Tasks to the List
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Clean the garage",
    "description": "Organize tools and clean up",
    "completed": false,
    "taskListId": 1
  }'
```

### Get Task List with Progress
```bash
curl http://localhost:8080/api/tasklists/1
```

```json
{
  "id": 1,
  "title": "Weekend Projects",
  "description": "Things to accomplish this weekend", 
  "taskCount": 3,
  "progress": 0.67,
  "tasks": [
    {
      "id": 1,
      "title": "Clean the garage",
      "description": "Organize tools and clean up",
      "completed": true
    },
    {
      "id": 2, 
      "title": "Fix the fence",
      "description": "Replace broken boards",
      "completed": true
    },
    {
      "id": 3,
      "title": "Plant flowers",
      "description": "Add color to the garden",
      "completed": false
    }
  ]
}
```

## 🔍 Key Features Deep Dive

### Smart Progress Calculation
The API automatically calculates completion percentages:
```java
// Automatically computed based on task statuses
"progress": 0.67  // 67% of tasks completed
"taskCount": 3    // Total number of tasks
```

### Clean Data Mapping
Separation between database entities and API responses:
- **Entities**: Optimized for database storage
- **DTOs**: Optimized for API consumption with computed fields
- **Mappers**: Handle conversion with null safety and data enrichment

### Robust Error Handling
- Comprehensive validation for all inputs
- Graceful handling of edge cases (empty lists, null values)
- Meaningful error responses for better debugging

## 🐳 Docker Support

### Full application stack
```yaml
# docker-compose.yml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - postgres
  
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: tasktrack
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
```

### Run with Docker
```bash
docker-compose up
```

## 📁 Project Structure

```
src/main/java/com/javidanhaj/tasktrack/
├── 📁 controllers/          # REST endpoints
├── 📁 services/             # Business logic
├── 📁 repositories/         # Data access
├── 📁 domain/
│   ├── 📁 entities/         # Database models
│   └── 📁 dto/              # API models
├── 📁 mappers/              # Entity ↔ DTO conversion
└── 📁 config/               # Application configuration
```

