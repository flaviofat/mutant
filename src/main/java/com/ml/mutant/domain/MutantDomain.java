package com.ml.mutant.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MutantDomain {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "${msg.dna_required}")
    @JsonProperty("dna")
    private String[] dna;

    @JsonProperty("is_human")
    private Boolean isHuman;

    public MutantDomain(){}

    public MutantDomain(Long id, String[] dna, Boolean isHuman) {
        this.id = id;
        this.dna = dna;
        this.isHuman = isHuman;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public Boolean getHuman() {
        return isHuman;
    }

    public void setHuman(Boolean human) {
        isHuman = human;
    }
}
