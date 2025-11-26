package com.example.todo.model;

public class Todo {
    private int id;
    private String task;

    public Todo() {}

    public Todo(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }
}
