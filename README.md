# QuizApp Application

## Technology Used
The application leverages the following technologies:

- **Java**: Programming Language
- **Spring Boot**: Framework for building the application
- **H2 Database**: In-memory database for simplicity during development and testing
- **JPA (Hibernate)**: ORM for database operations
- **Postman**: Tool for API testing

---

## Setup Instructions
Follow these steps to set up and run the application:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repository/quiz-app.git
   cd quiz-app
Build and Run the Application:
The application will run on http://localhost:8080.
Use Postman or any API testing tool to interact with the endpoints.

H2 Database Console:
Access the H2 database console at http://localhost:8080/h2-console.
Default credentials:

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave blank)
API Endpoints
Below is the list of available endpoints:

⭐ 1. Start a New Quiz Session
URL: /api/quiz/start
Method: POST
Params: userId (Long)
Description: Starts a new quiz session for the given user.
⭐ 2. Get a Random Question
URL: /api/quiz/question
Method: GET
Description: Fetches a random multiple-choice question from the database.
⭐ 3. Submit an Answer
URL: /api/quiz/submit
Method: POST
Params:
userId (Long)
questionId (Long)
answer (String)
Description: Submits an answer for the given question and updates the user's session.
⭐ 4. Get Session Summary
URL: /api/quiz/summary
Method: GET
Params: userId (Long)
Description: Retrieves the total questions answered and the count of correct/incorrect answers for the user.
