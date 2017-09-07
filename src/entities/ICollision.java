package entities;

import java.awt.*;

/**
 * Necessary methods for entities with collision.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public interface ICollision {
    /**
     * Check if collision has happened with object that has rectangle bounds.
     * @param bounds Rectangle bounds of an object that collision has happened with.
     * @return Has collision happened.
     */
    boolean checkCollision(Rectangle bounds);

    /**
     * Update position of the bounding box.
     */
    void updateBoundingBox();

    /**
     * Returns bounding box of the entity.
     * @return Rectangle that is bounding box of the entity.
     */
    Rectangle getBounds();
}
