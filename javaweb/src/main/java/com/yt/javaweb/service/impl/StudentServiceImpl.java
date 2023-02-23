package com.yt.javaweb.service.impl;

import com.yt.javaweb.dao.impl.StudentDAO;
import com.yt.javaweb.domin.Student;
import com.yt.javaweb.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {

    StudentDAO studentDAO = new StudentDAO();

    Scanner scanner = new Scanner(System.in);

    @Override
    public List<Student> getAllStudent() {
        List<Student> students = studentDAO.getAllStudents();
        return students;
    }

    @Override
    public List<Student> getStudentById(int id) {
        List<Student> student = studentDAO.getStudentById(id);

        return student;
    }

    @Override
    public void getStudentsByClass() {
        System.out.println("请输入要查询的班级");
        String s = scanner.nextLine();
        List<Student> students = studentDAO.getStudentsByClass(s);
        students.forEach(System.out ::println);
    }

    @Override
    public void getStudentsByLikeName() {
        System.out.println("请输入要模糊查询的学生姓名");
        String s = scanner.nextLine();
        List<Student> students = studentDAO.getStudentsByLikeName(s);
        students.forEach(System.out ::println);
    }

    @Override
    public void getStudentsOrderByClassAndGrade() {
        List<Student> students = studentDAO.getStudentsOrderByClassAndGrade();
        students.forEach(System.out ::println);
    }

    @Override
    public void getAvgOrderByAvg() {
        List<String> avgOrderByAvg = studentDAO.getAvgOrderByAvg();
        avgOrderByAvg.forEach(System.out ::println);
    }

    @Override
    public void getMaxAndMin() {
        List<String> maxAndMin = studentDAO.getMaxAndMin();
        maxAndMin.forEach(System.out ::println);
    }

    @Override
    public Boolean addStudent(Student student) {
        boolean result = studentDAO.addStudent(student);
        return result;
    }

    @Override
    public boolean updateStudent(Student student) {
       return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int id) {

        return studentDAO.deleteStudent(id);
    }
}
