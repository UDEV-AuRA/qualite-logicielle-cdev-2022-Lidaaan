package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.exception.EmployeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNow(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();

    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbauchePassee(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(2);
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheFuture(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(2));

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNull(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @ParameterizedTest
    @CsvSource({
            "'M12345',0,1,1.0,1700.0",
            "'M12345',2,1,1.0,1900.0",
            "'M12345',0,1,1.0,1700.0",
            ",0,1,1.0,1000.0",
            "'T12345',0,1,1.0,1000.0",
            "'T12345',1,1,1.0,1100.0",
            "'T12345',0,,1.0,1000.0",
            "'T12345',0,2,1.0,2300.0",
            "'T12345',3,2,1.0,2600.0",
            "'T12345',3,2,0.5,1300.0",
    })
    public void testGetPrimeAnnuelle(String matricule,
                                     Integer nbAnneesAnciennete,
                                     Integer performance,
                                     Double tauxTravail,
                                     Double primeCalculee){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbAnneesAnciennete),
                2500d, performance, tauxTravail);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);
    }

    //Note : La méthode augmenter salaire n'est censé qu'augmenter le salaire
    @Test
    public void testAugmenterSalairePositif() throws EmployeException {
        Employe employe = new Employe("Doe", "John", "T1234", LocalDate.now(), 1300d, 1, 1.0);
        employe.augmenterSalaire(20);

        Assertions.assertThat(employe.getSalaire()).isEqualTo(1560);
    }

    @Test
    public void testAugmenterSalaireNegatif(){
        try{
            Employe employe = new Employe("Doe", "John", "T1234", LocalDate.now(), 1300d, 1, 1.0);
            employe.augmenterSalaire(-20);
        }catch (EmployeException e){
        }
    }

    @Test
    public void testAugmenterSalaireNull(){
        try {
            Employe employe = new Employe("Doe", "John", "T1234", LocalDate.now(), 2000d, 1, 1.0);

            employe.augmenterSalaire(0);
        } catch (EmployeException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Le pourcentage saisie doit être positif");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "2019, 9",
            "2021, 11",
            "2022, 11",
            "2032, 10",
    })
    public void testGetNbRtt(Integer year, Integer nbRttCalcule){
        Employe employe = new Employe();

        Integer actual = employe.getNbRtt(LocalDate.of(year,1,1));

        Assertions.assertThat(actual).isEqualTo(nbRttCalcule);
    }
}
