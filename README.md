
# AI Crypto Advisor-Full Stack Project

The **AI Crypto Advisor** is a full-stack application that provides users with personalized cryptocurrency insights,
real-time market data, and AI-generated summaries based on user preferences and investor types.

This README covers both the **Frontend (React)** and **Backend (Spring Boot)** components, how to run them locally,
and how to connect to the deployed database.

## Architecture Overview
```
Frontend (React @ Vercel)
       ↓
Backend API (Spring Boot @ Render)
       ↓
PostgreSQL Database (@ Render)
```

The system follows a clean client-server architecture.
The backend exposes REST APIs secured with JWT tokens and aggregates data from multiple crypto sources.
The frontend consumes these APIs, providing users with a personalized dashboard experience.
All services are deployed in production environments and communicate securely via HTTPS.

## Backend – Spring Boot API
## Overview
The backend is a RESTful API built with **Spring Boot**, handling:
- User authentication via JWT
- Preference management
- Dashboard aggregation (AI insights, market data, news, memes)
- Integration with external APIs (CoinGecko, CryptoPanic, OpenRouter)
- PostgreSQL persistence (via JPA/Hibernate)

## Tech Stack
| Layer | Technology |
|--------|-------------|
| Language | Java 17 |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| Database | PostgreSQL (Render) |
| ORM | Hibernate (JPA) |
| API Clients | CoinGecko, CryptoPanic, OpenRouter |
| Deployment | Render |
| Build Tool | Maven |

## Configuration
**Environment Variables**
These are set securely on Render or locally in application.properties.

| Variable | Description |
|--------|-------------|
| DATABASE_URL | PostgreSQL connection URL |
| APP_JWT_SECRET | Secret for JWT token generation |
| COINGECKO_API_KEY | CoinGecko API key |
| CRYPTOPANIC_API_KEY | CryptoPanic API key |
| OPENROUTER_API_KEY | OpenRouter API key |

### Example `application.properties`

```properties
spring.application.name=AI_Crypto_Advisor
server.port=8080

# --- Database ---
spring.datasource.url=jdbc:postgresql://localhost:5432/crypto_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# --- JWT ---
app.jwt.secret=your_local_secret_here

# --- External APIs ---
coingecko.api.key=your_coingecko_key
cryptopanic.api.key=your_cryptopanic_key
openrouter.api.key=your_openrouter_key
```

> In production, all these values are defined securely as **environment variables** on Render.

---
## Database Access

The project uses a PostgreSQL database hosted on Render.
Connection credentials can be shared securely upon request.
(All environment variables are configured directly in Render for security reasons.)

**Recommended Tools:** DBeaver, pgAdmin, TablePlus

## Running Backend Locally

### Prerequisites
- Java 17+
- Maven
- PostgreSQL

### Commands
```bash
git clone https://github.com/orikeni/ai-crypto-advisor-backend.git
cd ai-crypto-advisor-backend
mvn clean package -DskipTests
mvn spring-boot:run
```

Backend runs on: [http://localhost:8080](http://localhost:8080)
---
## API Endpoints
| Endpoint | Method | Description |
|-----------|---------|-------------|
| `/api/auth/registration` | POST | Register new user |
| `/api/auth/login` | POST | User login |
| `/api/pref/{id}` | GET/PUT | Manage preferences |
| `/api/dashboard` | GET | Personalized dashboard |
| `/api/vote` | POST | Like/dislike content |

#  Frontend – React + TypeScript

## Overview
The frontend is a modern React application providing a dynamic crypto dashboard with AI insights and user personalization.

---
## Tech Stack
| Layer          | Technology    |
| :------------- | :------------- |
| **Language** | TypeScript   |
| **Framework** | React 18       |
| **State Mngmt**| Redux Toolkit     |
| **Styling** | TailwindCSS       |
| **Routing** | React Router v6     |
| **HTTP Client**| Axios    |
| **Build Tool** | Vite    |
| **Deployment** | Vercel |

-----
## Running Frontend Locally

### Prerequisites
- Node.js 18+
- npm / yarn
- Backend running (local or Render)

### Commands
```bash
git clone https://github.com/orikeni/ai-crypto-advisor-frontend.git
cd ai-crypto-advisor-frontend
npm install
npm run dev
```

Frontend runs on [http://localhost:5173](http://localhost:5173)

## Features
- Secure JWT auth (register/login)
- User preference wizard
- Personalized dashboard (AI, prices, news, memes)
- Responsive UI
- CORS + HTTPS support

---

## Author
**Ori Kenigsberg**  
GitHub: [https://github.com/orikeni](https://github.com/orikeni)

---

## Notes
- API keys are stored only in **Render environment variables**.
- Database credentials are private.
- Since the backend is hosted on Render (free tier), the server may spin down when inactive.
  As a result, the first request after a period of inactivity may take up to 1–2 minutes to load while the service restarts. Subsequent requests will respond normally.

