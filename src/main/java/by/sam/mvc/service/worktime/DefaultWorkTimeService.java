package by.sam.mvc.service.worktime;

import by.sam.mvc.models.WeekDay;
import by.sam.mvc.models.WorkTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class DefaultWorkTimeService implements WorkTimeService {

    private final String DIGIT_REGEX = "\\d";
    private final String TIME_START_REGEX = "timeStart\\d";
    private final String TIME_EDN_REGEX = "timeEnd\\d";



    public List<WorkTime> createWorkTimeListFromParams(Map<String, String> params){
        List<WorkTime> workTimeList = new ArrayList<>();

        params.forEach((k, v) -> {
            if (k.matches(DIGIT_REGEX)) {
                workTimeList.add(new WorkTime(WeekDay.valueOf(v.toUpperCase())));
            }
        });

        Iterator<WorkTime> timeStartIterator = workTimeList.iterator();
        params.forEach((k, v) -> {
            if (k.matches(TIME_START_REGEX)) {
                timeStartIterator.next().setStartTime(v);

            }
        });

        Iterator<WorkTime> workTimeIterator = workTimeList.iterator();
        params.forEach((k, v) -> {
            if (k.matches(TIME_EDN_REGEX)) {
                workTimeIterator.next().setEndTime(v);
            }
        });
        return workTimeList;
    }



}
