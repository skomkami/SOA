package app.beans;

import app.dao.GenericEntityDAO;
import app.model.Category;
import app.model.IdentifiableEntity;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GenericBean<T extends IdentifiableEntity> implements Serializable {

    private T createContents() {
        try {
            return dao.getPersistentClass().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    @PostConstruct
    private void initAddEntity() {
        addEntity = createContents();
    }

    @Inject
    GenericEntityDAO<T> dao;

    public List<T> getListOfAll() { return dao.getAll(); }

    public List<Integer> getEntitiesIds() {
        return getListOfAll().stream().map(e -> e.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void delete() {
        try {
            for ( T b: getSelectedList() )
                dao.remove(b);
        } catch (Exception e) {

        }
    }

    private Map<Integer, Boolean> checkedMap = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getCheckedMap() {
        return checkedMap;
    }

    public List<T> getSelectedList() {
        List<T> selectedList = new ArrayList<>();
        for(T entity : getListOfAll())
            if(checkedMap.containsKey(entity.getId()) && checkedMap.get(entity.getId()))
                selectedList.add(entity);

        return selectedList;
    }

    protected T addEntity;

    protected T editEntity;

    public T getEditEntity() {
        return editEntity;
    }
    public void setEditEntity(T editEntity) {
        this.editEntity = editEntity;
    }

    public Integer getEditEntityId() {
        return editEntityId;
    }

    public void setEditEntityId(Integer editEntityId) {
        if ( editEntityId != null ) {
            editEntity = dao.find(editEntityId);
        } else {
            editEntity = null;
        }

        this.editEntityId = editEntityId;
    }

    private Integer editEntityId;

    public T getAddEntity() {
        return addEntity;
    }

    public void insertEntity() {
        if ( this.addEntity == null ) {
            System.err.println( "Category to insert is null");
        } else {
            dao.add(this.addEntity);
            this.addEntity = createContents();
        }
    }

    public void setAddEntity(T editEntity) {
        this.editEntity = editEntity;
    }

    public boolean isInEditMode() {
        return editEntityId != null;
    }

    public void editEntityInDAO() {
        dao.edit(this.editEntity);
        this.editEntity = null;
        this.editEntityId = null;
    }

}
