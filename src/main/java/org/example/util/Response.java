package org.example.util;

import org.example.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Response {
  private Entity<?> a, b;
  private Vector2D overLapN, overLapV;
  private Boolean alnB, blnA;
}
