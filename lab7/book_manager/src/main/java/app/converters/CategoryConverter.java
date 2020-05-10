package app.converters;

import app.dao.CategoriesDAO;
import app.model.Category;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String authorId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{CategoriesDAO}", CategoriesDAO.class);

        int iend = authorId.indexOf('.');
        String idSubstring;
        if (iend != -1) {
            idSubstring= authorId.substring(0 , iend);
            CategoriesDAO categoriesDAO = (CategoriesDAO) vex.getValue(ctx.getELContext());
            return categoriesDAO.find(Integer.valueOf(idSubstring));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object category) {
        if (category == null) {
            return "-";
        }
        Category c = (Category)category;
        return c.getId() + ". " + c.getName();
    }

}