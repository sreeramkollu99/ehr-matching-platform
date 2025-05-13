# EHR Matching Platform

An AI-powered backend platform built using **Spring Boot** and **MongoDB/PostgreSQL** for matching patient electronic health records (EHRs) with clinical trials based on customizable criteria.

---

## 🚀 Features

- Secure **JWT-based authentication**
- Role-based access: Admin, Clinician
- Register/Login users linked to hospitals
- Add and fetch clinical trials
- Upload and store patient EHRs (MongoDB)
- Match EHRs to trials using a basic rule engine (extendable with AI)
- Company and hospital management

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3, Java 20
- **Database**: PostgreSQL (relational), MongoDB (EHRs)
- **Security**: Spring Security with JWT
- **Testing**: JUnit, MockMvc
- **Build Tool**: Maven

---

## ⚙️ Setup

### Prerequisites

- Java 20+
- PostgreSQL running at `localhost:5432/ehrmatch`
- MongoDB running at `localhost:27017/ehrdb`
- Maven

### Clone & Run

```bash
git clone https://github.com/sreeramkollu99/ehr-matching-platform.git
cd ehr-matching-platform
mvn spring-boot:run
🔐 Authentication
Register
POST /auth/register

json
Copy
Edit
{
  "name": "Dr. Smith",
  "email": "drsmith@example.com",
  "password": "secure123",
  "role": "CLINICIAN",
  "hospitalId": "UUID"
}
Login
POST /auth/login

json
Copy
Edit
{
  "email": "drsmith@example.com",
  "password": "secure123"
}
Returns a JWT token to be used as:

makefile
Copy
Edit
Authorization: Bearer <token>
📌 Endpoints Overview
Endpoint	Method	Description
/auth/**	POST	Register/Login
/api/companies	GET	List all companies
/api/hospitals	GET	List hospitals
/api/ehrs	POST	Upload patient EHR
/api/trials	POST	Add new trial
/api/match/{ehrId}	POST	Run matching for EHR
/api/match	GET	Get match results for clinician

✅ Test Coverage
Includes controller test cases for:

AuthController

CompanyController

MatchController

TrialController

PatientEhrController

🔮 Upcoming (AI Integration)
LLM/NLP-powered matcher (via FastAPI)

Smart trial suggestions

Patient-centric dashboard

👨‍💻 Author
Sreeram Kollu

Feel free to contribute, fork, or raise issues.

📄 License
This project is open source and available under the MIT License.

vbnet
Copy
Edit

Let me know if you want it tailored for monorepo structure or need a separate section for t