package org.example.rigid;

import lombok.Getter;
import lombok.Setter;

import lombok.AllArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
public class Circle extends Rigid {
  private int radius;
}
