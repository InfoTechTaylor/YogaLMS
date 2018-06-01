package yogaLMS.dao.yogaclass;

import yogaLMS.dao.GenericDaoFileImpl;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.yogaclass.YogaClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YogaClassDaoObjectStreamImpl extends GenericDaoFileImpl<YogaClass> implements YogaClassDao {

    private Map<Long, YogaClass> map = super.getMap();
    private String filename;
    private final String STRING_DELIMITER = "::";

    public YogaClassDaoObjectStreamImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public List<YogaClass> retrieveAllByStudio(String studio) throws YogaLMSPersistenceException {
        this.loadEntities();
        List<YogaClass> allYogaClasses = super.retrieveAll();
        List<YogaClass> filteredYogaClasses = new ArrayList<>();

        for(YogaClass currentClass : allYogaClasses){
            if (currentClass.getStudioName().equals(studio)){
                filteredYogaClasses.add(currentClass);
            }
        }

        return filteredYogaClasses;
    }

    @Override
    public void loadEntities() throws YogaLMSPersistenceException {
        try(ObjectInputStream infile = new ObjectInputStream(new FileInputStream(filename))){
            while(true){
                YogaClass currentYogaClass = (YogaClass)infile.readObject();
                map.put(currentYogaClass.getId(), currentYogaClass);
            }
        } catch (EOFException e) {
            // do nothing
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new YogaLMSPersistenceException("Error loading data.");
        }
    }

    @Override
    public void writeEntities() throws YogaLMSPersistenceException {
        try(ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream(filename))) {
            for(YogaClass currentYogaClass : map.values()) {
                outfile.writeObject(currentYogaClass);
            }
        } catch (EOFException e) {
            // do nothing
        } catch (IOException e) {
            e.printStackTrace();
            throw new YogaLMSPersistenceException("Error retrieving data.");
        }
    }
}
