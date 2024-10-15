package org.example.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {
  private T a, b;
  private Vector2D overLapN, overLapV;
  private boolean alnB, blnA;
  private boolean isColliding;

  public Response() {
  }

  public Response(T a, T b, Vector2D overLapN, Vector2D overLapV, boolean alnB, boolean blnA, boolean isColliding) {
    this.a = a;
    this.b = b;
    this.overLapN = overLapN;
    this.overLapV = overLapV;
    this.alnB = alnB;
    this.blnA = blnA;
    this.isColliding = isColliding;
  }

  public Response(T a, T b, Response<?> response) {
    this.a = a;
    this.b = b;
    this.overLapN = response.getOverLapN();
    this.overLapV = response.getOverLapV();
    this.alnB = response.isAlnB();
    this.blnA = response.isBlnA();
    this.isColliding = response.isColliding();
  }
}
