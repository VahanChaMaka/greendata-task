package ru.grishagin.greendatatask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Bank bank;

    @OneToOne
    private Client client;

    private Date openDate;

    //in hundredths of percent
    private int percentage;

    private int monthsDuration;
}
