package com.juanholy.helpdesk;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.Request;
import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.enums.Priority;
import com.juanholy.helpdesk.domain.enums.Status;
import com.juanholy.helpdesk.repositories.ClientRepository;
import com.juanholy.helpdesk.repositories.RequestRepository;
import com.juanholy.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Override
    public void run(String... args) throws Exception {

        Technician tec1 = new Technician(null, "Valdir Cezar", "44023016020", "valdir@gmail.com", "123", com.juanholy.helpdesk.domain.enums.Profile.TECHNICIAN);
        technicianRepository.save(tec1);

        Client cli1 =  new Client(null, "Linus Torvalds", "44784557083", "torvalds@gmail.com", "123", com.juanholy.helpdesk.domain.enums.Profile.CLIENT);
        clientRepository.save(cli1);

        Request req = new Request(null, null, Priority.MEDIUM, Status.IN_PROGRESS, "Request 1", "First request", tec1, cli1);
        requestRepository.save(req);
    }
}
