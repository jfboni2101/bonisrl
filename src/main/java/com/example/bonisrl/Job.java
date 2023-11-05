package com.example.bonisrl;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Job {
    StringProperty nameType;
    ObjectProperty<Integer> idClient;
    StringProperty firstNameClient;
    StringProperty lastNameClient;
    ObjectProperty<Integer> idEmployee;
    StringProperty firstNameEmployee;
    StringProperty lastNameEmployee;
    ObjectProperty<LocalDate> dateOfJob;
    ObjectProperty<LocalTime> hours;
    ObjectProperty<Float> size;
    StringProperty address;

    public Job(String nameType, Integer idClient, String firstNameClient, String lastNameClient, Integer idEmployee, String firstNameEmployee, String lastNameEmployee, LocalDate dateOfJob, LocalTime hours, Float size, String address) {
        this.nameType = new SimpleStringProperty(nameType);
        this.idClient = new SimpleObjectProperty<>(idClient);
        this.firstNameClient = new SimpleStringProperty(firstNameClient);
        this.lastNameClient = new SimpleStringProperty(lastNameClient);
        this.idEmployee = new SimpleObjectProperty<>(idEmployee);
        this.firstNameEmployee = new SimpleStringProperty(firstNameEmployee);
        this.lastNameEmployee = new SimpleStringProperty(lastNameEmployee);
        this.dateOfJob = new SimpleObjectProperty<>(dateOfJob);
        this.hours = new SimpleObjectProperty<>(hours);
        this.size = new SimpleObjectProperty<>(size);
        this.address = new SimpleStringProperty(address);
    }
    public Job() {
        this.nameType = new SimpleStringProperty("");
        this.idClient = new SimpleObjectProperty<>();
        this.firstNameClient = new SimpleStringProperty("");
        this.lastNameClient = new SimpleStringProperty("");
        this.idEmployee = new SimpleObjectProperty<>();
        this.firstNameEmployee = new SimpleStringProperty("");
        this.lastNameEmployee = new SimpleStringProperty("");
        this.dateOfJob = new SimpleObjectProperty<>();
        this.hours = new SimpleObjectProperty<>();
        this.size = new SimpleObjectProperty<>();
        this.address = new SimpleStringProperty("");
    }

    public String getNameType() {return nameType.get();}
    public StringProperty nameTypeProperty() {
        return nameType;
    }
    public void setNameType(String nameType) {
        this.nameType.set(nameType);
    }

    public Integer getIdClient() {return this.idClient.get();}
    public ObjectProperty<Integer> idClientProperty() {return this.idClient;}
    public void setIdClient(Integer idClient) {this.idClient.set(idClient);}

    public String getFirstNameClient() {return firstNameClient.get();}
    public StringProperty firstNameClientProperty() {
        return firstNameClient;
    }
    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient.set(firstNameClient);
    }

    public String getLastNameClient() {return lastNameClient.get();}
    public StringProperty lastNameClientProperty() {
        return lastNameClient;
    }
    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient.set(lastNameClient);
    }

    public Integer getIdEmployee() {return this.idEmployee.get();}
    public ObjectProperty<Integer> idEmployeeProperty() {return this.idEmployee;}
    public void setIdEmployee(Integer idEmployee) {this.idEmployee.set(idEmployee);}

    public String getFirstNameEmployee() {return firstNameEmployee.get();}
    public StringProperty firstNameEmployeeProperty() {
        return firstNameEmployee;
    }
    public void setFirstNameEmployee(String firstNameEmployee) {
        this.firstNameEmployee.set(firstNameEmployee);
    }

    public String getLastNameEmployee() {return lastNameEmployee.get();}
    public StringProperty lastNameEmployeeProperty() {
        return lastNameEmployee;
    }
    public void setLastNameEmployee(String lastNameEmployee) {
        this.lastNameEmployee.set(lastNameEmployee);
    }

    public LocalDate getDateOfJob() {
        return dateOfJob.get();
    }
    public ObjectProperty<LocalDate> dateOfJobProperty() {
        return dateOfJob;
    }
    public void setDateOfJob(LocalDate dateOfJob) {
        this.dateOfJob.set(dateOfJob);
    }

    public LocalTime getHours() {
        return hours.get();
    }
    public ObjectProperty<LocalTime> hoursProperty() {
        return hours;
    }
    public void setHours(LocalTime hours) {
        this.hours.set(hours);
    }

    public float getSize() {
        return size.get();
    }
    public ObjectProperty<Float> sizeProperty() {
        return size;
    }
    public void setSize(Float size) {
        this.size.set(size);
    }

    public String getAddress() {
        return address.get();
    }
    public StringProperty addressProperty() {
        return address;
    }
    public void setAddress(String address) {
        this.address.set(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Job job = (Job) o;
        return Objects.equals(nameType, job.nameType) && Objects.equals(idClient, job.idClient) && Objects.equals(idEmployee, job.idEmployee) && Objects.equals(dateOfJob, job.dateOfJob) && Objects.equals(hours, job.hours) && Objects.equals(size, job.size) && Objects.equals(address, job.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameType, idClient, idEmployee, dateOfJob, hours, size, address);
    }

    @Override
    public String toString() {
        return "Job{" + "nameType=" + nameType + ", idClient=" + idClient + ", idEmployee=" + idEmployee + ", dateOfJob=" + dateOfJob + ", hours=" + hours + ", size=" + size + ", address=" + address + '}';
    }
}
