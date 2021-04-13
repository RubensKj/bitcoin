package com.rubenskj.repository;

import com.rubenskj.model.Ordem;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class OrdemRepositoryTest {

    @InjectMock
    OrdemRepository ordemRepository;

    @Test
    public void testarSeListAllRetornaRetornaOrdensCorretas() {
        Ordem primeiraOrdem = new Ordem();
        Ordem segundaOrdem = new Ordem();

        List<Ordem> ordens = new ArrayList<>(Arrays.asList(primeiraOrdem, segundaOrdem));

        Mockito.when(ordemRepository.listAll()).thenReturn(ordens);

        List<Ordem> ordensFromRepository = ordemRepository.listAll();

        Assertions.assertSame(segundaOrdem, ordensFromRepository.get(1));
    }
}
