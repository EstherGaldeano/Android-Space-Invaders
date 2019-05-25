//paquete screen
package com.mygdx.game.Screen;

//imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceInvader;
import com.mygdx.game.actors.Assets;
import com.mygdx.game.actors.Tiro;

/**
 * Created by Esther on 03/06/2018.
 *
 * Pantalla de GameOver, aparece al morir el jugador, nos permite volver a jugar
 */

public class GameOverScreen extends ScreenAdapter
{
	private SpaceInvader game;
	private BitmapFont font;
	private SpriteBatch batch;
	public static Tiro tiro = new Tiro(Assets.tiro,0,0,300);

	public GameOverScreen(SpaceInvader game){

		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();

	}


	@Override
	public void render(float delta)
	{	//pintamos en negro
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//texto de la pantalla
		batch.begin();
		font.draw(batch,"GAME OVER",300,600);
		font.draw(batch,"Pulsa ENTER para continuar",260,550);
		batch.end();



		//si pulsamos enter volvemos a jugar
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){

			game.setScreen(new GameScreen(game));

			//puntuación a 0
			tiro.setScore(0);

		}
	}

//dispose para mejorar el rendimiento
	@Override
	public void dispose()
	{
		font.dispose();
		batch.dispose();
	}
}



