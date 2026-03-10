# InstaProo - Full Stack Social Media App
A full-stack social media web app built with React.js and Spring Boot. Features JWT authentication, role-based access control for Admin and User, post approval system, and REST APIs with MySQL database.

## 🚀 Tech Stack.....
**Frontend:**
- React.js (Vite)
- Tailwind CSS
- React Router DOM

**Backend:**
- Java 17
- Spring Boot
- Spring Security (JWT)
- Hibernate / JPA
- MySQL

 ## ✨ Features

- 🔐 JWT Authentication (Login / Signup)
- 👤 Role-based access — Admin & User
- 📝 User can create posts
- ⏳ Posts go to Admin for approval first
- ✅ Admin can Approve / Reject posts
- 👀 Approved posts visible to other users
- 🛡️ Protected routes — Admin & User dashboards separate

## 📁 Project Structure
# InstaProo - Full Stack Social Media App

A full-stack social media web app built with React.js and Spring Boot. Features JWT authentication, role-based access control for Admin and User, post approval system, and REST APIs with MySQL database.

---

## 🚀 Tech Stack

**Frontend:**
- React.js (Vite)
- Tailwind CSS
- React Router DOM

**Backend:**
- Java 17
- Spring Boot
- Spring Security (JWT)
- Hibernate / JPA
- MySQL

---

## ✨ Features

- 🔐 JWT Authentication (Login / Signup)
- 👤 Role-based access — Admin & User
- 📝 User can create posts
- ⏳ Posts go to Admin for approval first
- ✅ Admin can Approve / Reject posts
- 👀 Approved posts visible to other users
- 🛡️ Protected routes — Admin & User dashboards separate

---

## 📁 Project Structure
```
src/
├── api/          → authApi.js, postApi.js
├── components/   → AdminNavbar.jsx
├── pages/        → Login, Signup, UserFeed, AdminDashboard, AdminPending
├── routes/       → PrivateRoute, AdminRoute, AppRoutes
└── utils/        → auth.js
```

---

## ⚙️ Backend Setup
```
Port: 8080
Database: MySQL (instapro_db)
```

**Default Admin Credentials:**
```
Username: admin
Password: admin@123
```
Admin is auto-created when Spring Boot starts.

---

## 🖥️ Frontend Setup
```bash
cd insta-project
npm install
npm run dev
```
Runs on: `http://localhost:5173`

---

## 🗄️ Database Setup
```sql
CREATE DATABASE instapro_db;
USE instapro_db;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150),
    mobile VARCHAR(20),
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);
```

---

## 👨‍💻 Author

**Anuj Maurya**  
B.Tech AIML | Java Full Stack Developer  
[GitHub](https://github.com/Anujmaurya6)
