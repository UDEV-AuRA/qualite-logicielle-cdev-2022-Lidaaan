package com.ipiecoles.java.java350.model;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeTest {

    //Test methode GetNombreAnneeAnciennete
    @Test
    public void testGetNombreAnneeAnciennete(){
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));

        Assertions.assertThat(2).isEqualTo(employe.getNombreAnneeAnciennete());
    }

    @Test
    public void testGetNombreAnneeAncienneteNull(){
        Employe employe = new Employe();

        Assertions.assertThat(0).isEqualTo(employe.getNombreAnneeAnciennete());

    }

    @Test
    public void testGetNombreAnneeAncienneteFuture(){
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(2));

        Assertions.assertThat(0).isEqualTo(employe.getNombreAnneeAnciennete());

    }

    @Test
    public void testGetNombreAnneeAnciennetePassee(){
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));

        Assertions.assertThat(2).isEqualTo(employe.getNombreAnneeAnciennete());

    }
    ///////////////////////////////Fin GetNombreAnneeAnciennete////////////////////////////

    //Test getNbConges/////////////////////////////////
    @Test
    public void testGetNbConges(){

    }

    ///////////////////////////////Fin getNbConges/////////////////////////////////
}
