package main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "ProductsServlet", urlPatterns = "/products")
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> products = new ArrayList<>(
                Arrays.asList(
                        new Product[]{
                                new Product(1, "Хлеб", 30f),
                                new Product(2, "Велосипед", 10000f),
                                new Product(3, "Шапка-ушанка", 500f),
                                new Product(4, "Телефон", 15000f),
                                new Product(5, "НЁХ", 100500f),
                                }));
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><body>");
        products.forEach(p -> printWriter.println("<p>" + p.toString() + "</p>"));
        printWriter.println("</body></html>");
    }
}
