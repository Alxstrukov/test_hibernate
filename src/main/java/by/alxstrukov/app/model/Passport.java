package by.alxstrukov.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "passports")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passport implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "passport_number")
    private int passportNumber;

    public Passport(int passportNumber) {
        this.owner = null;
        this.passportNumber = passportNumber;
    }
}
