package by.sam.mvc.service.worktime;

import by.sam.mvc.models.WorkTime;
import by.sam.mvc.service.Service;

import java.util.List;
import java.util.Map;

public interface WorkTimeService extends Service<WorkTime> {

    List<WorkTime> createWorkTimeListFromParams(Map<String, String> params);


}
