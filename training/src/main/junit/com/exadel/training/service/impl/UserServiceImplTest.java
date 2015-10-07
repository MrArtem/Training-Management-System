package com.exadel.training.service.impl;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.UserService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final long TEST_EXPECTED_ID = 1;
    private static final long TEST_EXPECTED_ID_EXCEPTION = 0;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserDAO userDAO;

    @Mock
    private User testUser = new User();

    @Test
    public void testGetUserById() throws Exception {
        User testUser = new User();
        testUser.setId(TEST_EXPECTED_ID);

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID)).thenReturn(testUser);

        long actual = userService.getUserById(TEST_EXPECTED_ID).getId();

        Assert.assertEquals(TEST_EXPECTED_ID, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public  void testGetUserByIdException() throws Exception {
        testUser.setId(TEST_EXPECTED_ID_EXCEPTION);

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID_EXCEPTION)).thenReturn(testUser);

        long actual = userService.getUserById(TEST_EXPECTED_ID_EXCEPTION).getId();
    }

    @Test
    public void testGetListenerTrainingListOfUser() throws Exception {
        List<Training> trainingListTest = new ArrayList<Training>();

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID)).thenReturn(testUser);
        Mockito.when(testUser.getTrainingsListener()).thenReturn(trainingListTest);

        List<Training> actual = userService.getListenerTrainingListOfUser(TEST_EXPECTED_ID);
        Assert.assertEquals(trainingListTest, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetListenerTrainingListOfUserException() throws Exception {
        List<Training> trainingListTest = new ArrayList<Training>();

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID_EXCEPTION)).thenReturn(testUser);
        Mockito.when(testUser.getTrainingsListener()).thenReturn(trainingListTest);

        List<Training> actual = userService.getListenerTrainingListOfUser(TEST_EXPECTED_ID_EXCEPTION);
    }

    @Test
    public void testGetCoachTrainingListOfUser() throws Exception {
        List<Training> trainingListTest = new ArrayList<Training>();

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID)).thenReturn(testUser);
        Mockito.when(testUser.getTrainingsCoach()).thenReturn(trainingListTest);

        List<Training> actual = userService.getListenerTrainingListOfUser(TEST_EXPECTED_ID);
        Assert.assertEquals(trainingListTest, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetListenerCoachListOfUserException() throws Exception {
        List<Training> trainingListTest = new ArrayList<Training>();

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID_EXCEPTION)).thenReturn(testUser);
        Mockito.when(testUser.getTrainingsListener()).thenReturn(trainingListTest);

        List<Training> actual = userService.getListenerTrainingListOfUser(TEST_EXPECTED_ID_EXCEPTION);
    }

    @Test
    public void testIsCoach() throws Exception {
        Mockito.when(userDAO.isCoach(TEST_EXPECTED_ID, TEST_EXPECTED_ID)).thenReturn(true);
        Mockito.when(userDAO.isCoach(TEST_EXPECTED_ID, TEST_EXPECTED_ID + 1)).thenReturn(false);

        Boolean actual1 = userService.isCoach(TEST_EXPECTED_ID, TEST_EXPECTED_ID);
        Boolean actual2 = userService.isCoach(TEST_EXPECTED_ID, TEST_EXPECTED_ID + 1);

        Assert.assertEquals(new Boolean(true), actual1);
        Assert.assertEquals(new Boolean(false), actual2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsCoachException() throws Exception {
        Mockito.when(userDAO.isCoach(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID)).thenReturn(false);
        Mockito.when(userDAO.isCoach(TEST_EXPECTED_ID, TEST_EXPECTED_ID_EXCEPTION)).thenReturn(false);
        Mockito.when(userDAO.isCoach(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID_EXCEPTION)).thenReturn(false);

        Boolean actual1 = userService.isCoach(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID);
        Boolean actual2 = userService.isCoach(TEST_EXPECTED_ID, TEST_EXPECTED_ID_EXCEPTION);
        Boolean actual3 = userService.isCoach(TEST_EXPECTED_ID_EXCEPTION, TEST_EXPECTED_ID_EXCEPTION);
    }
}