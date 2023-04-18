package org.example;

public class Person{
    private int age = 10;
    private String name = "폴";
    private int getAge() { return age; }
    private int getAge(Integer no1) { return age + no1; }
    private int getAge(int no1, int no2) { return age + no1 + no2; }
    private int getAge(String no1) { return age + Integer.parseInt(no1); }
    private String getName() { return name; }
    private String getName(String subfix) { return name + subfix; }
    private Person getMe() { return this; }
}
