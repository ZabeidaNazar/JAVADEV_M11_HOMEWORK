package com.homework.java;

import com.homework.java.crud.ClientCrudService;
import com.homework.java.crud.PlanetCrudService;
import com.homework.java.crud.TicketCrudService;
import com.homework.java.entity.Client;
import com.homework.java.entity.Planet;
import com.homework.java.entity.Ticket;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        createTicket();
    }

    public static void testPlanetCrudService() {
        PlanetCrudService personService = new PlanetCrudService();

        // Створення нової Planet
        Planet newPlanet = new Planet();
        newPlanet.setId("JOHN");
        newPlanet.setName("John Doe");
        personService.save(newPlanet);

        // Читаємо та виводимо Planet по id
        Planet person = personService.findById("JOHN");
        System.out.println("Found Planet: " + person.getName());

        // Оновлюємо Planet
        person.setName("Jane");
        personService.update(person);

        // Видаляємо Planet
        personService.delete(person);

        HibernateUtil.getInstance().close();
    }

    public static void createTicket() {
        PlanetCrudService planetCrudService = new PlanetCrudService();
        ClientCrudService clientCrudService = new ClientCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();

        Planet mars = planetCrudService.findById("MARS");
        Planet earth = planetCrudService.findById("VEN");
        Client client = clientCrudService.findById(50);


        Ticket ticket = new Ticket(Timestamp.from(Instant.now()), client, earth, mars);
        ticketCrudService.save(ticket);


        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            System.out.println(session.get(Ticket.class, ticket.getId()));
        }

        System.out.println("Привіт");
    }
}
