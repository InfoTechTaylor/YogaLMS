package yogaLMS.dao.yogaclass;

import yogaLMS.dto.yogaclass.LogClass;

import java.util.List;

public interface LogClassDao {

    public LogClass create(LogClass logClass);

    public LogClass read(Long id);

    public void update(LogClass logClass);

    public void delete(LogClass logClass);

    public List<LogClass> retrieveAll();
}
