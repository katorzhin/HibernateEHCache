package com.test.hibernate.entity;


import javax.persistence.*;

@Entity
@Table(name = "PersonInfo")
public class PersonInfo {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", insertable = false, updatable = false)
    private int personId;

    @Column(name = "passport_number")
    private String passportNumber;

    @OneToOne
    @PrimaryKeyJoinColumn
    Person person;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "personId=" + personId +
                ", passportNumber='" + passportNumber + '\'' +
                ", person=" + person +
                '}';
    }
}
