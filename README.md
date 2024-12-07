# College Data Record Management

## Overview
This project is a Java-based **Student Record Management System** that allows users to add, update, view, and delete student data. The system uses **Swing** for the graphical user interface (GUI) and **JDBC** to interact with a **MySQL** database.

## Features
- Add student data (Name, Email, Course)
- Simple and user-friendly GUI built with Java Swing
- MySQL database for storing student records

## Prerequisites
- Java 8 or above
- MySQL Database
  - Create a database called `college`
  - Create a table `students` with the following fields: `id (INT)`, `name (VARCHAR)`, `email (VARCHAR)`, `course (VARCHAR)`

## How to Run
1. Download or clone the project.
2. Set up a MySQL database with the following schema:
   ```sql
   CREATE DATABASE college;
   USE college;
   CREATE TABLE students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255),
       email VARCHAR(255),
       course VARCHAR(255)
   );
