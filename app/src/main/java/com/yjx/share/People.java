package com.yjx.share;/**
 * Created by yjx on 15/5/23.
 */

import java.io.Serializable;

/**
 * User: YJX
 * Date: 2015-05-23
 * Time: 23:12
 */
public class People implements Serializable {
    private int age;
    private String name;

    public People(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
