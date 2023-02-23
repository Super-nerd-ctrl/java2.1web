package com.yt.javaweb.service;

import com.yt.javaweb.domin.Student;

import java.util.List;

public interface StudentService {

    /**
     * 查询所有学生信息
     */
    public List<Student> getAllStudent();

    /**
     * 根据学号查询学生信息
     *
     */
    public List<Student> getStudentById(int id);

    /**
     * 根据班级查询学生信息
     */
    public void getStudentsByClass();

    /**
     * 根据名字模糊查询学生信息
     */
    public void getStudentsByLikeName();

    /**
     * 按班级低到高，成绩高到低排序
     */
    public void getStudentsOrderByClassAndGrade();

    /**
     * 统计每个班的平均分，高到低排序
     */
    public void getAvgOrderByAvg();

    /**
     * 统计每个班的最高分，最低分
     */
    public void getMaxAndMin();

    /**
     * 添加学生信息
     */
    public Boolean addStudent(Student student);

    /**
     * 修改学生信息
     */
    public boolean updateStudent(Student student);

    /**
     * 删除学生信息
     */
    public boolean deleteStudent(int id);
}
