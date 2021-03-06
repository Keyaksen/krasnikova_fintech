package ru.fintech.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubInfo {
    private String id;
    private String instrumentId;
    private Long priceAlert;
    private String status;
    private String secName;
    private String ticker;
    private String secType;
    private String createdAt;
}
