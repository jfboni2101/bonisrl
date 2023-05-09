package com.example.bonisrl;

import java.util.Objects;

public class TypeOfJob {

    String name;
    String description;

    public TypeOfJob(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TypeOfJob(String name) {
        this.name = name;
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TypeOfJob typeOfJob = (TypeOfJob) o;
        return Objects.equals(name, typeOfJob.name) && Objects.equals(description, typeOfJob.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "TypeOfJob{" + "name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
