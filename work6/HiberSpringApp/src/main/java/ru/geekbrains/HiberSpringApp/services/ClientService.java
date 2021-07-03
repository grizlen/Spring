package ru.geekbrains.HiberSpringApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.HiberSpringApp.entities.Client;
import ru.geekbrains.HiberSpringApp.entities.Product;
import ru.geekbrains.HiberSpringApp.repositories.ClientRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clients;

    public ClientService(@Autowired ClientRepository clients) {
        this.clients = clients;
    }

    public List<Client> allClients() {
        return clients.findAll();
    }

    public Client newClient(String name) {
        Client client = new Client(name);
        clients.saveOrUpdate(client);
        return client;
    }

    public Client getClient(int id) {
        return clients.get(id);
    }

    public void addProduct(Client client, Product product) {
        client = clients.get(client.getId());
        client.getProducts().add(product);
        clients.saveOrUpdate(client);
    }

    public List<Product> getClientProducts(Client client) {
        client = clients.get(client.getId());
        return client != null ? client.getProducts() : Collections.emptyList();
    }
}
