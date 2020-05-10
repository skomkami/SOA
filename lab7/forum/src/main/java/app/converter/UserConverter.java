package app.converter;


import app.dao.UsersDAO;
import app.model.User;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String author) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{UsersDAO}", UsersDAO.class);

        int iend = author.indexOf('.');
        String idSubstring;
        if (iend != -1) {
            idSubstring= author.substring(0 , iend);
            UsersDAO usersDAO = (UsersDAO) vex.getValue(ctx.getELContext());
            return usersDAO.find(Integer.valueOf(idSubstring));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object user) {
        if (user == null) {
            return "-";
        }
        User u = (User)user;
        return u.getId() + ". " + u.getLogin();
    }

}
