package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.repositories.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;





@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProduitServiceImpTest {

    private ProductRepository produitRepository =  Mockito.mock(ProductRepository.class);;

    @InjectMocks
    private ProductServiceImpl produitService ;
    @Autowired
    private ProductRepository junitProRepo;
    @Autowired
    private ProductRepository junIProduitService;

    List<Product> produitList = new ArrayList() {
        {
            add(
                    Product.builder()
                            .idProduct(555L)
                            .title("Raed")
                            .price(555L)

                            .build()

            );
        }};

    @Test
    void retrieveAllProduits() {

        Mockito.when(produitRepository.findAll()).thenReturn(produitList);
        List<Product> produitList = produitService.retreiveAllProduct();
        assertFalse(produitList.isEmpty());
        verify(produitRepository).findAll();
    }

    @Test
    void addProduit() {
        Product newProduit = Product.builder()
                .idProduct(555L)
                .title("Raed")
                .price(555L)
                .build();
        Product responseProduit = this.produitService.addProduct(newProduit,newProduit.getIdProduct());
        assertNotNull(responseProduit);
        assertEquals(newProduit.getIdProduct(),responseProduit.getIdProduct());
        this.produitService.deleteProduct(responseProduit.getIdProduct());

    }

    @Test
    void deleteProduit() {
    }

    @Test
    void updateProduit() {
    }

    @Test
    void retrieveProduit() {
    }
}
