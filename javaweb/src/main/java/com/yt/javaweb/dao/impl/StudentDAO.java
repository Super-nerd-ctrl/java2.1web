package com.yt.javaweb.dao.impl;

import com.yt.javaweb.domin.Student;
import com.yt.javaweb.utils.MyC3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {


    /**
     * 查询全部学生信息
     *
     * @return 学生对象集合
     */
    public List<Student> getAllStudents() {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "select * from student";
        ArrayList<Student> students = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //返回所有表名的集合
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //获取表名
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int sex = resultSet.getInt("sex");
                int age = resultSet.getInt("age");
                String classname = resultSet.getString("classname");
                int score = resultSet.getInt("score");
                String grade = resultSet.getString("grade");

                Student student = new Student(id, name, sex, age, classname, score, grade);

                students.add(student);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return students;
    }

    /**
     * 根据学号查询学生信息
     *
     * @param id 学生学号
     * @return 学生对象
     */
    public List<Student> getStudentById(int id) {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "select * from student where id = ?";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            students = getList(resultSet);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    /**
     * 根据班级查询学生信息
     *
     * @param classname 班级
     * @return 学生信息集合
     */
    public List<Student> getStudentsByClass(String classname) {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "select * from student where classname = ?";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classname);
            ResultSet resultSet = preparedStatement.executeQuery();
            students = getList(resultSet);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    /**
     * 根据模糊姓名查询学生信息
     *
     * @param name
     * @return
     */
    public List<Student> getStudentsByLikeName(String name) {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "select * from student where name like ?";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            students = getList(resultSet);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    /**
     * 将查询到的结果集存入自己创建的集合中
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public List<Student> getList(ResultSet resultSet) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            //获取表名
            long id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int sex = resultSet.getInt("sex");
            int age = resultSet.getInt("age");
            String classname = resultSet.getString("classname");
            int score = resultSet.getInt("score");
            String grade = resultSet.getString("grade");

            Student student = new Student(id, name, sex, age, classname, score, grade);

            students.add(student);

        }

        return students;
    }

    /**
     * 将查询结果按班级低到高，成绩高到低排序
     *
     * @return
     */
    public List<Student> getStudentsOrderByClassAndGrade() {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "select * from student order by classname, score desc";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            students = getList(resultSet);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    /**
     * 统计每个班的平均分，高到低排序
     *
     * @return
     */
    public List<String> getAvgOrderByAvg() {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "select classname, avg(score) as score_avg from student group by classname order by score_avg desc ";
        List<String> avg = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String classname = resultSet.getString("classname");
                Integer score_avg = resultSet.getInt("score_avg");
                String score = Integer.toString(score_avg);
                avg.add(classname + "," + score);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return avg;
    }

    /**
     * 统计每个班的最高分，最低分
     *
     * @return
     */
    public List<String> getMaxAndMin() {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "select classname, max(score) as max_score, min(score) as min_score from student group by classname ";
        List<String> maxAndMin = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String classname = resultSet.getString("classname");
                Integer max_score = resultSet.getInt("max_score");
                String max = Integer.toString(max_score);
                Integer min_score = resultSet.getInt("min_score");
                String min = Integer.toString(min_score);
                maxAndMin.add(classname + "，最高分：" + max + " ，最低分：" + min);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return maxAndMin;
    }

    /**
     * 添加学生
     */
    public boolean addStudent(Student student) {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "insert into student values (null, ?, ?, ?, ?, ?, ?)";
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getSex());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getClassname());
            preparedStatement.setInt(5, student.getScore());
            preparedStatement.setString(6, student.getGrade());

            result = preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 修改学生信息
     *
     * @return
     */
    public boolean updateStudent(Student student) {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "update student set name = ?, age = ?, sex = ?, classname = ?, score = ?, grade = ? where id = ?";
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getSex());
            preparedStatement.setString(4, student.getClassname());
            preparedStatement.setInt(5, student.getScore());
            preparedStatement.setString(6, student.getGrade());
            preparedStatement.setLong(7, student.getId());

            result = preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 删除学生信息
     *
     * @param id
     * @return
     */
    public boolean deleteStudent(long id) {
        Connection connection = MyC3P0Util.getConnection();
        String sql = "delete from student where id = ?";
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            result = preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
