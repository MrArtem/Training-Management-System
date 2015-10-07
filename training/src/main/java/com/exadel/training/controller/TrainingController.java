package com.exadel.training.controller;

import com.exadel.training.controller.model.GetTrainingModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {
    @RequestMapping(value = "/training/{id}", method = RequestMethod.GET)
    GetTrainingModel getTrainingMadel(@PathVariable("id") long id) {
        return new GetTrainingModel();
    }
}
