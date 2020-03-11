package exercise2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "EkspertPiwny", urlPatterns = "/EkspertPiwny")
public class EkspertPiwny extends HttpServlet {

    private HashMap<String, String> colorMark = new HashMap<>();

    public void init() throws ServletException {
        super.init();
        colorMark.put("lightGold", "Browar Kormoran");
        colorMark.put("yellow", "Carlsberg");
        colorMark.put("darkGold", "Okocim");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beerColor = request.getParameter("beerColor");
        String destination = "wynik.jsp";

        String result = colorMark.getOrDefault(beerColor, "Coś poszło nie tak");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
        request.setAttribute("result", result);
        requestDispatcher.forward(request, response);
    }
}
