package com.ml.mutant.service;

import com.ml.mutant.domain.StatsDomain;
import com.ml.mutant.model.DnaEntity;
import com.ml.mutant.repository.DnaRepository;
import com.ml.mutant.service.impl.MutantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

    private static final int ZERO = 0;

    private final String[] flat = {"AAAAGT", "CCCCGA", "GGGATT", "AATTCC", "AGAGCG", "TCACTG"};
    private final String[] vertical = {"AGGAGT", "AAGCGA", "AGAATT", "AATACT", "AGAGAT", "TCACAT"};
    private final String[] diagonal = {"AGGAGT", "TAGCGA", "AGAGTT", "TATAGG", "AGAGTA", "TCACAT"};
    private final String[] nonMatch = {"DDFFFD", "WWQQQW", "SSVSVV", "LLXXXL", "PPOPPP", "HHJJJH"};

    @Mock
    private DnaRepository dnaRepository;
    @Mock
    private DnaEntity humanDna;
    @Mock
    private DnaEntity mutantDna;

    private MutantServiceImpl service;

    @Before
    public void setup() {

        when(humanDna.isHuman()).thenReturn(true);
        when(dnaRepository.findAll()).thenReturn(asList(humanDna, mutantDna));

        service = new MutantServiceImpl(dnaRepository);
    }

    @Test
    public void shouldBeMutantWhenDnsIsHorizontal() {

        Boolean mutant = service.isMutant(flat);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldBeMutantWhenDnsIsVertical() {

        Boolean mutant = service.isMutant(vertical);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldBeMutantWhenDnsIsDiagonal() {

        Boolean mutant = service.isMutant(diagonal);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldSaveAsMutant() {

        ArgumentCaptor<DnaEntity> captor = ArgumentCaptor.forClass(DnaEntity.class);

        when(dnaRepository.save(captor.capture())).thenReturn(mock(DnaEntity.class));
        service.isMutant(vertical);

        DnaEntity dnaEntity = captor.getValue();
        assertThat(dnaEntity.isHuman(), is(false));
    }

    @Test
    public void shouldSaveAsHuman() {

        ArgumentCaptor<DnaEntity> captor = ArgumentCaptor.forClass(DnaEntity.class);

        when(dnaRepository.save(captor.capture())).thenReturn(mock(DnaEntity.class));

        try {
            service.isMutant(nonMatch);
        } catch (Exception e) {
        }
        DnaEntity dnaEntity = captor.getValue();
        assertThat(dnaEntity.isHuman(), is(true));
    }

    @Test
    public void shouldGetZeroWhenThereIsNoStats() {

        when(dnaRepository.findAll()).thenReturn(Collections.emptyList());

        StatsDomain stats = service.getStats();

        assertThat(stats.getQtdMutantDna(), equalTo(ZERO));
        assertThat(stats.getQtdHumanDna(), equalTo(ZERO));
        assertThat(stats.getRatio(), equalTo(0.0));
    }

    @Test
    public void shouldStats() {

        StatsDomain stats = service.getStats();

        assertThat(stats.getQtdMutantDna(), equalTo(1));
        assertThat(stats.getQtdHumanDna(), equalTo(1));
        assertThat(stats.getRatio(), equalTo(1.0));
    }

}


