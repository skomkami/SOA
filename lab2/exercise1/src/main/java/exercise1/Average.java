package exercise1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet(name = "Average", urlPatterns = "/avg")
public class Average extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] numbers = request.getParameterValues("numbers");

        Double sum = 0.0;

        for(int i=0; i< numbers.length; i++)
           sum += Double.parseDouble(numbers[i]);

        PrintWriter out = response.getWriter();
        out.println(sum/numbers.length);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] numbers = req.getParameterValues("numbers");

        Boolean allNumbers = true;
        ArrayList<Double> parsedNumbers = new ArrayList<>();

        for(int i=0; i<numbers.length; ++i) {
            try {
                double d = Double.parseDouble(numbers[i]);
                parsedNumbers.add(d);
            } catch (NumberFormatException nfe) {
                allNumbers = false;
                break;
            }
        }

        PrintWriter out = resp.getWriter();

        if(allNumbers) {
            parsedNumbers.sort(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    if(o1 >= o2) return 1;
                    else return -1;
                }
            });

            System.out.println(parsedNumbers);
            out.println(parsedNumbers);
        } else
            out.println("Wrong data");

    }
}
