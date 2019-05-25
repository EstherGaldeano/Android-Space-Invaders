//paquete game
package com.mygdx.game;

//imports
import com.badlogic.gdx.Game;

import static com.badlogic.gdx.math.MathUtils.random;

//clase Main de nuestro juego, hereda de Game
public class SpaceInvader extends Game
{
	@Override
	public void create()
	{
		com.mygdx.game.actors.Assets.init();
		setScreen(new com.mygdx.game.Screen.MainGameScreen(this));
	}

	@Override
	public void dispose()
	{
		super.dispose();
		com.mygdx.game.actors.Assets.dispose();
	}
}