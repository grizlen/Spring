package app;

import app.controller.CmdController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("app");
        CmdController controller = context.getBean(CmdController.class);
        controller.help();
        while (controller.process());
        context.close();
    }
}