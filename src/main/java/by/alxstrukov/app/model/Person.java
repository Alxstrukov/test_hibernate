package by.alxstrukov.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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


    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    private List<Item> items;

    public Person(int age, List<Item> items, String name) {
        this.age = age;
        this.items = items;
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
