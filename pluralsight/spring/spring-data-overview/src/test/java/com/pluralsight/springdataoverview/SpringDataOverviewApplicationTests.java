package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;

//@SpringBootTest We need a parts of the spring framework for testing Data / JPA tests.
@DataJpaTest
public class SpringDataOverviewApplicationTests {

    @Autowired
    EntityManager entityManager;

    @Test
    void contextLoads() {

        Flight flight = new Flight();
        flight.setOrigin("LAX");
        flight.setDestination("FDR");
        flight.setScheduledAt(LocalDateTime.now());

        entityManager.persist(flight);

        TypedQuery<Flight> writtenFlight = entityManager.createQuery(
                "SELECT f from Flight f", Flight.class);

        Assertions.assertThat(writtenFlight.getResultList())
                .hasSize(1)
                .first().isEqualTo(flight);
    }

}
