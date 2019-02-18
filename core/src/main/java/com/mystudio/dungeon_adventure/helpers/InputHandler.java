package com.mystudio.dungeon_adventure.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mystudio.dungeon_adventure.model.Player.PlayerBaseClass;
import com.mystudio.dungeon_adventure.model.Player.PlayerBasicClass;

public class InputHandler implements InputProcessor {
    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        boolean keyPressedDown = false;

        switch (keycode) {
            case Input.Keys.A:
                keyPressedDown = true;
                GameAttributes.PLAYER_MOVING_LEFT = true;
                break;
            case Input.Keys.W:
                keyPressedDown = true;
                GameAttributes.PLAYER_MOVING_UP = true;
                break;
            case Input.Keys.S:
                GameAttributes.PLAYER_MOVING_DOWN = true;
                keyPressedDown = true;
                break;
            case Input.Keys.D:
                GameAttributes.PLAYER_MOVING_RIGHT = true;
                keyPressedDown = true;
                break;
            case Input.Keys.I:
                // inventory is toggle-able (switch to opposite state each button press)
                GameAttributes.PLAYER_INVENTORY_OPEN = !GameAttributes.PLAYER_INVENTORY_OPEN;
                keyPressedDown = true;
                break;
        }

        return keyPressedDown;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        boolean keyPressedUp = false;

        switch (keycode) {
            case Input.Keys.A:
                keyPressedUp = true;
                GameAttributes.PLAYER_MOVING_LEFT = false;
                break;
            case Input.Keys.W:
                keyPressedUp = true;
                GameAttributes.PLAYER_MOVING_UP = false;
                break;
            case Input.Keys.S:
                GameAttributes.PLAYER_MOVING_DOWN = false;
                keyPressedUp = true;
                break;
            case Input.Keys.D:
                GameAttributes.PLAYER_MOVING_RIGHT = false;
                keyPressedUp = true;
                break;
        }

        return keyPressedUp;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
