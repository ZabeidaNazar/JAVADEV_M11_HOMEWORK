package com.homework.java.crud;

import com.homework.java.entity.Client;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ClientCrudServiceTest {
    private ClientCrudService clientService = new ClientCrudService();

    @Test
    void testSave() {
        Client client = new Client("Клієнт");

        clientService.save(client);

        Client resClient = clientService.findById(client.getId());
        Assertions.assertEquals(client, resClient);
    }

    @Test
    void testSaveWithOneSymbolInName() {
        Client client = new Client("Н");

        Assertions.assertThrows(ConstraintViolationException.class, () -> clientService.save(client));
    }

    @Test
    void testSaveWithMoreThanMaxSymbolInName() {
        Client client = new Client("Н".repeat(201));

        Assertions.assertThrows(DataException.class, () -> clientService.save(client));
    }

    @Test
    void testFindById() {
        Client firstClient = new Client(10, "Віктор");

        Client actualClient = clientService.findById(firstClient.getId());

        Assertions.assertEquals(firstClient, actualClient);
    }

    @Test
    void testUpdate() {
        Client modifiedNameOfClient = new Client(1, "Оксана 123");

        clientService.update(modifiedNameOfClient);

        Client resClient = clientService.findById(modifiedNameOfClient.getId());
        Assertions.assertEquals(modifiedNameOfClient, resClient);
    }

    @Test
    void testDelete() {
        Client client = new Client(14, "Денис");

        clientService.delete(client);

        Client resClient = clientService.findById(14);
        Assertions.assertNull(resClient);
    }
}