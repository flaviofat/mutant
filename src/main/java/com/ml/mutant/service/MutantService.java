package com.ml.mutant.service;

import com.ml.mutant.domain.StatsDomain;

public interface MutantService {
    Boolean isMutant(String[] dna);
    StatsDomain getStats();
}
