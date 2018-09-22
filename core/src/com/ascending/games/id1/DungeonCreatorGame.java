package com.ascending.games.id1;

import com.ascending.games.id1.model.world.Player;
import com.ascending.games.id1.model.world.PlayerService;
import com.ascending.games.id1.view.SkinService;
import com.ascending.games.id1.view.title.TitleScreen;
import com.ascending.games.lib.view.SceneManager2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.jetbrains.annotations.NotNull;

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
		setScreen(new TitleScreen(this));
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

	@NotNull
	public SceneManager2 getSceneManager() {
		return sceneManager;
	}

	@NotNull
	public Skin getSkin() { return skin; }

	@NotNull
	public Player getPlayer() { return player; }
}
