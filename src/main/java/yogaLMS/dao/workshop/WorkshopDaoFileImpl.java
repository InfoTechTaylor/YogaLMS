package yogaLMS.dao.workshop;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.workshop.Workshop;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class WorkshopDaoFileImpl implements WorkshopDao {

    private Map<Long, Workshop> workshopMap = new HashMap<>();
    private Long currentHighestId = 0L;
    private String filename;
    private final String STRING_DELIMITER = "::";

    public WorkshopDaoFileImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public Workshop create(Workshop workshop) throws YogaLMSPersistenceException {
        workshop.setId(getNextId());
        workshopMap.put(workshop.getId(), workshop);
        writeWorkshops(); // write from Map to file
        return workshop;
    }

    private Long getNextId(){
        currentHighestId++;
        return currentHighestId;
    }

    @Override
    public Workshop read(Long id) throws YogaLMSPersistenceException {
        loadWorkshops(); // read from file into Map
        return workshopMap.get(id);
    }

    @Override
    public void update(Workshop workshop) throws YogaLMSPersistenceException {
        workshopMap.replace(workshop.getId(), workshop);
        writeWorkshops(); // write from Map to file
    }

    @Override
    public void delete(Workshop workshop) throws YogaLMSPersistenceException {
        workshopMap.remove(workshop.getId());
        writeWorkshops(); // write from Map to file
    }

    @Override
    public List<Workshop> retrieveAll() throws YogaLMSPersistenceException {
        loadWorkshops();
        return new ArrayList<>(workshopMap.values());
    }

    private void writeWorkshops() throws YogaLMSPersistenceException {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(filename));
            // write out the objects to the file
            List<Workshop> workshops = this.retrieveAll();
            for(Workshop currentWorkshop : workshops){
                // write the workshop object to the file as a line
                out.println(currentWorkshop.getId() + STRING_DELIMITER
                        + currentWorkshop.getDate() + STRING_DELIMITER
                        + currentWorkshop.getName() + STRING_DELIMITER
                        + currentWorkshop.getTeacherName() + STRING_DELIMITER
                        + currentWorkshop.getNumHours() + STRING_DELIMITER);
                out.flush(); // force PrinteWriter to write line to the file
            }
            out.close();
        } catch (IOException e) {
            throw new YogaLMSPersistenceException("Error saving data to storage.");
        }
    }

    private void loadWorkshops() throws YogaLMSPersistenceException {
        // load workshop information from file to Map
        Scanner scanner = null;
        try {
            // create Scanner object for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
            // currentLine holds the most recent line read from the file
            String currentLine;
            // currentToken holds each of the parts of the currentLine after it has been split on our STRING_DELIMITER
            String[] currentTokens;

            // Go through each line in filename, decoding each line into a workshop object
            if(scanner != null){
                while(scanner.hasNextLine()) {
                    // get the next line in the file
                    currentLine = scanner.nextLine();
                    //break up the line into tokens
                    currentTokens = currentLine.split(STRING_DELIMITER);
                    // create a new object and put it into the Map
                    Workshop currentWorkshop = new Workshop();
                    // set the remaining items on currentWorkshop
                    // file setup id::date::name::teacherName::numHours
                    currentWorkshop.setId(Long.parseLong(currentTokens[0]));
                    currentWorkshop.setDate(LocalDate.parse(currentTokens[1]));
                    currentWorkshop.setName(currentTokens[2]);
                    currentWorkshop.setTeacherName(currentTokens[3]);
                    currentWorkshop.setNumHours(Double.parseDouble(currentTokens[4]));
                    // put currentWorkshop into the map
                    workshopMap.put(currentWorkshop.getId(), currentWorkshop);
                }
                scanner.close();
            }
        } catch (IOException e) {
            throw new YogaLMSPersistenceException("Error reading data from storage.");
        }
    }
}
