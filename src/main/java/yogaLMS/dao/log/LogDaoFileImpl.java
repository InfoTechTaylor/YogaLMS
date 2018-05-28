package yogaLMS.dao.log;

import yogaLMS.dao.GenericDaoFileImpl;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.program.Program;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LogDaoFileImpl extends GenericDaoFileImpl<Log> implements LogDao {

    private Map<Long, Log> map = super.getMap();
    private String filename;
    private final String STRING_DELIMITER = "::";

    public LogDaoFileImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void loadEntities() throws YogaLMSPersistenceException {
        // load log information from file to Map
        Scanner scanner = null;

        try {
            // create Scanner object for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(this.filename)));

            // currentLine holds the most recent line read from the file
            String currentLine;

            // currentToken holds each of the parts of the currentLine after it has been split on our STRING_DELIMITER
            String[] currentTokens;

            // Go through each line in FILE, decoding each line into a object
            // process while we have more lines in the file
            if(scanner != null){
                while(scanner.hasNextLine()) {
                    // get the next line in the file
                    currentLine = scanner.nextLine();
                    //break up the line into tokens
                    currentTokens = currentLine.split(STRING_DELIMITER);
                    // create a new  object and put it into the map
                    Log currentLog = new Log();
                    // set the remaining items on currentLog
                    // file setup id::programID::startDate::endDate
                    currentLog.setId(Long.parseLong(currentTokens[0]));
                    //lazy load program object
                    Program program = new Program();
                    program.setId(Long.parseLong(currentTokens[1]));
                    currentLog.setProgram(program);
                    currentLog.setStartDate(LocalDate.parse(currentTokens[2]));
                    if(currentTokens[3] != null) {
                        currentLog.setEndDate(LocalDate.parse(currentTokens[3]));
                    } else {
                        currentLog.setEndDate(null);
                    }

                    // put currentItem into the map
                    this.map.put(currentLog.getId(), currentLog);
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
            List<Log> logList = super.retrieveAll();
            for(Log currentLog : logList){
                // write the object to the file as a line
                // file setup id::programID::startDate::endDate
                out.println(currentLog.getId() + STRING_DELIMITER
                        + currentLog.getProgram().getId() + STRING_DELIMITER
                        + currentLog.getStartDate() + STRING_DELIMITER
                        + currentLog.getEndDate() + STRING_DELIMITER);

                // force PrinteWriter to write line to the file
                out.flush();
            }
            out.close();
        } catch (IOException | YogaLMSPersistenceException e) {
            throw new YogaLMSPersistenceException("Error saving data.");
        }
    }
}
