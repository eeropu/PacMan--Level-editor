package pacman.pacman.leveleditor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Used to retrieve images from the resources.
 *
 * @author eerop
 */
public class ImageGetter {

    private ClassLoader cl;
    private BufferedImage spriteSheet;

    /**
     * Constructor for the ImageGetter-class.
     */
    public ImageGetter() {
        cl = this.getClass().getClassLoader();
        URL resource = cl.getResource("Pictures/PacMan_SpriteSheet.png");
        try {
            spriteSheet = ImageIO.read(resource);
        } catch (IOException e) {
        }
    }

    /**
     * This method is used to retrieve an image from the resources.
     *
     * @param address the path to the image
     * @return returns the image
     */
    public BufferedImage getImage(String address) {
        URL resource = cl.getResource(address);
        BufferedImage image = null;
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
        }
        return image;
    }

    /**
     * This method is used to retrieve a subimage from the spritesheet
     * containing the images used for the gameobjects.
     *
     * @param x integer that defines the x coordinate of the subimage
     * @return returns the image
     */
    public BufferedImage getSubImage(int x) {
        return spriteSheet.getSubimage(x * 32 - 32, 0, 32, 32);
    }
}
