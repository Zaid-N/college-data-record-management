package com.college;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentForm extends JFrame {
    private JTextField nameField, ageField, courseField;
    private JButton addButton, viewButton;

    public StudentForm() {
        setTitle("Student Management System");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Course:"));
        courseField = new JTextField();
        add(courseField);

        addButton = new JButton("Add Student");
        add(addButton);

        viewButton = new JButton("View Students");
        add(viewButton);

        addButton.addActionListener(e -> addStudent());
        viewButton.addActionListener(e -> viewStudents());

        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String course = courseField.getText();

        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, course);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully!");

            // Clear fields
            nameField.setText("");
            ageField.setText("");
            courseField.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student.");
        }
    }

    private void viewStudents() {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM students";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            StringBuilder studentList = new StringBuilder();
            while (rs.next()) {
                studentList.append("ID: ").append(rs.getInt("id"))
                           .append(", Name: ").append(rs.getString("name"))
                           .append(", Age: ").append(rs.getInt("age"))
                           .append(", Course: ").append(rs.getString("course"))
                           .append("\n");
            }

            JOptionPane.showMessageDialog(this, studentList.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching students.");
        }
    }
}
