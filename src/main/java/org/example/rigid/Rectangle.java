package org.example.rigid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Rectangle extends Rigid {
  private int width, height;
}
