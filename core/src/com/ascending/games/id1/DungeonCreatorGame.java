package com.ascending.games.id1;

import com.ascending.games.id1.model.world.Player;
import com.ascending.games.id1.edit.world.PlayerService;
import com.ascending.games.id1.view.SkinService;
import com.ascending.games.id1.view.world.WorldScreen;
import com.ascending.games.lib.view.SceneManager2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DungeonCreatorGame extends Game {
	private SceneManager2 sceneManager;
	private Texture img;
	private Skin skin;
	private Player player;

	@Override
	public void create () {
		System.out.println("Starting game Dungeon Creator");

		sceneManager = new SceneManager2(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		img = new Texture("badlogic.jpg");

		player = new PlayerService().createInitialPlayer();
		skin = new SkinService().createSkin();
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
	public Skin getSkin() { return skin; }
	public Player getPlayer() { return player; }
}
