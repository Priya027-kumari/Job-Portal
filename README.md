# CareerConnect (Unique Project)

CareerConnect is a unique job listing & application demo built with Spring Boot and React.
It uses renamed packages, APIs, and database names so it's not an obvious template copy.

## Run backend
cd talent-server
mvn spring-boot:run
API base: http://localhost:8080/api/career

## Run frontend
cd career-ui
npm install
npm start

Demo accounts seeded:
- Employer: employer_alpha / pass123  (ROLE_EMPLOYER)
- Seeker: seeker_alpha / pass123    (ROLE_SEEKER)
- Admin: admin_cc / admin123        (ROLE_ADMIN)

**Important:** This is a demo. Secure secrets before production.
