package org.example.entity;

import java.awt.Graphics;

import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.MeteoriteStats;
import org.example.util.Response;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Meteorite extends Entity<MeteoriteStats> {
  public Rigid getRigid() {
    return null;
  }

  @Override
  public void render(Graphics g) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'render'");
  }

  @Override
  public void update(float deltaTime) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void onCollisionStay(Entity other, Response response) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onCollisionStay'");
  }

  @Override
  public void onCollisionExit(Entity other, Response response) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onCollisionExit'");
  }

  @Override
  public void onCollisionEnter(Entity other, Response response) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onCollisionEnter'");
  }
}
