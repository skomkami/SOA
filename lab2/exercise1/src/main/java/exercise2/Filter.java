package exercise2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "Filter", urlPatterns = "/doradcapiwny")
public class Filter implements javax.servlet.Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            int age = Integer.parseInt(req.getParameter("age"));
            if(age >= 18)
                chain.doFilter(req, resp);
            else
                out.println("Jesteś za młody");
        } catch(Exception e) {
            out.println("Niepoprawne dane");
        }

    }
}
