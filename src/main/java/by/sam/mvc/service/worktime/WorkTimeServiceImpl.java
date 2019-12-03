package by.sam.mvc.service.worktime;

import by.sam.mvc.models.worktime.WeekDay;
import by.sam.mvc.models.worktime.WorkTime;
import by.sam.mvc.repository.worktime.WorkTimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    private final String DIGIT_REGEX = "\\d";
    private final String TIME_START_REGEX = "timeStart\\d";
    private final String TIME_EDN_REGEX = "timeEnd\\d";

    private final WorkTimeRepository workTimeRepository;

    public WorkTimeServiceImpl(WorkTimeRepository workTimeRepository) {
        this.workTimeRepository = workTimeRepository;
    }

    @Transactional
    @Override
    public void create(WorkTime time) {
        workTimeRepository.create(time);
    }
    @Transactional
    @Override
    public WorkTime read(int id) {
        return workTimeRepository.read(id);
    }
    @Transactional
    @Override
    public void update(WorkTime time) {
        WorkTime updateWorkTime = workTimeRepository.read(time.getId());
        updateWorkTime.setDay(time.getDay());
        updateWorkTime.setStartTime(time.getStartTime());
        updateWorkTime.setEndTime(time.getEndTime());
        workTimeRepository.update(time);
    }
    @Transactional
    @Override
    public void delete(int id) {
        workTimeRepository.delete(id);
    }





    public List<WorkTime> createWorkTimeListFromParams(Map<String, String> params){
        List<WorkTime> workTimeList = new ArrayList<>();

        params.forEach((k, v) -> {
            if (k.matches(DIGIT_REGEX)) {
                workTimeList.add(new WorkTime(DayOfWeek.valueOf(v.toUpperCase())));
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
