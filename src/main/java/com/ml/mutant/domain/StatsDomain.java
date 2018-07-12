package com.ml.mutant.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDomain {

    @JsonProperty("count_mutant_dna")
    private Integer qtdMutantDna = 0;

    @JsonProperty("count_human_dna")
    private Integer qtdHumanDna = 0;

    @JsonProperty("ratio")
    private Double ratio = 0.0;

    public StatsDomain(){}

    public StatsDomain(Integer qtdMutantDna, Integer qtdHumanDna, Double ratio) {
        this.qtdMutantDna = qtdMutantDna;
        this.qtdHumanDna = qtdHumanDna;
        this.ratio = ratio;
    }

    public Integer getQtdMutantDna() {
        return qtdMutantDna;
    }

    public void setQtdMutantDna(Integer qtdMutantDna) {
        this.qtdMutantDna = qtdMutantDna;
    }

    public Integer getQtdHumanDna() {
        return qtdHumanDna;
    }

    public void setQtdHumanDna(Integer qtdHumanDna) {
        this.qtdHumanDna = qtdHumanDna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
