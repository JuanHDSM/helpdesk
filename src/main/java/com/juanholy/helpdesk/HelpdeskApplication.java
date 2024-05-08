package com.juanholy.helpdesk;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.Request;
import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.enums.Priority;
import com.juanholy.helpdesk.domain.enums.Profile;
import com.juanholy.helpdesk.domain.enums.Status;
import com.juanholy.helpdesk.repositories.ClientRepository;
import com.juanholy.helpdesk.repositories.RequestRepository;
import com.juanholy.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private RequestRepository requestRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Technician tec1 = new Technician(null, "Valdir Cezar", "44023016020", "valdir@gmail.com", "123");
		tec1.addProfile(Profile.ADMIN);
		technicianRepository.save(tec1);

		Client cli1 =  new Client(null, "Linus Torvalds", "44784557083", "torvalds@gmail.com", "123");
		clientRepository.save(cli1);

		Request req = new Request(null, null, Priority.MEDIUM, Status.IN_PROGRESS, "Request 1", "First request", tec1, cli1);
		requestRepository.save(req);
	}
}
