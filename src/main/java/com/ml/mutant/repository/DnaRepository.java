package com.ml.mutant.repository;

import com.ml.mutant.model.DnaEntity;
import org.springframework.data.repository.CrudRepository;

public interface DnaRepository  extends CrudRepository<DnaEntity, Long> {}
