package app.controller;

import app.models.Product;
import app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Scanner;

@Component
public class CmdController {

    @Value("true")
    private boolean started;
    private Scanner scanner;
    private final ProductService productService;

    public CmdController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    private void init(){
        System.out.println("init...");
        scanner = new Scanner(System.in);
    }

    @PreDestroy
    private void free(){
        System.out.println("free...");
        scanner.close();
    }

    public boolean process(){
        String[] cmd = scanner.nextLine().split(" ", 2);
        if (cmd.length == 0 || cmd[0].isEmpty()) {
            help();
        } else if (cmd[0].equalsIgnoreCase("all")) {
            all();
        } else if (cmd[0].equalsIgnoreCase("add")) {
            if (cmd.length == 2) {
                create(cmd[1]);
            } else {
                help();
            }
        } else if (cmd[0].equalsIgnoreCase("title")) {
            if (cmd.length == 2) {
                rename(cmd[1]);
            } else {
                help();
            }
        } else if (cmd[0].equalsIgnoreCase("cost")) {
            if (cmd.length == 2) {
                cost(cmd[1]);
            } else {
                help();
            }
        } else if (cmd[0].equalsIgnoreCase("del")) {
            if (cmd.length == 2) {
                delete(cmd[1]);
            } else {
                help();
            }
        } else if (cmd[0].equalsIgnoreCase("exit")) {
            started = false;
        } else {
            help();
        }
        return started;
    }

    private void all() {
        List<Product> list = productService.findAll();
        System.out.println("все товары:");
        list.forEach(p -> System.out.printf("%d %s cost %.2f\n", p.getId(), p.getTitle(), p.getCost()));
    }

    private void create(String args) {
        String[] params = args.split(",");
        if (params.length != 2) {
            help();
            return;
        }
        String n = params[0].trim();
        float c = Float.parseFloat(params[1].trim());
        if (productService.addProduct(n, c)) {
            System.out.printf("Create: %s cost %.2f\n", n, c);
        }
    }

    private void rename(String args) {
        String[] params = args.split(",");
        if (params.length != 2) {
            help();
            return;
        }
        int id = Integer.parseInt(params[0].trim());
        String n = params[1].trim();
        if (productService.rename(id, n)) {
            System.out.printf("product: %d now \"%s\"\n", id, n);
        }
    }

    private void cost(String args) {
        String[] params = args.split(",");
        if (params.length != 2) {
            help();
            return;
        }
        int id = Integer.parseInt(params[0].trim());
        float c = Float.parseFloat(params[1].trim());
        if (productService.recost(id, c)) {
            System.out.printf("product: %d cost = %.2f\n", id, c);
        }
    }

    private void delete(String param) {
        int id = Integer.parseInt(param.trim());
        if (productService.delete(id)) {
            System.out.printf("product: %d removed\n", id);
        }
    }

    public void help() {
        System.out.println("valid commands:");
        System.out.println("\tall");
        System.out.println("\tadd name,cost");
        System.out.println("\title id,newTitle");
        System.out.println("\tcost id,newcost");
        System.out.println("\tdel id");
        System.out.println("\texit");
    }

}
