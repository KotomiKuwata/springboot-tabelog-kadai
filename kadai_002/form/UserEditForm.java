package com.example.kadai_002.form;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEditForm {
    @NotNull
    private Integer id;
    
    @NotBlank(message = "氏名を入力してください。")
    private String name;
    
    @NotBlank(message = "フリガナを入力してください。")
    private String furigana;
    
    @NotNull(message = "生年月日を入力してください。")
    private Date dateOfBirth;
    
    @NotBlank(message = "電話番号を入力してください。")
    private String phoneNumber;
    
    @NotBlank(message = "メールアドレスを入力してください。")
    private String email;
}