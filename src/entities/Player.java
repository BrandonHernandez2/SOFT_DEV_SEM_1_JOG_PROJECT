package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 1.0f * Game.SCALE;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    // Jumping / Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE));

    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = playerAction;
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        // TODO: if inAir
        if (inAir)
        // TODO: if airSpeed is less than 0
        // TODO: set playerAction to JUMP
        if (airSpeed < 0)
            playerAction = JUMP;
        // TODO: else set playerAction to FALLING
        else
            playerAction = FALLING;

        if (attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;

        // TODO: if jump
        // TODO: call jump()
        if (jump)
            jump();
        // TODO: if not left and not right and not inAir
        // TODO: return
        if (!left && !right && !inAir)
            return;

        // create a float called xSpeed and set to 0
        float xSpeed = 0;

        // TODO: if left subtract playerSpeed from xSpeed
        if (left)
            xSpeed = xSpeed - playerSpeed;
        // TODO: if right add playerSpeed to xSpeed
        if (right)
            xSpeed = xSpeed - playerSpeed;


        // TODO: if not inAir
        if (!inAir)
        // TODO: if not IsEntityOnFloor(hitbox, lvlData)
        if (!IsEntityOnFloor(hitbox,lvlData))
        // TODO: set inAir to true
            inAir = true;


        // TODO: if inAir
        if (inAir)
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y = hitbox.y + airSpeed;
                airSpeed += gravity;
                updateXPos(airSpeed);
            }else
                hitbox.y = hitbox.y + GetEntityXPosNextToWall(hitbox, airSpeed);
            if (airSpeed > 0)
            resetInAir();
            else
            airSpeed = fallSpeedAfterCollision;
            updateXPos(xSpeed);
            if (inAir) {
                updateXPos(xSpeed);
                moving = true;
            }else
                updateXPos(xSpeed);
            moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            xSpeed += hitbox.x;
        else
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];
        for (int row = 0; row < animations.length; row++)
            for (int col = 0; col < animations[row].length; col++)
                animations[row][col] = img.getSubimage(col * 64, row * 40, 64, 40);

    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox,lvlData))
            inAir = true;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    // TODO: repeat for Up, Down, Right for previous 2

    public boolean isUp() { return  up; }

    public void setUp(boolean up) { this.up = up; }

    public boolean isDown() { return down; }

    public void setDown(boolean down) { this.down = down; }

    public boolean isRight() { return right; }

    public void setRight(boolean right) { this.right = right;}


    public void setJump(boolean jump) {
        // TODO: set this jump to jump.
        this.jump = jump;
    }

}