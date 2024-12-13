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
        // TODO: call initHitbox passing in x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE)

    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        // TODO: call g.drawImage passing in animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - YDrawOffset), width, height, null)
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
        // TODO: if CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                // TODO: add airSpeed to hitbox.y
                hitbox.y = hitbox.y + airSpeed;
                // TODO: add gravity to airSpeed
                airSpeed += gravity;
                // TODO: updateXPos
                updateXPos(airSpeed);
                // TODO: else
                // TODO: set hitbox.y to GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed)
            }else
                hitbox.y = hitbox.y + GetEntityXPosNextToWall(hitbox, airSpeed);
        // TODO: if airSpeed is positive
        // TODO: call resetInAir()
        if (airSpeed > 0)
            resetInAir();
        // TODO: else
        // TODO: set airSpeed to fallSpeedAfterCollision
        else
            airSpeed = fallSpeedAfterCollision;
        // TODO: done with else call updateXPos(xSpeed)
        updateXPos(xSpeed);
        // TODO: else (based off of if inAir)
        if (inAir) {
            // TODO: call updateXPos(xSpeed)
            // TODO: set moving to true
            updateXPos(xSpeed);
            moving = true;
        }else
            updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        // TODO: if inAir then return
        if (inAir)
            return;
        // TODO: set inAir to true
        inAir = true;
        // TODO: set airSpeed to jumpSpeed
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        // TODO: set inAir to false
        inAir = false;
        // TODO: set airSpeed to 0
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        // TODO: if CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)
        // TODO: add xSpeed to hitbox.x
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            xSpeed += hitbox.x;
        // TODO: else set hitbox.x to GetEntityXPosNextToWall(hitbox, xSpeed)
        else
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
    }

    private void loadAnimations() {
        // TODO: create a BufferedImage called img and set to LoadSvae.GetSpriteAtlas(LoadSave.PLAYER_ATLAS)
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];
        for (int row = 0; row < animations.length; row++)
            for (int col = 0; col < animations[row].length; col++)
                animations[row][col] = img.getSubimage(col * 64, row * 40, 64, 40);

    }

    public void loadLvlData(int[][] lvlData) {
        // TODO: set this lvlData to lvlData
        this.lvlData = lvlData;
        // TODO: if not IsEntityOnFloor(hitbox, lvlData)
        // TODO: set inAir to true
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