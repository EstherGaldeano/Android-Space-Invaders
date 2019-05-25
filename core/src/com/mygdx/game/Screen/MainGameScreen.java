//paquete screen
package com.mygdx.game.Screen;

//imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceInvader;
import com.mygdx.game.actors.Assets;

/**
 * Created by Esther on 03/06/2018.
 *
 * Pantalla inicial, al pulsar Enter empieza el juego
 */

public class MainGameScreen extends ScreenAdapter
{
	private SpaceInvader game;
	private BitmapFont font;
	private SpriteBatch batch;
	private Texture hero = new Texture(Assets.hero);

	public MainGameScreen(SpaceInvader game){
	this.game = game;
	batch = new SpriteBatch();
		font = new BitmapFont();

	}


	@Override
	public void render(float delta)
	{	//pintamos de negro
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//al pulsar Enter empieza el juego
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){

		game.setScreen(new GameScreen(game));

		}

		//escribimos el texto en la pantalla
		batch.begin();
		font.draw(batch,"SPACE INVADERS",300,600);
		font.draw(batch,"Presiona ENTER para continuar",260,550);
		batch.draw(hero,340,300);

		batch.end();

	}


	@Override
	public void dispose()
	{
		font.dispose();
		batch.dispose();
		hero.dispose();
	}
}
