package com.yangweiye.springbootdemos.enumeration;

public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");
    private Integer id;
    private String name;

    SexEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getSexEnum(Integer id) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getId() == id) {
                return sexEnum;
            }
        }

        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
