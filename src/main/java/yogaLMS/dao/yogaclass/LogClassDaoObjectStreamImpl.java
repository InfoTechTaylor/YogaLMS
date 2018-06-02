package yogaLMS.dao.yogaclass;

import yogaLMS.dao.GenericDaoFileImpl;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.yogaclass.LogClass;

import java.io.*;
import java.util.Map;

public class LogClassDaoObjectStreamImpl extends GenericDaoFileImpl<LogClass> implements LogClassDao {

    private Map<Long, LogClass> map = super.getMap();
    private String filename;
    private final String STRING_DELIMITER = "::";

    public LogClassDaoObjectStreamImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void loadEntities() throws YogaLMSPersistenceException {
        try(ObjectInputStream infile = new ObjectInputStream(new FileInputStream(filename))){
            while(true){
                LogClass currentLogClass = (LogClass)infile.readObject();
                map.put(currentLogClass.getId(), currentLogClass);
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
            for(LogClass currentLogClass : map.values()) {
                outfile.writeObject(currentLogClass);
            }
        } catch (EOFException e) {
            // do nothing
        } catch (IOException e) {
            e.printStackTrace();
            throw new YogaLMSPersistenceException("Error retrieving data.");
        }
    }
}
