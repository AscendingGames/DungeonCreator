package com.ascending.games.id1;

import com.ascending.games.id1.view.SkinService;
import com.ascending.games.id1.view.title.TitleScreen;
import com.ascending.games.lib.edit.resource.IResource;
import com.ascending.games.lib.edit.resource.ResourceFactory;
import com.ascending.games.lib.model.geometry.Rectangle2;
import com.ascending.games.lib.view.SpriteView;
import com.ascending.games.lib.view.Toolkit;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.jetbrains.annotations.NotNull;

public class DungeonCreatorGame extends Game {

	private static final String SAVE_FILE = "./DungeonCreator/data/save/save.json";

	private Toolkit toolkit;
	private Skin skin;
	private IResource saveResource;

	@Override
	public void create () {
		System.out.println("Starting game Dungeon Creator");

		toolkit = new Toolkit((new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		saveResource = new ResourceFactory().createResource(SAVE_FILE);
		skin = new SkinService().createSkin(toolkit.getTextureManager());

		Texture bgTexture = toolkit.getTextureManager().getTexture("bg.jpg");
        SpriteView bgView = new SpriteView(new Rectangle2(new Vector2(0,0), new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), bgTexture, 0);
		toolkit.getSceneManager().getViews().add(bgView);

		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		toolkit.getSceneManager().render();
		super.render();
	}
	
	@Override
	public void dispose () {
		if (saveResource.isLoaded()) {
			saveResource.save();
		}

		toolkit.dispose();
	}

	@NotNull
	public Toolkit getToolkit() {
		return toolkit;
	}

	@NotNull
	public Skin getSkin() { return skin; }

	@NotNull
	public IResource getSaveResource() { return saveResource; }
}
