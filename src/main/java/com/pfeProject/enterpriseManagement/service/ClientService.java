package com.pfeProject.enterpriseManagement.service;

import com.pfeProject.enterpriseManagement.DTO.ClientDto;
import com.pfeProject.enterpriseManagement.Model.Client;
import com.pfeProject.enterpriseManagement.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


        public ResponseEntity<?> addClient(ClientDto clientJson)
        {
            Client newClient= mapToClient(clientJson);
            clientRepository.save(newClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        }

    private Client mapToClient(ClientDto clientJson) {
        Client client =new Client();
        client.setEmail(clientJson.getEmail());
        client.setAddresse(clientJson.getAddresse());

        client.setNumeroTel(clientJson.getNumeroTel());
        client.setRemiseTVA(clientJson.getRemiseTVA());
        return client;
    }
    public ResponseEntity<?> updateClient(Long id, ClientDto updatedClientData) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            // Mettre à jour les champs de l'utilisateur avec les nouvelles données
            existingClient.setNumeroTel(updatedClientData.getNumeroTel());
            existingClient.setEmail(updatedClientData.getEmail());
            existingClient.setAddresse(updatedClientData.getAddresse());
            existingClient.setRemiseTVA(updatedClientData.getRemiseTVA());

existingClient.setRemiseTVA(updatedClientData.getRemiseTVA());
            clientRepository.save(existingClient);

            return ResponseEntity.ok(existingClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteClient(Long id) {


        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok(optionalClient.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
