package app.beans;

import app.dao.ReadersDAO;
import app.model.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("readers")
@ApplicationScoped
public class Readers implements Serializable {

    @Inject
    private ReadersDAO readersDAO;

    public void deleteReader() {
        try {
            for ( Reader b: getSelectedReaders() )
                readersDAO.removeReader(b);
        } catch (Exception e) {

        }
    }

    private Map<Integer, Boolean> checkedReaders = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getCheckedReaders() {
        return checkedReaders;
    }

    public List<Reader> getReadersList() {
        return readersDAO.getReaders();
    }

    public List<Integer> getReadersIds() {
        return getReadersList().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Reader> getSelectedReaders() {
        List<Reader> selectedReaders = new ArrayList<>();
        for(Reader reader : getReadersList())
            if(checkedReaders.containsKey(reader.getId()) && checkedReaders.get(reader.getId()))
                selectedReaders.add(reader);

        return selectedReaders;
    }
    private Reader addReader = new Reader();

    private Reader editReader;

    public Reader getEditReader() {
        return editReader;
    }
    public void setEditReader(Reader editReader) {
        this.editReader = editReader;
    }

    public Integer getEditReaderId() {
        return editReaderId;
    }

    public void setEditReaderId(Integer editReaderId) {
        if ( editReaderId != null ) {
            editReader = readersDAO.findReader(editReaderId);
        } else {
            editReader = null;
        }

        this.editReaderId = editReaderId;
    }

    private Integer editReaderId;

    public Reader getAddReader() {
        return addReader;
    }

    public void insertReader() {
        if ( this.addReader == null ) {
            System.err.println( "Reader to insert is null");
        } else {


            readersDAO.addReader(this.addReader);
            this.addReader = new Reader();
        }
    }

    public void setAddReader(Reader addReader) {
        this.addReader = addReader;
    }

    public boolean isInEditMode() {
        return editReaderId != null;
    }

    public void editReaderInDAO() {
        readersDAO.editReader(this.editReader);
        this.editReader = null;
        this.editReaderId = null;
    }
}
