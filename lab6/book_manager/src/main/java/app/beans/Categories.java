package app.beans;

import app.dao.CategoriesDAO;
import app.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("categories")
@ApplicationScoped
public class Categories implements Serializable {

    @Inject
    private CategoriesDAO categoriesDAO;

    public List<Category> getCategoriesList() { return categoriesDAO.getAll(); }

    public List<Integer> getCategoriesIds() {
        return getCategoriesList().stream().map(c -> c.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void deleteCategory() {
        try {
            for ( Category b: getSelectedCategories() )
                categoriesDAO.remove(b);
        } catch (Exception e) {

        }
    }

    private Map<Integer, Boolean> checkedCategories = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getCheckedCategories() {
        return checkedCategories;
    }

    public List<Category> getSelectedCategories() {
        List<Category> selectedCategories = new ArrayList<>();
        for(Category category : getCategoriesList())
            if(checkedCategories.containsKey(category.getId()) && checkedCategories.get(category.getId()))
                selectedCategories.add(category);

        return selectedCategories;
    }
    private Category addCategory = new Category();

    private Category editCategory;

    public Category getEditCategory() {
        return editCategory;
    }
    public void setEditCategory(Category editCategory) {
        this.editCategory = editCategory;
    }

    public Integer getEditCategoryId() {
        return editCategoryId;
    }

    public void setEditCategoryId(Integer editCategoryId) {
        if ( editCategoryId != null ) {
            editCategory = categoriesDAO.find(editCategoryId);
        } else {
            editCategory = null;
        }

        this.editCategoryId = editCategoryId;
    }

    private Integer editCategoryId;

    public Category getAddCategory() {
        return addCategory;
    }

    public void insertCategory() {
        if ( this.addCategory == null ) {
            System.err.println( "Category to insert is null");
        } else {


            categoriesDAO.add(this.addCategory);
            this.addCategory = new Category();
        }
    }

    public void setAddCategory(Category addCategory) {
        this.addCategory = addCategory;
    }

    public boolean isInEditMode() {
        return editCategoryId != null;
    }

    public void editCategoryInDAO() {
        categoriesDAO.edit(this.editCategory);
        this.editCategory = null;
        this.editCategoryId = null;
    }

}
