#  Inter Departmental Collaboration Hub

A Java-based application designed to improve coordination and collaboration between multiple departments within an institution by integrating Java, MongoDB, and Doubly Linked Lists for efficient data handling.

---

##  Aim
To build a platform that enables seamless interaction, project tracking, and data sharing between departments, supported by MongoDB for persistent storage and Doubly Linked Lists for fast in-memory operations.

---

##  Features
- Add, update, delete department details  
- Add, update, delete student details inside each department  
- Search departments and students  
- MongoDB database integration  
- Doubly Linked List for efficient traversal and manipulation  
- Clean console-based interface  
- Real-time synchronization between DLL and MongoDB  

---

##  Technologies Used
- **Java (JDK 8+)**
- **MongoDB / MongoDB Compass**
- **MongoDB Java Driver**
- **Doubly Linked Lists**
- **IntelliJ IDEA / Eclipse / VS Code**

---

##  System Requirements

### Hardware
- Processor: Intel Core i3 or higher  
- RAM: 4 GB (8 GB recommended)  
- Storage: 500 MB free  

### Software
- Windows / Linux / macOS  
- Java JDK 8 or higher  
- MongoDB 4.0+  
- IDE like IntelliJ / Eclipse  
- MongoDB Compass  

---

##  Project Development Steps

### 1️⃣ Understanding the Problem
- Identified issues with manual data handling between departments.
- Goal: Build an automated hub for collaboration and student management.

### 2️⃣ Planning the System
- Chose Java for console-based backend system.
- Used MongoDB for persistent storage.
- Used Doubly Linked Lists for runtime management.

### 3️⃣ Setting Up Environment
- Installed Java JDK  
- Installed MongoDB & Compass  
- Configured MongoDB Server (`mongodb://localhost:27017`)  
- Added MongoDB driver JAR files to Java project  

### 4️⃣ Designing Data Structures
- Created `DepartmentNode`, `StudentNode`  
- Created DLL classes for departments and students  
- Designed CRUD operations  

### 5️⃣ Implementing MongoDB Integration
- Created database: `collaborationHub`
- Created collections:  
  - `departments`  
  - `<deptName>_students`  
- Implemented insert, update, delete, find operations  

### 6️⃣ Writing Main Application
- Built menu-driven interface:
  - Add Department  
  - Display Departments  
  - Manage Students  
  - Delete Department  
  - Exit  
- Integrated StudentManagement module  

### 7️⃣ Testing
- Added sample departments and verified in MongoDB  
- Tested student operations  
- Verified DLL and DB consistency  

### 8️⃣ Output Generation
- Captured MongoDB screenshots  
- Captured console output  
- Added images to repository  

### 9️⃣ Finalizing Project
- Cleaned folder structure  
- Added README.md  
- Made GitHub repository public  

---

##  Installation Guide

### Step 1: Install Java
Download and install JDK 8 or higher.

### Step 2: Install MongoDB
Install MongoDB Server & Compass  
Start MongoDB service:
mongod

shell
Copy code

### Step 3: Clone the Repository
git clone <your-repository-link>

yaml
Copy code

### Step 4: Add MongoDB Java Driver
Place the JAR files inside your project’s library folder.

---

##  How to Run the Project

### Compile the program:
javac InterDepartmentalCollaborationHub.java

shell
Copy code

### Run the program:
java InterDepartmentalCollaborationHub

yaml
Copy code

---

##  Output

### ✔ Console Output
=== Inter Departmental Collaboration Hub ===

Add Department

Display Departments

Manage Department Students

Delete Department

Exit
Enter your choice:
1
Enter Department ID: 101
Enter Department Name: CSE
Department added successfully!

shell
Copy code

### ✔ MongoDB Output
{
"deptId": "101",
"deptName": "cse"
}

yaml
Copy code

---

##  Folder Structure
/PROJECT
│── src/
│ ├── InterDepartmentalCollaborationHub.java
│ ├── StudentManagement.java
│
│── output/
│ ├── console_output.png
│ ├── mongodb_output.png
│
│── README.md

yaml
Copy code

---

##  Submit GitHub Link
- This repository contains the complete source code for the project.  
- It is set to **public** for reviewer access.  
- The repository includes a clean structure and a detailed README explaining setup, requirements, and how to run the project.  

---

##  Future Enhancements
- Add user authentication  
- Develop a full GUI (JavaFX / Swing)  
- Add analytics dashboard  
- Add collaboration reports  
- Export data to PDF / Excel  

---

## 👤 Author
**Neerugatti Karthik**  
Java Developer | HCL GUVI Program
