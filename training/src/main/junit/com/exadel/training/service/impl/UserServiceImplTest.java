package com.exadel.training.service.impl;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.UserService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Created by ayudovin on 06.10.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final long TEST_EXPECTED_ID = 1;
    private static final long TEST_EXPECTED_ID_Exception = 0;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserDAO userDAO;

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
        User testUser = new User();
        testUser.setId(TEST_EXPECTED_ID_Exception);

        Mockito.when(userDAO.getUserByID(TEST_EXPECTED_ID_Exception)).thenReturn(testUser);

        long actual = userService.getUserById(TEST_EXPECTED_ID_Exception).getId();
    }
}