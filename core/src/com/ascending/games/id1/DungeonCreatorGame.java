package com.ascending.games.id1;

import com.ascending.games.id1.view.SkinService;
import com.ascending.games.id1.view.title.TitleScreen;
import com.ascending.games.lib.edit.resource.IResource;
import com.ascending.games.lib.edit.resource.ResourceFactory;
import com.ascending.games.lib.model.geometry.Rectangle2;
import com.ascending.games.lib.view.SceneManager2;
import com.ascending.games.lib.view.SpriteView;
import com.ascending.games.lib.view.texture.TextureManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.jetbrains.annotations.NotNull;

public class DungeonCreatorGame extends Game {

	private static final String SAVE_FILE = "./DungeonCreator/data/save/save.json";

	private TextureManager textureManager;
	private SceneManager2 sceneManager;
	private Skin skin;
	private IResource saveResource;
	private SpriteView bgView;

	@Override
	public void create () {
		System.out.println("Starting game Dungeon Creator");

		sceneManager = new SceneManager2(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		textureManager = new TextureManager();
		saveResource = new ResourceFactory().createResource(SAVE_FILE);
		skin = new SkinService().createSkin();

		Texture bgTexture = textureManager.getTexture("bg.jpg");
        bgView = new SpriteView(new Rectangle2(new Vector2(0,0), new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), bgTexture, 0);
        sceneManager.getViews().add(bgView);

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
		textureManager.dispose();
	}

	@NotNull
	public SceneManager2 getSceneManager() {
		return sceneManager;
	}

	@NotNull
	public Skin getSkin() { return skin; }

	@NotNull
	public IResource getSaveResource() { return saveResource; }

	@NotNull
	public TextureManager getTextureManager() { return textureManager; }
}
