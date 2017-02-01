import gameobjects.Direction;
import gameobjects.Ghost;
import gameobjects.PacMan;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class GhostTest {
    
    private Ghost ghost;
    private PacMan pacman;
    
    public GhostTest() {
        pacman = new PacMan(11, 1, Direction.Up);
        ghost = new Ghost(1, 1, pacman);
    }
    
}
