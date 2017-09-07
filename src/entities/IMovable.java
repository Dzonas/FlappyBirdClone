package entities;

/**
 * Necessary methods for moving entities.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public interface IMovable {
    /**
     * Move along X axis.
     */
    void moveX();

    /**
     * Move along Y axis.
     */
    void moveY();
}
