package br.com.schoolapp.controller.dto;

public class ClassroomDto {

    private long id;
    private String name;
    private int size;
    private int year;

    public ClassroomDto(String name, int size, int year) {
        this.name = name;
        this.size = size;
        this.year = year;
    }

    public ClassroomDto() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getYear() {
        return year;
    }

    public void setId(long id) {
        this.id = id;
    }
}
