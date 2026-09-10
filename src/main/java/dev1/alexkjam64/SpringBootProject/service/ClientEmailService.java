package dev1.alexkjam64.SpringBootProject.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev1.alexkjam64.SpringBootProject.repository.ClientEmail;
import dev1.alexkjam64.SpringBootProject.repository.ClientEmailRepository;

@Service
public class ClientEmailService {
    private final ClientEmailRepository clientRepository;

    public ClientEmailService(ClientEmailRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientEmail retrieve(int id) throws NoDataException{
        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        return clientRepository.getEmail(id);
    }

    public Map<Integer, ClientEmail> retrieveAllEmails(List<Integer> ids){
        return clientRepository.getBatchEmails(ids).stream().collect(Collectors.toMap(ClientEmail::id, email->email));
    }

    // Sanitizes data before adding to database
    public void create(ClientEmail request, int id) throws InvalidDataException{
        sanitizeData(request);
        
        // Assuming it past all the checks... call the repo to create
        clientRepository.addClient(request, id);
    }

    // Sanitizes data before updating database
    public void update(ClientEmail entity, int id) throws InvalidDataException, NoDataException{
        sanitizeData(entity);

        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        clientRepository.updateClient(entity, id);
    }

    public void delete(int id) throws NoDataException{
        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        clientRepository.deleteClient(id);
    }

    protected void sanitizeData(ClientEmail data) throws InvalidDataException{
        // If the email is null or empty... blow up!
        if(data.email() == null || data.email().trim().isEmpty()){
            throw new InvalidDataException("Email is null or empty!");
        }

        // If any of the name attributes are longer than the db columns... blow up!
        if(data.email().length() > 60){
            throw new InvalidDataException("Email surpasses 60 characters!");
        }

        // Should follow the typical email format, otherwise... blow up!
        /* Local part: Letters and numbers are allowed along side . - _ + ~ ' & = %
         * Domain part: After the @ symbol letters and numbers are allowed along side - .
         * Emails are usually between 1 - 254 characters in length (but for this project it'll be 60)
         * The domain part must be a valid domain (but for this project we won't check the validity of it) 
        */
        if(!data.email().matches("^[a-zA-Z0-9._+~'-]+(?:[a-zA-Z0-9._+~'-]*[a-zA-Z0-9])?@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")){
            throw new InvalidDataException("Email error! Does not match the email format!");
        }
    }

    protected void checkDataExist(int id) throws NoDataException{
        if(clientRepository.getEmail(id) == null){
            throw new NoDataException("Data does not exist!");
        }
    }
}
