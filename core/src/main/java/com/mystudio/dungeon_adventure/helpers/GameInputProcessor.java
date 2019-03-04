package com.mystudio.dungeon_adventure.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mystudio.dungeon_adventure.view.InventoryScreen;
import jdk.nashorn.internal.objects.annotations.Constructor;

public class GameInputProcessor implements InputProcessor {

    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            InputHandler.isLeftMousePressedDown = true;
            InputHandler.mousePressedAtX = x;
            InputHandler.mousePressedAtY = y;
        }
        else if (button == Input.Buttons.RIGHT) {
            InputHandler.isRightMousePressedDown = true;
            InputHandler.mousePressedAtX = x;
            InputHandler.mousePressedAtY = y;
        }

        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {

        // only count touch ups if it is preceded by a touch down
        if (InputHandler.isLeftMousePressedDown) {

            if (button == Input.Buttons.LEFT) {
                InputHandler.isLeftMousePressedDown = false;
                InputHandler.mouseReleasedAtX = x;
                InputHandler.mouseReleasedAtY = y;

                // cancel drag
                InputHandler.isLeftMouseDragged = false;
                InputHandler.mouseDraggedX = -1;
                InputHandler.mouseDraggedY = -1;
            } else if (button == Input.Buttons.RIGHT) {
                InputHandler.isRightMousePressedDown = false;
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        if (InputHandler.isLeftMousePressedDown) {
            InputHandler.isLeftMouseDragged = true;
            InputHandler.mouseDraggedX = x;
            InputHandler.mouseDraggedY = y;
        }
        return true;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
        InputHandler.mouseHoveringAtX = x;
        InputHandler.mouseHoveringAtY = y;
        return true;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}
