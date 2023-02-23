package com.yt.javaweb.domin;
import java.io.Serializable;


/**
* `student`
* @TableName student
*/
public class Student implements Serializable {

    public Student() {
    }

    public Student(Long id, String name, Integer sex, Integer age, String classname, Integer score, String grade) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.classname = classname;
        this.score = score;
        this.grade = grade;
    }

    /**
    * 主键
    */
    private Long id;
    /**
    * 用户名
    */

    private String name;
    /**
    * 性别
    */

    private Integer sex;
    /**
    * 年龄
    */

    private Integer age;
    /**
    * 班级名
    */

    private String classname;
    /**
    * 成绩
    */

    private Integer score;
    /**
    * 成绩等级
    */
    private String grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return  "id:" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", classname='" + classname + '\'' +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                '}';
    }
}
