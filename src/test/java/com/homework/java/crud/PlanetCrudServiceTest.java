package com.homework.java.crud;

import com.homework.java.entity.Planet;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlanetCrudServiceTest {
    private PlanetCrudService planetService = new PlanetCrudService();

    @Test
    void testSave() {
        Planet planet = new Planet("TEST", "Тестова планета");

        planetService.save(planet);

        Planet resPlanet = planetService.findById(planet.getId());
        Assertions.assertEquals(planet, resPlanet);
    }

    @Test
    void testSaveWithExistingId() {
        Planet planet = new Planet("EAR", "Земля 2");

        Assertions.assertThrows(ConstraintViolationException.class, () -> planetService.save(planet));
    }

    @Test
    void testSaveWithOneSymbolInId() {
        Planet planet = new Planet("1", "Планета");

        Assertions.assertThrows(ConstraintViolationException.class, () -> planetService.save(planet));
    }

    @Test
    void testSaveWithSixSymbolInId() {
        Planet planet = new Planet("666666", "Планета");

        Assertions.assertThrows(ConstraintViolationException.class, () -> planetService.save(planet));
    }

    @Test
    void testSaveWithLowercaseLetterInId() {
        Planet planet = new Planet("NAMe", "Планета");

        Assertions.assertThrows(ConstraintViolationException.class, () -> planetService.save(planet));
    }

    @Test
    void testSaveNotLatinLettersInId() {
        Planet planet = new Planet("ПЛАН", "Планета");

        Assertions.assertThrows(ConstraintViolationException.class, () -> planetService.save(planet));
    }

    @Test
    void testFindById() {
        Planet earth = new Planet("URAN", "Уран");

        Planet actualPlanet = planetService.findById("URAN");

        Assertions.assertEquals(earth, actualPlanet);
    }

    @Test
    void testUpdate() {
        Planet modifiedNameOfEarth = new Planet("EAR", "Планета Земля");

        planetService.update(modifiedNameOfEarth);

        Planet resPlanet = planetService.findById("EAR");
        Assertions.assertEquals(modifiedNameOfEarth, resPlanet);
    }

    @Test
    void testDelete() {
        Planet earth = new Planet("NEPT", "Нептун");

        planetService.delete(earth);

        Planet resPlanet = planetService.findById(earth.getId());
        Assertions.assertNull(resPlanet);
    }
}