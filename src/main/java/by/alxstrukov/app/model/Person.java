package by.alxstrukov.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "people")
@Data
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

    @Column(name = "items")
    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
