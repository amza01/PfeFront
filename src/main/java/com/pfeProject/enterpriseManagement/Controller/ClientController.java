package com.pfeProject.enterpriseManagement.Controller;

import com.pfeProject.enterpriseManagement.DTO.ClientDto;
import com.pfeProject.enterpriseManagement.Model.Client;
import com.pfeProject.enterpriseManagement.Repository.ClientRepository;
import com.pfeProject.enterpriseManagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientService clientService;
    @GetMapping("/getClient")
    public ResponseEntity<List<Client>> getClient() {

        List<Client> clients = clientRepository.findAll();
        System.out.println(clients);
        return ResponseEntity.ok().body(clients);
    }
    @PostMapping("/postClient")
    public ResponseEntity<?> postClient(@RequestBody ClientDto clientJson) {
        System.out.println(clientJson);
        return clientService.addClient(clientJson);
    }
    @PutMapping("/updateClient/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientDto ClientJson) {


        // Votre logique de mise à jour ici, en utilisant le service UserService

        return clientService.updateClient(id, ClientJson);
    }
    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        System.out.println("Deleting user with ID: " + id);

        return clientService.deleteClient(id);
    }
}
