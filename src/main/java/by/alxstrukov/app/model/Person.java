package by.alxstrukov.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
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



    //CascadeType.PERSIST - указывает, что при сохранении в БД объекта Person, также будет сохранён каждый объект Item
    //CascadeType.ALL - включит все операции, которые есть в CascadeType
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
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

    public void addItem(Item item) {
        item.setOwner(this);
        if (this.items == null) this.items = new ArrayList<>();
        this.items.add(item);
    }
}
