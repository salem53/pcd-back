package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Client;
import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/clients"})
@CrossOrigin("http://localhost:4200")
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //add client
    @PostMapping("/add")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    //get list of clients

    @GetMapping("/list")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    //get one client

    @GetMapping("/{ClientId}")
    public java.util.Optional<Client> getClient(@PathVariable Long ClientId) {
        return  clientRepository.findById(ClientId);
    }

    //get client by email

    @GetMapping("/getClientByEmail/{clientEmail}")
    public java.util.Optional<Client> getClientByEmail(@PathVariable String clientEmail) {
        return clientRepository.findByEmail(clientEmail);
    }

    // update client

    @PutMapping("/{ClientId}")
    public Client updateClient(@PathVariable Long ClientId, @Valid @RequestBody Client clientRequest) {
        return clientRepository.findById(ClientId).map(client -> {
            client.setFirstName(clientRequest.getFirstName());
            client.setLastName(clientRequest.getLastName());
            client.setEmail(clientRequest.getEmail());
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + ClientId + " not found"));
    }

    // delete client
    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientRepository.findById(clientId).map(client -> {
            clientRepository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " +
                clientId + " not found"));
    }
}
