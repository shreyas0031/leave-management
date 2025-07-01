# 📝 Leave Management System

A full-stack web application that allows employees to apply for leaves and admins to manage leave requests efficiently.

## 🛠️ Tech Stack
- **Frontend**: React, TypeScript, Axios, SweetAlert2
- **Backend**: Spring Boot, Hibernate, REST API
- **Database**: MySQL

## 👥 User Roles
- **USER**: Apply for leave, view leave history, and department details.
- **ADMIN**: View all leave requests, approve/reject, filter by status/department, and manage departments.

## ✅ Features
- Role-based login and dashboard
- Leave application & history
- Admin leave approval system
- Department management (Admin only)
- Persistent login via localStorage

## 🚀 Run Locally

### Backend (Spring Boot)
1. Import into your IDE.
2. Configure `application.properties` for MySQL.
3. Run the `LeaveManagementApplication.java`.

### Frontend (React)
```bash
cd frontend
npm install
npm start
