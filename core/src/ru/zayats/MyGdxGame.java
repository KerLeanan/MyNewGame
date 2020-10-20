package ru.zayats;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.zayats.Screen.MenuScreen;

public class MyGdxGame extends Game {
	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}
