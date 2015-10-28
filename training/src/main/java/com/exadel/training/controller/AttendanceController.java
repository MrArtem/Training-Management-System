package com.exadel.training.controller;

import com.exadel.training.controller.model.attendanceModels.AttendanceModel;
import com.exadel.training.dao.domain.Attendance;
import com.exadel.training.service.AttendanceService;
import com.exadel.training.validate.annotation.LegalID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ayudovin on 27.10.2015.
 */
@RestController
@RequestMapping("/attendance_controller")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @RequestMapping(value = "/all_attendance/{idLesson}", method = RequestMethod.GET)
    @LegalID
    public List<AttendanceModel> getAllAttendanceByLessonID(@PathVariable("idLesson") long idLesson) {
        List<AttendanceModel> attendanceModelList = new ArrayList<AttendanceModel>();

        for(Attendance attendance : attendanceService.getAllAttendanceByLessonID(idLesson)) {
            attendanceModelList.add(new AttendanceModel(attendance));
        }

        return attendanceModelList;
    }

    @RequestMapping(value = "/update_attendace", method = RequestMethod.POST, consumes = "application/json")
    public void updateAttendance(@RequestBody AttendanceModel attendanceModel) {
        attendanceService.updateAttendance(attendanceModel);
    }
}
