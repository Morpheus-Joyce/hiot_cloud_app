package com.huatec.hiot_cloud.test.networktest;

import java.io.Serializable;

/**
 * 学生实体类
 */
class Student implements Serializable {

    /**
     * 姓名
     */
    private String name;

    private int id;

    private int height;

    private boolean graduted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGraduted() {
        return graduted;
    }

    public void setGraduted(boolean graduted) {
        this.graduted = graduted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
