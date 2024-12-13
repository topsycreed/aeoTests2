package testData;

import models.User;

public class UserConstants {
    public static final String DEFAULT_USER = """
                {
                  "id": 0,
                  "username": "username",
                  "firstName": "firstName",
                  "lastName": "lastName",
                  "email": "email@gmail.com",
                  "password": "123qwerty",
                  "phone": "+7(919)321456",
                  "userStatus": 0
                }""";
    public static final User DEFAULT_USER2 = new User("username", "Gena", "lastName",
            "gena@gmail.com", "123qwerty", "+7(919)321456", 0);
}
