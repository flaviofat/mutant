package com.ml.mutant.service.impl;

import com.ml.mutant.domain.StatsDomain;
import com.ml.mutant.exception.MutantException;
import com.ml.mutant.model.DnaEntity;
import com.ml.mutant.repository.DnaRepository;
import com.ml.mutant.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MutantServiceImpl implements MutantService{

    final static String DNA_SEQ_CCCC = "CCCC";
    final static String DNA_SEQ_GGGG = "GGGG";
    final static String DNA_SEQ_AAAA = "AAAA";

    @Autowired
    private DnaRepository dnaRepository;

    public Boolean isMutant(String[] dna) {

        Integer count = 0;

        for (int j = 0; j < dna.length; j++) {

            String x = "";
            String y = "";
            String tx = "";
            String ty = "";

            for (int i = 0; i < dna.length; i++) {
                x = x + dna[j].charAt(i);
                y = y + dna[i].charAt(j);

                if(i+j < dna.length) {
                    tx = tx + dna[i].charAt(i + j);

                    if (j != 0)
                        ty = ty + dna[i + j].charAt(i);
                }
            }

            if (validateDna(x))
                count = count + 1;

            if (validateDna(y))
                count = count + 1;

            if (validateDna(tx))
                count = count + 1;

            if (validateDna(ty))
                count = count + 1;

        }

        if(count > 1) {
            dnaRepository.save(new DnaEntity(Arrays.toString(dna), Boolean.FALSE));
            return true;
        }

        dnaRepository.save(new DnaEntity(Arrays.toString(dna), Boolean.TRUE));
        throw new MutantException("${msg.no_mutant}");
    }

    private Boolean validateDna(String dna){
        if (dna.contains(DNA_SEQ_AAAA) || dna.contains(DNA_SEQ_CCCC) || dna.contains(DNA_SEQ_GGGG))
            return true;
        return false;
    }


    public StatsDomain getStats(){

        Iterable<DnaEntity> entitys = dnaRepository.findAll();

        StatsDomain domain = new StatsDomain();
        entitys.forEach(d -> {
            if(d.isHuman()) {
                domain.setQtdHumanDna(domain.getQtdHumanDna() + 1);
            } else {
                domain.setQtdMutantDna(domain.getQtdMutantDna() + 1);
            }
        });

        if (domain.getQtdMutantDna() != 0 && domain.getQtdHumanDna() == 0){
            domain.setRatio(new Double(domain.getQtdMutantDna().doubleValue()));
        } else if (domain.getQtdMutantDna() != 0 && domain.getQtdHumanDna() != 0) {
            domain.setRatio(new Double(domain.getQtdMutantDna().doubleValue() / domain.getQtdHumanDna().doubleValue()));
        }

        return domain;
    }

    public static void main(String[] args) {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        //String[] dna = {"ZZZZZZ", "ZZZZZZ", "ZZZZZZ", "ZZZZZZ", "CCCCTA", "ZZZZZZ"};
        System.out.println(new MutantServiceImpl().isMutant(dna));
    }

}
