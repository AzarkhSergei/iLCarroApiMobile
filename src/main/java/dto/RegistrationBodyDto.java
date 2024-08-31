package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationBodyDto {

  private String username;
  private String password;
  private String pattern;
  private String firstName;
  private String lastName;

}
