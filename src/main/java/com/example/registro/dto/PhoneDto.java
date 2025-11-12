package com.example.registro.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PhoneDto {
    @NotNull private Integer number;
    @NotNull private Integer citycode;
    @NotNull private Integer contrycode;

}
