package controller;


import model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestUserDatabaseController {

    UserDatabaseController userDatabaseController;


    @Mock
    User user;

    @BeforeEach
    public void setup() {
        userDatabaseController = new UserDatabaseController();
        Mockito.when(user.getUsername()).thenReturn("u");
        Mockito.when(user.getPassword()).thenReturn("p");
        Mockito.when(user.getNickname()).thenReturn("n");
        userDatabaseController.addUser(user);
    }

    @Test
    public void testGetUserByUsername() {
        User foundedUser = UserDatabaseController.getUserByUsername("u");
        boolean result = (foundedUser.getPassword().equals("pa"));
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetUserByNickname() {
        User foundedUser = UserDatabaseController.getUserByNickname("n");
        boolean result = (foundedUser.getPassword().equals("p"));
        Assertions.assertTrue(result);
    }

    @Test
    public void testChangeNickname() {
        userDatabaseController.changeNickname(user, "ni");
        User foundedUser = UserDatabaseController.getUserByUsername("u");
        boolean result = (foundedUser.getNickname().equals("ni"));
        Assertions.assertTrue(result);
    }

    @Test
    public void testChangePassword() {
        userDatabaseController.changePassword(user, "pa");
        User foundedUser = UserDatabaseController.getUserByUsername("u");
        boolean result = (foundedUser.getPassword().equals("pa"));
        Assertions.assertTrue(result);
    }




    @AfterEach
    public void after() {
        userDatabaseController.removeUser(user);
    }

}
