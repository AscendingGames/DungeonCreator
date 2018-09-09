package com.ascending.games.id1;

import com.ascending.games.id1.edit.board.BoardDomain;
import com.ascending.games.id1.edit.board.IRoomFactory;
import com.ascending.games.id1.model.board.Board;
import com.ascending.games.id1.model.board.Room;
import com.ascending.games.id1.model.board.RoomElement;
import com.ascending.games.id1.view.BoardView;
import com.ascending.games.lib.model.geometry.Coord2;
import com.ascending.games.lib.view.SceneManager2;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

public class DungeonCreatorGame extends ApplicationAdapter {
	private SceneManager2 sceneManager;
	private Texture img;

	@Override
	public void create () {
		sceneManager = new SceneManager2(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		img = new Texture("badlogic.jpg");

		Board board = new Board(10, 20);
		BoardDomain boardDomain = new BoardDomain(board, new IRoomFactory() {
		    @NotNull
            @Override
            public Room createRoom() {
                return new Room(Collections.singletonList(new RoomElement(new Coord2(0, 0))), new Vector2(0,0));
            }
        });
		BoardView boardView = new BoardView(board);
		sceneManager.getViews().add(boardView);
	}

	@Override
	public void render () {
		sceneManager.render();
	}
	
	@Override
	public void dispose () {
		sceneManager.dispose();
		img.dispose();
	}
}
