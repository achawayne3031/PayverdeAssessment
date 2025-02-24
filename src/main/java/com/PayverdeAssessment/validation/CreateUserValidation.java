package com.PayverdeAssessment.validation;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserValidation {

    private int id;

    @NotNull(message = "full name is required")
    @NotEmpty(message = "full name not be empty")
    private String fullName;

    @NotNull(message = "email is required")
    @NotEmpty(message = "email not be empty")
    @Email(message = "email: Invalid email address")
    private String email;


    @Override
    public String toString() {
        return "CreateUserValidation{" +
                ", firstName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
