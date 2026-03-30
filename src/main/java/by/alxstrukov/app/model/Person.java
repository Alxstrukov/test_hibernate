package by.alxstrukov.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "people")
@Data
@ToString
@NoArgsConstructor
public class Person {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Passport passport;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
        passport.setOwner(this);
    }
}
