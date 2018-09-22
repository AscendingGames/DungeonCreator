package com.ascending.games.id1;

import com.ascending.games.id1.view.SkinService;
import com.ascending.games.id1.view.title.TitleScreen;
import com.ascending.games.lib.edit.resource.IResource;
import com.ascending.games.lib.edit.resource.JSONResource;
import com.ascending.games.lib.view.SceneManager2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.jetbrains.annotations.NotNull;

public class DungeonCreatorGame extends Game {

	private static final String SAVE_FILE = "./DungeonCreator/data/save/save.json";

	private SceneManager2 sceneManager;
	private Texture img;
	private Skin skin;
	private IResource saveResource;

	@Override
	public void create () {
		System.out.println("Starting game Dungeon Creator");

		sceneManager = new SceneManager2(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		img = new Texture("badlogic.jpg");
		saveResource = new JSONResource(SAVE_FILE);
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
		if (saveResource.isLoaded()) {
			saveResource.save();
		}

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
	public IResource getSaveResource() { return saveResource; }
}
