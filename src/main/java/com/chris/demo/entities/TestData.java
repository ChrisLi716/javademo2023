package com.chris.demo.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lilun
 * @Date 2020-11-02 17:24
 * @Description
 **/
public class TestData {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1, "Chris", 23, 23000.43));
        list.add(new Employee(2, "John", 48, 13000.85));
        list.add(new Employee(3, "Joffery", 48, 32000.52));
        list.add(new Employee(4, "Allan", 34, 15800.83));
        list.add(new Employee(5, "Hedy", 22, 25200.45));
        list.add(new Employee(6, "Ethan", 2, 22500.84));
        list.add(new Employee(7, "Chris", 13, 13000.43));

        return list;
    }


    public static List<Student> getStudents() {
        List<Student> studentScoreList = new ArrayList<>();
        Student studentScore1 = new Student() {{
            setName("张三");
            setSubject("语文");
            setScore(70);
        }};
        Student studentScore2 = new Student() {{
            setName("张三");
            setSubject("数学");
            setScore(80);
        }};
        Student studentScore3 = new Student() {{
            setName("张三");
            setSubject("英语");
            setScore(65);
        }};
        Student studentScore4 = new Student() {{
            setName("李四");
            setSubject("语文");
            setScore(68);
        }};
        Student studentScore5 = new Student() {{
            setName("李四");
            setSubject("数学");
            setScore(70);
        }};
        Student studentScore6 = new Student() {{
            setName("李四");
            setSubject("英语");
            setScore(90);
        }};
        Student studentScore7 = new Student() {{
            setName("王五");
            setSubject("语文");
            setScore(80);
        }};
        Student studentScore8 = new Student() {{
            setName("王五");
            setSubject("数学");
            setScore(85);
        }};
        Student studentScore9 = new Student() {{
            setName("王五");
            setSubject("英语");
            setScore(70);
        }};

        studentScoreList.add(studentScore1);
        studentScoreList.add(studentScore2);
        studentScoreList.add(studentScore3);
        studentScoreList.add(studentScore4);
        studentScoreList.add(studentScore5);
        studentScoreList.add(studentScore6);
        studentScoreList.add(studentScore7);
        studentScoreList.add(studentScore8);
        studentScoreList.add(studentScore9);

        return studentScoreList;
    }
}
