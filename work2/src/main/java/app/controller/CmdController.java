package app.controller;

import app.models.Product;
import app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            return started;
        }
        if (cmd[0].equalsIgnoreCase("all")) {
            all();
            return started;
        }
        try {
            if (cmd[0].equalsIgnoreCase("add")) {
                create(getArgs(cmd));
            } else if (cmd[0].equalsIgnoreCase("title")) {
                rename(getArgs(cmd));
            } else if (cmd[0].equalsIgnoreCase("cost")) {
                cost(getArgs(cmd));
            } else if (cmd[0].equalsIgnoreCase("del")) {
                delete(getArgs(cmd)[0]);
            } else if (cmd[0].equalsIgnoreCase("count")) {
                count();
            } else if (cmd[0].equalsIgnoreCase("avg")) {
                average();
            } else if (cmd[0].equalsIgnoreCase("exit")) {
                started = false;
            }
        } catch (Exception e) {
            help();
        }
        return started;
    }

    private String[] getArgs(String[] cmd) throws Exception {
        if (cmd.length == 2) {
            return Arrays.stream(cmd[1].split(","))
                    .map(s -> s.trim())
                    .toArray(String[]::new);

        } else {
            throw new Exception("Invalid parameters.");
        }
    }

    private void all() {
        List<Product> list = productService.findAll();
        System.out.println("все товары:");
        list.forEach(p -> System.out.printf("%d %s cost %.2f\n", p.getId(), p.getTitle(), p.getCost()));
    }

    private void create(String[] args) {
        if (args.length != 2) {
            help();
            return;
        }
        String n = args[0];
        float c = Float.parseFloat(args[1]);
        if (productService.addProduct(n, c)) {
            System.out.printf("Create: %s cost %.2f\n", n, c);
        }
    }

    private void rename(String[] args) {
        if (args.length != 2) {
            help();
            return;
        }
        int id = Integer.parseInt(args[0]);
        String n = args[1];
        if (productService.rename(id, n)) {
            System.out.printf("product: %d now \"%s\"\n", id, n);
        }
    }

    private void cost(String[] args) {
        if (args.length != 2) {
            help();
            return;
        }
        int id = Integer.parseInt(args[0].trim());
        float c = Float.parseFloat(args[1].trim());
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

    private void count() {
        System.out.println("Count all products = " + productService.getCount());
    }

    private void average() {
        System.out.println("Average cost of all products = " + productService.getAvg());
    }

    public void help() {
        System.out.println("valid commands:");
        System.out.println("\tall");
        System.out.println("\tadd name,cost");
        System.out.println("\title id,newTitle");
        System.out.println("\tcost id,newcost");
        System.out.println("\tdel id");
        System.out.println("\tcount");
        System.out.println("\tavg");
        System.out.println("\texit");
    }

}
