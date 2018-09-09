package com.ascending.games.id1;

import com.ascending.games.id1.edit.board.BoardDomain;
import com.ascending.games.id1.edit.board.IRoomFactory;
import com.ascending.games.id1.model.board.Board;
import com.ascending.games.id1.model.board.Room;
import com.ascending.games.id1.model.board.RoomElement;
import com.ascending.games.id1.view.BoardView;
import com.ascending.games.id1.view.DungeonScreen;
import com.ascending.games.lib.model.geometry.Coord2;
import com.ascending.games.lib.view.SceneManager2;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class DungeonCreatorGame extends Game {
	private SceneManager2 sceneManager;
	private Texture img;

	@Override
	public void create () {
		System.out.println("Starting game Dungeon Creator");

		sceneManager = new SceneManager2(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		img = new Texture("badlogic.jpg");

		setScreen(new DungeonScreen(this));
	}

	@Override
	public void render () {
		super.render();
		sceneManager.render();
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
