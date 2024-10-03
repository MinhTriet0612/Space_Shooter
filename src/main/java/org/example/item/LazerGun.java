package org.example.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LazerGun extends Weapon {
    @Override
    public void fire() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fire'");
    }

    @Override
    public boolean setAuto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAuto'");
    }

    @Override
    public void update(float deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
