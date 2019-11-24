package by.sam.mvc.service.worktime;

import by.sam.mvc.models.WorkTime;

import java.util.List;
import java.util.Map;

public interface WorkTimeService {

    public List<WorkTime> createWorkTimeListFromParams(Map<String, String> params);
}
