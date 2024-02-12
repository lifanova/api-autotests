package ru.alfabank.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserData {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

}
