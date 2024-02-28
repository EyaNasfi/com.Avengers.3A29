
package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class event {
    private int id;
    private String lieu;
    private String name;
    private LocalDate date;
    private LocalTime heure;
// syrine

    public event(String nameValue, String lieuValue){
        this.name=nameValue;
        this.lieu=lieuValue;

    }

    public event() {
        this.id = id;
        this.lieu = lieu;
        this.name = name;
        this.date = date;
        this.heure = heure;
    }
public event(String nameValue, String lieuValue, LocalDate dateValue, LocalTime timeValue){
        this.name=nameValue;
        this.lieu=lieuValue;
        this.date=dateValue;
        this.heure=timeValue;
}


    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "event{" +
                "id=" + id +
                ", lieu='" + lieu + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", heure=" + heure +
                '}';
    }
}


