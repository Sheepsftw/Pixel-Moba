package game.world.bullets;

import game.hitboxes.CircleBox;
import game.screens.Frame;
import game.world.MapObject;
import java.io.IOException;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

public class BulletBill extends Bullet {

    public final String DEFAULT_PATH = "bulletbill.png";
    double direction;
    int speed;
    public MapObject caster;

    private void init() throws IOException {
        Texture buffer = Frame.loadImage(DEFAULT_PATH);
        Sprite texture = new Sprite();
        texture.setTexture(buffer);
        this.texture = texture;
        setOriginMiddle();
        texture.rotate((float) Math.toDegrees(direction + Math.PI));
        hitbox = new CircleBox(new Vector2i((int) xPos, (int) yPos), 10);
    }

    public BulletBill(int sizex, int sizey, int xPos, int yPos, double direction) throws IOException {
        super();

        this.sizex = sizex;
        this.sizey = sizey;
        this.xPos = xPos;
        this.yPos = yPos;

        this.direction = direction;
        speed = 1;
        init();
    }

    public BulletBill(int sizex, int sizey, int xPos, int yPos, int xTar, int yTar) throws IOException {
        super();

        this.sizex = sizex;
        this.sizey = sizey;
        this.xPos = xPos;
        this.yPos = yPos;
        float x = xTar - xPos;
        float y = yTar - yPos;
        if (x < 0) {
            direction = Math.atan(y * 1.0 / (x)) + Math.PI;
        } else if (x != 0) {
            direction = Math.atan(y * 1.0 / x);
        } else {
            direction = Math.atan(y * 1.0 / (.1));
        }

        speed = 1;
        init();
    }

    @Override
    public void update() {
        shift(speed * (float) Math.cos(direction), speed * (float) Math.sin(direction));
        if (xPos > map.sizex + 100 || xPos < -20 || yPos > map.sizey + 100 || yPos < -20) {
            remove = true;
        }
        ((CircleBox)hitbox).origin = new Vector2i((int)xPos,(int)yPos);
    }

}
