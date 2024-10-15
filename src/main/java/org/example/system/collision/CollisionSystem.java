package org.example.system.collision;

import org.example.entity.Entity;
import org.example.rigid.Rigid;
import org.example.stats.SystemStats;
import org.example.system.GameSystem;
import org.example.util.Response;
import org.example.util.SAT;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class CollisionSystem extends GameSystem<SystemStats> {
  private HashMap<UUID, Response<Entity<?>>> oldCollisionMap = new HashMap<>();
  private HashMap<UUID, Response<Entity<?>>> newCollisionMap = new HashMap<>();
  private int loopCount = 0;

  public static UUID getCollisionKey(Entity<?> entity1, Entity<?> entity2) {
    return new UUID(entity1.getUuid().getMostSignificantBits() ^ entity2.getUuid().getMostSignificantBits(),
        entity1.getUuid().getLeastSignificantBits() ^ entity2.getUuid().getLeastSignificantBits());
  }

  @Override
  public void update(float deltaTime) {
    this.setLoopCount(0);
    this.newCollisionMap = new HashMap<>();

    for (int i = 0; i < this.getWorld().getEntities().size(); i++) {
      for (int j = i + 1; j < this.getWorld().getEntities().size(); j++) {
        this.setLoopCount(loopCount + 1);
        Entity<?> entity1 = this.getWorld().getEntities().get(i);
        Entity<?> entity2 = this.getWorld().getEntities().get(j);

        if (entity1.isCollidable() && entity2.isCollidable()) {
          Rigid r1 = entity1.getRigid();
          Rigid r2 = entity2.getRigid();

          if (r1 == null || r2 == null) {
            continue;
          }

          Response<Rigid> responseRigid = SAT.testCollide(r1, r2);
          Response<Entity<?>> responseEntity = new Response<Entity<?>>(entity1, entity2, responseRigid);
          if (responseRigid.isColliding()) {
            UUID collisionKey = CollisionSystem.getCollisionKey(entity1, entity2);
            this.newCollisionMap.put(collisionKey, responseEntity);
            if (this.oldCollisionMap.containsKey(collisionKey)) {
              entity1.onCollisionStay(entity2, responseEntity);
              entity2.onCollisionStay(entity1, responseEntity);
            } else {
              entity1.onCollisionEnter(entity2, responseEntity);
              entity2.onCollisionEnter(entity1, responseEntity);
            }
          }
        }
      }
    }

    for (UUID key : this.oldCollisionMap.keySet()) {
      this.setLoopCount(loopCount + 1);
      Entity<?> entity1 = this.oldCollisionMap.get(key).getA();
      Entity<?> entity2 = this.oldCollisionMap.get(key).getB();
      if (!this.newCollisionMap.containsKey(key)) {
        entity1.onCollisionExit(entity2, this.oldCollisionMap.get(key));
        entity2.onCollisionExit(entity1, this.oldCollisionMap.get(key));
      }
    }

    this.oldCollisionMap = this.newCollisionMap;
  }
}
