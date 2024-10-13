package org.example;

import java.awt.Graphics;

import org.example.stats.Stats;
import org.example.system.status.Status;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseGameObject<S extends Stats> extends BaseObject {
  private World world;
  private Status<S> status;
  private boolean isAlreadyReady = false;

  protected BaseGameObject() {
  }

  public final void checkReady() {
    if (!this.isAlreadyReady() && this.isReady()) {
      this.onReady();
      this.setAlreadyReady(true);
    }
  }

  public boolean isReady() {
    return this.isAlreadyReady() || (this.getWorld() != null && this.getStatus() != null);
  }

  public void onReady() {
  }

  public void setWorld(World world) {
    this.world = world;
    this.checkReady();
  }

  public void setStatus(Status<S> status) {
    this.status = status;
    this.checkReady();
  }

  public void beforeUpdate(float deltaTime) {
  }

  public void update(float deltaTime) {
  }

  public void afterUpdate(float deltaTime) {
  }

  public void beforeRender(Graphics g) {
  }

  public void render(Graphics g) {
  }

  public void afterRender(Graphics g) {
  }

  public S getCurrentStats() {
    return this.getStatus().getCurrentStats();
  }

  public S getInitStats() {
    return this.getStatus().getInitStats();
  }
}
