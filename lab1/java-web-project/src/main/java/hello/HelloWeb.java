package hello;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hi")
class HelloWeb extends HttpServlet {

    public void service(HttpServletRequest req, @org.jetbrains.annotations.NotNull HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Welcome in java");
    }
}