package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OperatorServiceImplTest {



    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Test
    public void testAddOperator() {
        //Creer un objet Operator
        Operator operator = Operator.builder()
                .fname("ahmed")
                .lname("ahmed")
                .password("12345")
                .build();
//génère un id pour lopérator et envoie loperator sauvegarder
        when(operatorRepository.save(Mockito.any(Operator.class))).thenAnswer(invocation -> {
            Operator savedOperator = invocation.getArgument(0);
            savedOperator.setIdOperateur(generateNonNullOrUniqueID());
            return savedOperator;
        });

        Operator savedOperator = operatorService.addOperator(operator);
// vérifier que la méthode save appelé une seule fois avec un objet operator
        verify(operatorRepository, times(1)).save(Mockito.any(Operator.class));

        assertNotNull(savedOperator.getIdOperateur());
        assertEquals("ahmed", savedOperator.getFname());
        assertEquals("ahmed", savedOperator.getLname());
        assertEquals("12345", savedOperator.getPassword());
    }

    private Long generateNonNullOrUniqueID() {

        return System.currentTimeMillis();
    }

    @Test
    public void testRetrieveOperator() {
        // Create a new operator
        Operator operator = new Operator();
        operator.setFname("rania");
        operator.setLname("Smith");
        operator.setPassword("0000");

        // pour simuler le comportement save
        when(operatorRepository.save(Mockito.any(Operator.class))).thenReturn(operator);


        Operator savedOperator = operatorRepository.save(operator);

        // pour simuler le comportement findbyid
        when(operatorRepository.findById(savedOperator.getIdOperateur())).thenReturn(java.util.Optional.ofNullable(savedOperator));

        Operator retrievedOperator = operatorService.retrieveOperator(savedOperator.getIdOperateur());

        // Verifie que findbyid  appelee une seule fois avec cet id
        verify(operatorRepository, times(1)).findById(savedOperator.getIdOperateur());

        assertNotNull(retrievedOperator);
        assertEquals("rania", retrievedOperator.getFname());
        assertEquals("Smith", retrievedOperator.getLname());
        assertEquals("0000", retrievedOperator.getPassword());
    }

}