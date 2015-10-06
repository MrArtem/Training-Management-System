package com.exadel.training.service.impl;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.FeedbackService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ayudovin on 06.10.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceImplTest {
    private static final long TEST_EXPECTED_ID = 1;

    @InjectMocks
    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @Mock
    private UserDAO userDAO;

    @Mock
    private User testUser = new User();

    @Test
    public void testGetFeedbackListForUser() throws Exception {

        List<Feedback> feedbackList = new ArrayList<Feedback>();
        Feedback feedback = new Feedback();
        feedback.setId(TEST_EXPECTED_ID);
        feedbackList.add(feedback);

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID)).thenReturn(testUser);
        Mockito.when(testUser.getFeedbackList()).thenReturn(feedbackList);

        List<Feedback> feedbackListActual = feedbackService.getFeedbackListForUser(TEST_EXPECTED_ID);
        Assert.assertEquals(feedbackList,feedbackListActual);
    }
}