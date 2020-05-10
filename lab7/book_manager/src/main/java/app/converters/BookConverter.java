package app.converters;

import app.dao.BooksDAO;
import app.model.Book;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "bookConverter")
public class BookConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String book) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{BooksDAO}", BooksDAO.class);

        int iend = book.indexOf('.');
        String idSubstring;
        if (iend != -1) {
            idSubstring= book.substring(0 , iend);
            BooksDAO booksDAO = (BooksDAO) vex.getValue(ctx.getELContext());
            return booksDAO.find(Integer.valueOf(idSubstring));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object book) {
        if (book == null) {
            return "-";
        }
        Book b = (Book)book;
        return b.getId() + ". " + b.getTitle();
    }

}