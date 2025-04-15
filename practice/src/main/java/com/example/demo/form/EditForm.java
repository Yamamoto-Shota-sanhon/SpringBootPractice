package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EditForm implements Serializable {

    private Long id;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,11}$", message = "10桁または11桁の数字で入力してください")
    private String phone;

    @NotBlank
    @Pattern(regexp = "[0-9]{3}[-]{0,1}[0-9]{4}", message = "郵便番号が正しく入力されていません")
    private String zipCode;

    @NotBlank
    private String address;

    @NotBlank
    private String buildingName;

    @NotEmpty
    private String contactType;

    @NotBlank
    private String body;


    
}
