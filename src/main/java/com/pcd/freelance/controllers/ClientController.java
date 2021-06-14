package com.pcd.freelance.controllers;

import com.pcd.freelance.encryptDecrypt.AES;
import com.pcd.freelance.entities.Client;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping({"/clients"})
@CrossOrigin("http://localhost:4200")
public class ClientController  {


    @Autowired
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //add client
    @PostMapping("/add")
    public Client createClient(@Valid @RequestBody Client client) {
        client.setPassword(AES.encrypt(client.getPassword(),AES.getPersonalkey()));
        client.setInscriptionDate(new Date());
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
    public Client getClientByEmail(@PathVariable String clientEmail) {
        Client client = clientRepository.findByEmail(clientEmail).get();
        client.setPassword(AES.decrypt(client.getPassword(),AES.getPersonalkey()));
        return client ;
    }

    // update client

    @PutMapping("/{ClientId}")
    public Client updateClient(@PathVariable Long ClientId, @Valid @RequestBody Client clientRequest) {
        return clientRepository.findById(ClientId).map(client -> {
            client.setFirstName(clientRequest.getFirstName());
            client.setLastName(clientRequest.getLastName());
            client.setEmail(clientRequest.getEmail());
            client.setAddress(clientRequest.getAddress());
            client.setBirthday(clientRequest.getBirthday());
            client.setSexe(clientRequest.getSexe());
            client.setTelephoneNumber(clientRequest.getTelephoneNumber());
            client.setJob(clientRequest.getJob());
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + ClientId + " not found"));
    }

    // update client profile

    @PutMapping("/profile/{clientEmail}")
    public Client updateClientProfil(@PathVariable String clientEmail, @Valid @RequestBody Client clientRequest) {
        return clientRepository.findByEmail(clientEmail).map(client -> {
            client.setFirstName(clientRequest.getFirstName());
            client.setLastName(clientRequest.getLastName());
            client.setEmail(clientRequest.getEmail());
            client.setAddress(clientRequest.getAddress());
            client.setBirthday(clientRequest.getBirthday());
            client.setSexe(clientRequest.getSexe());
            client.setTelephoneNumber(clientRequest.getTelephoneNumber());
            client.setJob(clientRequest.getJob());
            client.setNationality(clientRequest.getNationality());
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + clientEmail + " not found"));
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

    @PostMapping("/saveImageByEmail/{clientEmail}")
    public Client uplaodimage(@RequestParam("imageFile") MultipartFile file, @PathVariable String clientEmail) throws IOException, SQLException {
        /*String extension = FilenameUtils.getExtension(file.getOriginalFilename());*/
        System.out.println("Original Image Byte Size - " + file.getBytes().length + " name : "+ file.getOriginalFilename() +
                " type : "+ file.getContentType());
        Client client = clientRepository.findByEmail(clientEmail).get();
        if(file!=null) {
            client.setImage(compressBytes(file.getBytes()));
        }


        return clientRepository.save(client);
    }
    @GetMapping(path = { "/getImageByEmail/{clientEmail}" })

    public Client getimage(@PathVariable String clientEmail) throws IOException {


        Client client = clientRepository.findByEmail(clientEmail).get();

        if(client.getImage()!=null){
            client.setImage(decompressBytes(client.getImage()));
        }





        return client;

    }

    // compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();

        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }

        return outputStream.toByteArray();
    }
}
