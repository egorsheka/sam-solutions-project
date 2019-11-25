package by.sam.mvc.service.worktime;

import by.sam.mvc.models.WorkTime;
import by.sam.mvc.repository.worktime.WorkTimeRepository;
import by.sam.mvc.service.Service;

import java.util.List;
import java.util.Map;

public interface WorkTimeService extends Service<WorkTime> {

    public List<WorkTime> createWorkTimeListFromParams(Map<String, String> params);
}
