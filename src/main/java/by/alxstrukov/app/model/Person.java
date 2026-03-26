package by.alxstrukov.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @Column
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;
}
