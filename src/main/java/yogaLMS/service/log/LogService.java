package yogaLMS.service.log;

import yogaLMS.dto.log.Log;

import java.util.List;

public interface LogService {

    public Log create(Log log);

    public Log read(Long id);

    public void update(Log log);

    public void delete(Log log);

    public List<Log> retrieveAll();
}
