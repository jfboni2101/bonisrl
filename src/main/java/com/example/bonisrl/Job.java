package com.example.bonisrl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Job {
    String nameType;
    String firstNameClient;
    String lastNameClient;
    String firstNameEmployee;
    String lastNameEmployee;
    LocalDate dateOfJob;
    LocalTime hours;
    float size;
    String address;

    public Job(String nameType, String firstNameClient, String lastNameClient, String firstNameEmployee, String lastNameEmployee, LocalDate dateOfJob, LocalTime hours, float size, String address) {
        this.nameType = nameType;
        this.firstNameClient = firstNameClient;
        this.lastNameClient = lastNameClient;
        this.firstNameEmployee = firstNameEmployee;
        this.lastNameEmployee = lastNameEmployee;
        this.dateOfJob = dateOfJob;
        this.hours = hours;
        this.size = size;
        this.address = address;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getFirstNameEmployee() {
        return firstNameEmployee;
    }

    public void setFirstNameEmployee(String firstNameEmployee) {
        this.firstNameEmployee = firstNameEmployee;
    }

    public String getLastNameEmployee() {
        return lastNameEmployee;
    }

    public void setLastNameEmployee(String lastNameEmployee) {
        this.lastNameEmployee = lastNameEmployee;
    }

    public LocalDate getDateOfJob() {
        return dateOfJob;
    }

    public void setDateOfJob(LocalDate dateOfJob) {
        this.dateOfJob = dateOfJob;
    }

    public LocalTime getHours() {
        return hours;
    }

    public void setHours(LocalTime hours) {
        this.hours = hours;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Job job = (Job) o;
        return Float.compare(job.size, size) == 0 && Objects.equals(nameType, job.nameType) && Objects.equals(firstNameClient, job.firstNameClient) && Objects.equals(lastNameClient, job.lastNameClient) && Objects.equals(firstNameEmployee, job.firstNameEmployee) && Objects.equals(lastNameEmployee, job.lastNameEmployee) && Objects.equals(dateOfJob, job.dateOfJob) && Objects.equals(hours, job.hours) && Objects.equals(address, job.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameType, firstNameClient, lastNameClient, firstNameEmployee, lastNameEmployee, dateOfJob, hours, size, address);
    }

    @Override
    public String toString() {
        return "Job{" + "nameType='" + nameType + '\'' + ", firstNameClient='" + firstNameClient + '\'' + ", lastNameClient='" + lastNameClient + '\'' + ", firstNameEmployee='" + firstNameEmployee + '\'' + ", lastNameEmployee='" + lastNameEmployee + '\'' + ", dateOfJob=" + dateOfJob + ", hours=" + hours + ", size=" + size + ", address='" + address + '\'' + '}';
    }
}
