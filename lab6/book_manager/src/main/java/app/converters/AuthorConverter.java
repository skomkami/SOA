package app.converters;

import app.dao.AuthorsDAO;
import app.model.Author;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "authorConverter")
public class AuthorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String author) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{AuthorsDAO}", AuthorsDAO.class);

        int iend = author.indexOf('.');
        String idSubstring;
        if (iend != -1) {
            idSubstring= author.substring(0 , iend);
            AuthorsDAO authorsDAO = (AuthorsDAO) vex.getValue(ctx.getELContext());
            return authorsDAO.find(Integer.valueOf(idSubstring));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object author) {
        if (author == null) {
            return "-";
        }
        Author a = (Author)author;
        return a.getId() + ". " + a.getFirstName() + " " + a.getLastName();
    }

}