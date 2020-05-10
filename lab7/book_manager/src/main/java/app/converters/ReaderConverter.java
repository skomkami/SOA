package app.converters;

import app.dao.ReadersDAO;
import app.model.Reader;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "readerConverter")
public class ReaderConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String reader) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{ReadersDAO}", ReadersDAO.class);

        int iend = reader.indexOf('.');
        String idSubstring;
        if (iend != -1) {
            idSubstring= reader.substring(0 , iend);
            ReadersDAO readersDAO = (ReadersDAO) vex.getValue(ctx.getELContext());
            return readersDAO.find(Integer.valueOf(idSubstring));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object reader) {
        if (reader == null) {
            return "-";
        }
        Reader a = (Reader)reader;
        return a.getId() + ". " + a.getFirstName() + " " + a.getLastName();
    }

}