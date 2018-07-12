package com.ml.mutant.model;

import javax.persistence.*;

@Entity
@Table(name = "DNA")
public class DnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DNA")
    private String dna;

    @Column(name = "IS_HUMAN")
    private Boolean isHuman;

    public DnaEntity(){}

    public DnaEntity(String dna, Boolean isHuman) {
        this.dna = dna;
        this.isHuman = isHuman;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public Boolean isHuman() {
        return isHuman;
    }

    public void setHuman(Boolean human) {
        isHuman = human;
    }
}
