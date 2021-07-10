package ru.geekbrains.HiberSpringApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.geekbrains.HiberSpringApp.entities.Client;
import ru.geekbrains.HiberSpringApp.entities.Product;
import ru.geekbrains.HiberSpringApp.services.ClientService;
import ru.geekbrains.HiberSpringApp.services.ProductService;

@SpringBootApplication
public class HiberSpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiberSpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner out(ClientService clientService, ProductService productService) {
		return (args) -> {
			Client client = clientService.getClient(1);
			if (client == null) {
				client = clientService.newClient("Покупатель №1");
			}
			Product product = productService.getProduct(1);
			if (product == null) {
				product = productService.newProduct("Товар №1", 100f);
			}

//			clientService.addProduct(client, product);

			if (client != null) {
				System.out.println("products of client " + client.getName() );
				clientService.getClientProducts(client).forEach(System.out::println);
			}

			if (product != null) {
				System.out.println("clients of product " + product.getName() );
				productService.getProductClients(product).forEach(System.out::println);
			}

			System.out.println("Clients");
			clientService.allClients().forEach(System.out::println);

			System.out.println("Products");
			productService.allProducts().forEach(System.out::println);
		};
	}
}
