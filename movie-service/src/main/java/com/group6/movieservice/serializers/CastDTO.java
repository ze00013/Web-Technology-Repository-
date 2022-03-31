package com.group6.movieservice.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CastDTO {
    private String screenName;
    private String playedBy;
}
