package ru.geekbrains.SpringMarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {

    private String userName;
    private String token;

}
