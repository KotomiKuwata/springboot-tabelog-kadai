package com.example.kadai_002.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	
	@NotNull
    private Integer storeId;
	
	private Integer userId;
	
    @Min(1)
    @Max(5)
    private Integer rating;

    @Size(max = 255)
    private String comment;
}
