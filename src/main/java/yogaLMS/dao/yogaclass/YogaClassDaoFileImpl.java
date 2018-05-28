package yogaLMS.dao.yogaclass;

import yogaLMS.dao.GenericDaoFileImpl;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.yogaclass.YogaClass;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class YogaClassDaoFileImpl extends GenericDaoFileImpl<YogaClass> implements YogaClassDao {

    private Map<Long, YogaClass> map = super.getMap();
    private String filename;
    private final String STRING_DELIMITER = "::";

    public YogaClassDaoFileImpl(String filename) {
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
        // load Yoga class information from file to Map
        Scanner scanner = null;

        try {
            // create Scanner object for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(this.filename)));

            // currentLine holds the most recent line read from the file
            String currentLine;

            // currentToken holds each of the parts of the currentLine after it has been split on our STRING_DELIMITER
            String[] currentTokens;

            // Go through each line in FILE, decoding each line into a YogaClass object
            // process while we have more lines in the file
            if(scanner != null){
                while(scanner.hasNextLine()) {
                    // get the next line in the file
                    currentLine = scanner.nextLine();
                    //break up the line into tokens
                    currentTokens = currentLine.split(STRING_DELIMITER);
                    // create a new LogClass object and put it into the map LogClassMap
                    YogaClass currentYogaClass = new YogaClass();
                    // set the remaining items on currentYogaClass
                    // file setup id::date::className::teacherName::studioName:numHours
                    currentYogaClass.setId(Long.parseLong(currentTokens[0]));
                    currentYogaClass.setDate(LocalDate.parse(currentTokens[1]));
                    currentYogaClass.setClassName(currentTokens[2]);
                    currentYogaClass.setTeacherName(currentTokens[3]);
                    currentYogaClass.setStudioName(currentTokens[4]);
                    currentYogaClass.setHours(Double.parseDouble(currentTokens[5]));

                    // put currentItem into the map
                    this.map.put(currentYogaClass.getId(), currentYogaClass);
                }
                scanner.close();
            }
        } catch (IOException e) {
            throw new YogaLMSPersistenceException("Error retrieving data.");
        }
    }

    @Override
    public void writeEntities() throws YogaLMSPersistenceException {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(this.filename));
            // write out the objects to the file
            List<YogaClass> yogaClassList = super.retrieveAll();
            for(YogaClass currentClass : yogaClassList){
                // write the object to the file as a line
                // file setup id::date::className::teacherName::studioName:numHours
                out.println(currentClass.getId() + STRING_DELIMITER
                        + currentClass.getDate() + STRING_DELIMITER
                        + currentClass.getClassName() + STRING_DELIMITER
                        + currentClass.getTeacherName() + STRING_DELIMITER
                        + currentClass.getStudioName() + STRING_DELIMITER
                        + currentClass.getHours());

                // force PrinteWriter to write line to the file
                out.flush();
            }
            out.close();
        } catch (IOException | YogaLMSPersistenceException e) {
            throw new YogaLMSPersistenceException("Error saving data.");
        }
    }
}
