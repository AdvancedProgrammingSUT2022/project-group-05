package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestUser {

    User user;


    @BeforeEach
    public void setup() {
        user = new User("mohammadreza", "smkh", "1234", null, 0);
    }

    @Test
    public void testSetUsername() {
        user.setUsername("sam");
        String result = user.getUsername();
        Assertions.assertEquals("sam", result);
    }

    @Test
    public void testSetNickname() {
        user.setNickname("jose");
        String result = user.getNickname();
        Assertions.assertEquals("jose", result);
    }

    @Test
    public void testSetPassword() {
        user.setPassword("4321");
        String result = user.getPassword();
        Assertions.assertEquals("4321", result);
    }

    @Test
    public void testSetScore() {
        user.setScore(10000);
        int result = user.getScore();
        Assertions.assertEquals(10000, result);
    }





}
