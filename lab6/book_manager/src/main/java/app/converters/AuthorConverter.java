package app.converters;

import app.beans.Authors;
import app.model.Author;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "authorConverter")
public class AuthorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String authorId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{authors}", Authors.class);

        Authors authors = (Authors)vex.getValue(ctx.getELContext());
        return authors.getAuthor(Integer.valueOf(authorId.substring(0,1)));
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