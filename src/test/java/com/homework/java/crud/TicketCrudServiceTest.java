package com.homework.java.crud;

import com.homework.java.entity.Client;
import com.homework.java.entity.Planet;
import com.homework.java.entity.Ticket;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;


class TicketCrudServiceTest {
    private TicketCrudService ticketService = new TicketCrudService();

    @Test
    void testSave() {
        Ticket ticket = new Ticket(Timestamp.valueOf("2024-01-03 20:56:27.847962"),
            new Client(1, "Оксана"),
            new Planet("MARS", "Марс"),
            new Planet("VEN", "Венера")
        );

        ticketService.save(ticket);

        Ticket resTicket = ticketService.findById(ticket.getId());
        Assertions.assertEquals(ticket, resTicket);
    }

    @Test
    void testSaveWithNullClient() {
        Ticket ticket = new Ticket(Timestamp.valueOf("2024-01-03 20:56:27.847962"),
                null,
                new Planet("MARS", "Марс"),
                new Planet("VEN", "Венера")
        );

        Assertions.assertThrows(PropertyValueException.class, () -> ticketService.save(ticket));
    }

    @Test
    void testSaveWithNotExistingClient() {
        Ticket ticket = new Ticket(Timestamp.valueOf("2024-01-03 20:56:27.847962"),
                new Client(Integer.MAX_VALUE, "Не існує"),
                new Planet("MARS", "Марс"),
                new Planet("VEN", "Венера")
        );

        Assertions.assertThrows(ConstraintViolationException.class, () -> ticketService.save(ticket));
    }

    @Test
    void testSaveWithNotExistingFromPlanet() {
        Ticket ticket = new Ticket(Timestamp.valueOf("2024-01-03 20:56:27.847962"),
                new Client(1, "Оксана"),
                new Planet("NEXIS", "Не існує"),
                new Planet("VEN", "Венера")
        );

        Assertions.assertThrows(IllegalStateException.class, () -> ticketService.save(ticket));
    }

    @Test
    void testSaveWithNotExistingToPlanet() {
        Ticket ticket = new Ticket(Timestamp.valueOf("2024-01-03 20:56:27.847962"),
                new Client(1, "Оксана"),
                new Planet("VEN", "Венера"),
                new Planet("NEXIS", "Не існує")
        );

        Assertions.assertThrows(IllegalStateException.class, () -> ticketService.save(ticket));
    }

    @Test
    void testSaveWithNullPlanet() {
        Ticket ticket = new Ticket(Timestamp.valueOf("2024-01-03 20:56:27.847962"),
                new Client(1, "Оксана"),
                null,
                new Planet("VEN", "Венера")
        );

        Assertions.assertThrows(PropertyValueException.class, () -> ticketService.save(ticket));
    }

    @Test
    void testFindById() {
        Ticket firstTicket = new Ticket(7,
                Timestamp.valueOf("2019-01-21 16:56:44.000"),
                new Client(7, "Тетяна"),
                new Planet("VEN", "Венера"),
                new Planet("URAN", "Уран")
        );

        Ticket actualTicket = ticketService.findById(firstTicket.getId());

        Assertions.assertEquals(firstTicket, actualTicket);
    }

    @Test
    void testUpdate() {
        Ticket modifiedToPlanetOfTicket = new Ticket(26,
                Timestamp.valueOf("2021-12-08 23:48:02.000"),
                new Client(26, "Валентина"),
                new Planet("JUP", "Юпітер"),
                new Planet("VEN", "Венера")
        );

        ticketService.update(modifiedToPlanetOfTicket);

        Ticket resTicket = ticketService.findById(modifiedToPlanetOfTicket.getId());
        Assertions.assertEquals(modifiedToPlanetOfTicket, resTicket);
    }

    @Test
    void testDelete() {
        Ticket ticket = new Ticket(23,
                Timestamp.valueOf("2016-02-21 17:02:28.000"),
                new Client(23, "Світлана"),
                new Planet("MARS", "Уран"),
                new Planet("URAN", "Земля")
        );

        ticketService.delete(ticket);

        Ticket resTicket = ticketService.findById(ticket.getId());
        Assertions.assertNull(resTicket);
    }
}