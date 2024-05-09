package com.sahabuddin.eshoppers.dto;

import com.sahabuddin.eshoppers.util.PasswordEqual;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.StringJoiner;
@PasswordEqual(
        first = "password",
        second = "passwordConfirmed",
        message = "password and confirm password do not match"
)
public class UserDTO {
    @NotEmpty
    @Size(min = 4, max = 32)
    private String username;
    @NotEmpty
    @Size(min = 8, max = 64)
    @Email
    private String email;
    @NotEmpty
    @Size(min = 6, max = 16)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 16)
    private String passwordConfirmed;
    @NotEmpty
    @Size(min = 4, max = 32)
    private String firstName;
    @NotEmpty
    @Size(min = 4, max = 32)
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",UserDTO.class.getSimpleName()+"{","}")
                .add("username='"+username+"'")
                .add("password='"+"*".repeat(password.length())+"'")
                .add("passwordConfirmed='"+"*".repeat(passwordConfirmed.length())+"'")
                .add("email='"+email+"'")
                .add("firstName='"+firstName+"'")
                .add("lastName='"+lastName+"'")
                .toString();
    }
}
