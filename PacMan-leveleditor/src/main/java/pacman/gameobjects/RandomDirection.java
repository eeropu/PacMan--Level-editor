package pacman.gameobjects;

import java.util.Random;

/**
 *
 * @author eerop
 *
 * This class is used to set a ghosts direction randomly when PacMan is out of
 * reach.
 */
public class RandomDirection {

    private Random random = new Random();
    private int i;

    /**
     * Sets the ghost direction randomly taking all possible directions to
     * consideration and avoiding U-turns.
     *
     * @param ghost the ghost that direction is about to be changed.
     */
    public void randomDirection(Ghost ghost) {
        if (ghost.d == Direction.Right) {
            randomDirectionRight(ghost);
        } else if (ghost.d == Direction.Left) {
            randomDirectionLeft(ghost);
        } else if (ghost.d == Direction.Up) {
            randomDirectionUp(ghost);
        } else {
            randomDirectionDown(ghost);
        }
    }

    public void randomDirectionRight(Ghost ghost) {
        i = 0;
        if (ghost.graph[(ghost.x + 64) / 32][(ghost.y + 32) / 32] == 1) {
            i += 1;
        }
        if (ghost.graph[(ghost.x + 32) / 32][(ghost.y) / 32] == 1) {
            i += 3;
        }
        if (ghost.graph[(ghost.x + 32) / 32][(ghost.y + 64) / 32] == 1) {
            i += 5;
        }
        if (i == 0) {
            ghost.d = Direction.Left;
        } else if (i == 3) {
            ghost.d = Direction.Up;
        } else if (i == 5) {
            ghost.d = Direction.Down;
        } else if (i == 4) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Up;
            }
        } else if (i == 6) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Down;
            }
        } else if (i == 8) {
            int r = random.nextInt(2);
            if (r == 0) {
                ghost.d = Direction.Up;
            } else {
                ghost.d = Direction.Down;
            }
        } else if (i == 9) {
            int r = random.nextInt(3);
            if (r == 1) {
                ghost.d = Direction.Up;
            } else if (r == 2) {
                ghost.d = Direction.Down;
            }
        }
    }

    public void randomDirectionLeft(Ghost ghost) {
        i = 0;
        if (ghost.graph[(ghost.x) / 32][(ghost.y + 32) / 32] == 1) {
            i += 1;
        }
        if (ghost.graph[(ghost.x + 32) / 32][(ghost.y) / 32] == 1) {
            i += 3;
        }
        if (ghost.graph[(ghost.x + 32) / 32][(ghost.y + 64) / 32] == 1) {
            i += 5;
        }
        if (i == 0) {
            ghost.d = Direction.Right;
        } else if (i == 3) {
            ghost.d = Direction.Up;
        } else if (i == 5) {
            ghost.d = Direction.Down;
        } else if (i == 4) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Up;
            }
        } else if (i == 6) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Down;
            }
        } else if (i == 8) {
            int r = random.nextInt(2);
            if (r == 0) {
                ghost.d = Direction.Up;
            } else {
                ghost.d = Direction.Down;
            }
        } else if (i == 9) {
            int r = random.nextInt(3);
            if (r == 1) {
                ghost.d = Direction.Up;
            } else if (r == 2) {
                ghost.d = Direction.Down;
            }
        }
    }

    public void randomDirectionUp(Ghost ghost) {
        i = 0;
        if (ghost.graph[(ghost.x + 32) / 32][(ghost.y) / 32] == 1) {
            i += 1;
        }
        if (ghost.graph[(ghost.x + 64) / 32][(ghost.y + 32) / 32] == 1) {
            i += 3;
        }
        if (ghost.graph[(ghost.x) / 32][(ghost.y + 32) / 32] == 1) {
            i += 5;
        }
        if (i == 0) {
            ghost.d = Direction.Down;
        } else if (i == 3) {
            ghost.d = Direction.Right;
        } else if (i == 5) {
            ghost.d = Direction.Left;
        } else if (i == 4) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Right;
            }
        } else if (i == 6) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Left;
            }
        } else if (i == 8) {
            int r = random.nextInt(2);
            if (r == 0) {
                ghost.d = Direction.Right;
            } else {
                ghost.d = Direction.Left;
            }
        } else if (i == 9) {
            int r = random.nextInt(3);
            if (r == 1) {
                ghost.d = Direction.Right;
            } else if (r == 2) {
                ghost.d = Direction.Left;
            }
        }
    }

    public void randomDirectionDown(Ghost ghost) {
        i = 0;
        if (ghost.graph[(ghost.x + 32) / 32][(ghost.y + 64) / 32] == 1) {
            i += 1;
        }
        if (ghost.graph[(ghost.x + 64) / 32][(ghost.y + 32) / 32] == 1) {
            i += 3;
        }
        if (ghost.graph[(ghost.x) / 32][(ghost.y + 32) / 32] == 1) {
            i += 5;
        }
        if (i == 0) {
            ghost.d = Direction.Up;
        } else if (i == 3) {
            ghost.d = Direction.Right;
        } else if (i == 5) {
            ghost.d = Direction.Left;
        } else if (i == 4) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Right;
            }
        } else if (i == 6) {
            int r = random.nextInt(2);
            if (r == 1) {
                ghost.d = Direction.Left;
            }
        } else if (i == 8) {
            int r = random.nextInt(2);
            if (r == 0) {
                ghost.d = Direction.Right;
            } else {
                ghost.d = Direction.Left;
            }
        } else if (i == 9) {
            int r = random.nextInt(3);
            if (r == 1) {
                ghost.d = Direction.Right;
            } else if (r == 2) {
                ghost.d = Direction.Left;
            }
        }
    }

    /*
     * Following classes are for testpurposes.
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    public int getI() {
        return i;
    }
}
