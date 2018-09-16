package com.ascending.games.id1;

import com.ascending.games.id1.view.world.WorldScreen;
import com.ascending.games.lib.view.SceneManager2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class DungeonCreatorGame extends Game {
	private SceneManager2 sceneManager;
	private Texture img;

	@Override
	public void create () {
		System.out.println("Starting game Dungeon Creator");

		sceneManager = new SceneManager2(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		img = new Texture("badlogic.jpg");

		setScreen(new WorldScreen(this));
	}

	@Override
	public void render () {
		sceneManager.render();
		super.render();
	}
	
	@Override
	public void dispose () {
		sceneManager.dispose();
		img.dispose();
	}

	public SceneManager2 getSceneManager() {
		return sceneManager;
	}
}
