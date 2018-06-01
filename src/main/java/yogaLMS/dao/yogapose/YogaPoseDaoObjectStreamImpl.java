package yogaLMS.dao.yogapose;

import yogaLMS.dao.GenericDaoFileImpl;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.yogapose.YogaPose;

import java.io.*;
import java.util.Map;

public class YogaPoseDaoObjectStreamImpl extends GenericDaoFileImpl<YogaPose> implements YogaPoseDao {

    private Map<Long, YogaPose> map = super.getMap();
    private String filename;
    private final String STRING_DELIMITER = "::";

    public YogaPoseDaoObjectStreamImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void loadEntities() throws YogaLMSPersistenceException {
        try(ObjectInputStream infile = new ObjectInputStream(new FileInputStream(filename))){
            while(true){
                YogaPose currentYogaPose = (YogaPose)infile.readObject();
                map.put(currentYogaPose.getId(), currentYogaPose);
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
            for(YogaPose currentYogaPose : map.values()) {
                outfile.writeObject(currentYogaPose);
            }
        } catch (EOFException e) {
            // do nothing
        } catch (IOException e) {
            e.printStackTrace();
            throw new YogaLMSPersistenceException("Error retrieving data.");
        }
    }
}
