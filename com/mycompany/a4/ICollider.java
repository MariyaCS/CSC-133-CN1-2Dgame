package com.mycompany.a4;

public interface ICollider {
	boolean collidesWith(ICollider otherObject);
	void handleCollision(ICollider otherObject);
}
