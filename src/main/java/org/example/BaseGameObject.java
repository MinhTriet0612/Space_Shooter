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

  public boolean isReady() {
    return this.isAlreadyReady() || (this.getWorld() != null && this.getStatus() != null);
  }

  public void beforeUpdate(float deltaTime) {
    if (this.isReady()) {
      this.setAlreadyReady(true);
    }
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
}
