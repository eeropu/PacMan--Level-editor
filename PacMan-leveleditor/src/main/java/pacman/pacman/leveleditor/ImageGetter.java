package pacman.pacman.leveleditor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageGetter {
    
    private ClassLoader cl;
    private BufferedImage spriteSheet;

    public ImageGetter() {
        cl = this.getClass().getClassLoader();
        URL resource = cl.getResource("Pictures/PacMan_SpriteSheet.png");
        try{
            spriteSheet = ImageIO.read(resource);
        } catch (IOException e){
        }
    }
    
    public BufferedImage getImage(String address){
        URL resource = cl.getResource(address);
        BufferedImage image = null;
        try{
            image = ImageIO.read(resource);
        } catch (IOException e){
        }
        return image;
    }
    
    public BufferedImage getSubImage(int x){
        return spriteSheet.getSubimage(x * 32 - 32, 0, 32, 32);
    }
}
