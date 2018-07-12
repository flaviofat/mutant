package com.ml.mutant.service;

import com.ml.mutant.domain.StatsDomain;
import com.ml.mutant.model.DnaEntity;
import com.ml.mutant.repository.DnaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class MutantApplicationTests {

	private List<DnaEntity> list = new ArrayList<>();
	private StatsDomain sd = new StatsDomain(1,1,1.0);

	@Before
	public void setUp() {

		list.add(new DnaEntity("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]",false));
		list.add(new DnaEntity("[AATACT, CCCAGA, GGGATT, AATTCC, AGAGCG, TCACTG] ",true));

	}

	@MockBean
	private MutantService service;

	@MockBean
	private DnaRepository repository;

	@Test
	public void test_dna_mutant() {

		// given
		when(repository.findAll()).thenReturn(list);
		when(service.getStats()).thenReturn(sd);


		//assertThat(service.getStats().getQtdHumanDna()).isEqualTo(name);
	}


}
