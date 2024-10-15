package org.example.entity;

import java.awt.Graphics;

import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.MeteoriteStats;
import org.example.system.status.Status;
import org.example.util.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meteorite extends Entity<MeteoriteStats> {
  private Status<MeteoriteStats> status = new Status<>(new MeteoriteStats());

  protected Meteorite() {
  }

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.getStatus().getInitStats().getRadius());
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    // throw new UnsupportedOperationException("Unimplemented method 'render'");
  }

  @Override
  public void update(float deltaTime) {
    // throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void onCollisionStay(Entity<?> other, Response<Entity<?>> response) {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'onCollisionStay'");
  }

  @Override
  public void onCollisionExit(Entity<?> other, Response<Entity<?>> response) {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'onCollisionExit'");
  }

  @Override
  public void onCollisionEnter(Entity<?> other, Response<Entity<?>> response) {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'onCollisionEnter'");
  }
}
