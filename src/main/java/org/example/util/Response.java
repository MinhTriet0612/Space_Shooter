package org.example.util;

import org.example.entities.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Response {
    private Entity a, b;
    private Vector2D overLapN, overLapV;
    private Boolean alnB, blnA;
}