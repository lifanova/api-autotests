package ru.alfabank.data;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserData {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private String updatedAt;

}
