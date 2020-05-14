package app.rest;

import app.dao.MoviesDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/movie")
public class MoviesEndpoint {

    MoviesDAO moviesDAO = new MoviesDAO();
    @GET
    @Path("/{id}")
    public String getMovie(@PathParam("id") int id) {
        return "test";
//        if (movie != null) {
//            return Response.ok(movie).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response saveCity(@FormParam("title") String title,
//                             @FormParam("link") String link) {
//
//        Movie movie = new Movie();
//        movie.setTitle(title);
//        movie.setLink(link);
//
//        try {
//            moviesDAO.add(movie);
//            return Response.ok().status(Response.Status.CREATED).build();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return Response.notModified().build();
//        }
//    }
}
