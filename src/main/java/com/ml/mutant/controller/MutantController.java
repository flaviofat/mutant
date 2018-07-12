package com.ml.mutant.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ml.mutant.domain.MutantDomain;
import com.ml.mutant.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class MutantController {

    @Autowired
    private MutantService service;

    @PostMapping("/mutant")
    public ResponseEntity<List<JSONPObject>> checkDNA(@Valid @RequestBody MutantDomain mutant) {
        return new ResponseEntity(service.isMutant(mutant.getDna()), HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<JSONPObject>> stats() {
        return new ResponseEntity(service.getStats(), HttpStatus.OK);
    }

}
