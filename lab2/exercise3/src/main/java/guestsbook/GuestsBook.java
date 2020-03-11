package guestsbook;

import model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

@WebServlet(name = "GuestsBook", urlPatterns = "/guest-book")
public class GuestsBook extends HttpServlet {

    private Vector<Post> posts = new Vector<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean logged = (Boolean)req.getSession().getAttribute("logged");

        if( logged == null || logged == false)
            req.getRequestDispatcher("/").forward(req, resp);
        else {
            String formattedPosts = "";

            for(Post post: posts)
                formattedPosts +=
                        String.format(
                                "<h2>%s</h2>(%s) says: <br/> <p>%s</p>",
                                post.getUserName(),
                                post.getUserEmail(),
                                post.getComment()
                        );

            PrintWriter out = resp.getWriter();
            out.println(formattedPosts);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userName = req.getParameter("name");
            String userEmail = req.getParameter("email");
            String comment = req.getParameter("comment");
            posts.add(new Post(userName, userEmail, comment));

            req.getRequestDispatcher("/feedback_form.jsp").forward(req, resp);

        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            out.println(e.getMessage());
        }
    }
}
