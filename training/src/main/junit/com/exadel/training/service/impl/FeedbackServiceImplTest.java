package com.exadel.training.service.impl;

import com.exadel.training.dao.FeedbackDAO;
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
    private static final long TEST_EXPECTED_ID_EXCEPTION = 0;

    @InjectMocks
    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @Mock
    private UserDAO userDAO;
    @Mock
    private FeedbackDAO feedbackDAO;

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

    @Test(expected = IllegalArgumentException.class)
    public void testGetFeedbackListForUserException() throws Exception {
        List<Feedback> feedbackList = new ArrayList<Feedback>();
        Feedback feedback = new Feedback();
        feedback.setId(TEST_EXPECTED_ID_EXCEPTION);
        feedbackList.add(feedback);

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID_EXCEPTION)).thenReturn(testUser);
        Mockito.when(testUser.getFeedbackList()).thenReturn(feedbackList);

        List<Feedback> feedbackListActual = feedbackService.getFeedbackListForUser(TEST_EXPECTED_ID_EXCEPTION);
    }

    @Test
    public void testGetFeedbackListFromTrainingForUser() throws Exception {
        List<Feedback> feedbackList = new ArrayList<Feedback>();
        Feedback feedback = new Feedback();
        feedback.setId(TEST_EXPECTED_ID);
        feedbackList.add(feedback);

        Mockito.when(feedbackDAO.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID, TEST_EXPECTED_ID)).thenReturn(feedbackList);

        List<Feedback> actual = feedbackService.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID, TEST_EXPECTED_ID);

        Assert.assertEquals(feedback.getId(), actual.get(0).getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFeedbackListFromTrainingForUserException() throws Exception {
        List<Feedback> feedbackList = new ArrayList<Feedback>();
        Feedback feedback = new Feedback();
        feedback.setId(TEST_EXPECTED_ID);
        feedbackList.add(feedback);

        Mockito.when(feedbackDAO.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID)).thenReturn(feedbackList);
        Mockito.when(feedbackDAO.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID, TEST_EXPECTED_ID_EXCEPTION)).thenReturn(feedbackList);
        Mockito.when(feedbackDAO.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID_EXCEPTION)).thenReturn(feedbackList);

        feedbackService.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID);
        feedbackService.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID, TEST_EXPECTED_ID_EXCEPTION);
        feedbackService.getFeedbackListFromTrainingForUser(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID_EXCEPTION);
    }
}