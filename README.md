# Announcement Board - CRUD

## Description

A CRUD system for managing announcements, built with Spring Boot for the back-end and React (or JavaScript) for the front-end. It allows users to view a paginated list of announcements, view details, and mark them as viewed. This system is ideal for building notification or announcement management applications.

## Technologies Used

- **Back-end:** Spring Boot (version x.x.x)
- **Front-end:** React (or vanilla JavaScript) (version x.x.x)
- **Database:** H2 (or another of your choice)
- **ORM:** Hibernate

## Features

1. **Announcement Listing:**
   - Paginated, showing only the title.
   - When clicked, it displays all the information (title, description, publication date, and view date).
   - Marks the announcement as viewed.

2. **CRUD Operations:**
   - **Create:** Allows creating new announcements.
   - **Read:** Displays the list of announcements with pagination.
   - **Update:** Allows updating existing announcements.
   - **Delete:** Allows deleting announcements.

## API Endpoints

- **GET /api/notifications:** Returns the paginated list of announcements.
- **GET /api/notifications/{id}:** Returns detailed information of a specific announcement.
- **POST /api/notifications:** Creates a new announcement.
- **PUT /api/notifications/{id}:** Updates an existing announcement.
- **DELETE /api/notifications/{id}:** Deletes an announcement.

## Running Instructions

### Back-end (Spring Boot)

1. Clone the repository:
   ```bash
   git clone <repository URL>
   cd <back-end directory>

2. Build the project:
   ```bash
   ./mvnw clean install

3. Run the project:
   ```bash
   ./mvnw spring-boot:run

4. The API will be available at http://localhost:8080/notice

### Front-end (React)

1. Clone the repository:
   ```bash
   git clone <repository URL>
   cd <front-end directory>

2. Install dependencies:
   ```bash
   npm install

3. Run the project:
   ```bash
   npm start

4. The front-end will be available at http://localhost:3000