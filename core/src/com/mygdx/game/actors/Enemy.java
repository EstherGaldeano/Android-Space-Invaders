//paquete actors
package com.mygdx.game.actors;

//imports
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Screen.GameScreen;


/**
 * Created by Esther on 03/06/2018.
 *
 * Clase de los Enemigos
 * Desde esta clase gestionaremos sus atributos, puntos de vida, las colisiones y los disparos
 */


public class Enemy extends GameObject
{
	//atributos
	private float x;
	private boolean live; //booleano para comprobar si está vivo
	private static int lifePoints; //vida de el enemigo
	private long lastFire; // long para gestionar los disparos

   //constructor
	public Enemy(String nomTexture, float x, float y)
	{
		super(nomTexture, x, y);
		this.lifePoints=10; //la vida de los enemigos es de 10 puntos
	}


	//acciones de la clase
	@Override
	public void act(float delta)
	{
		super.act(delta);
		live = true;

		//en caso de colisión entre la nave del jugador y una enemiga, se acaba el juego
		if (getRectangle().overlaps(GameScreen.player.getRectangle()))
		{
			GameScreen.player.setLive(false);
		}

		//mientras el enemigo esté vivo
		if (this.isLive())
		{
			//realizará disparos automáticamente, con 1000 mils entre disparo
			if(TimeUtils.millis()-lastFire>=1000){
				//sonido del disparo
				//Assets.tiroSonido.play();

				//situamos el origen del tiro en el Enemy
				TiroEnemigo tiro = new TiroEnemigo(Assets.tiroEnemigo,getX()+30,getY(),300);

				if(getStage()!=null)
				{	//añadimos el tiro al stage
					getStage().addActor(tiro);
				}

				lastFire = TimeUtils.millis();
			}

		}



		if(getY()< -Assets.ALTO){
			remove();
		}

	}

	//seters y geters

	//comprobación para saber si está vivo
	public boolean isLive()
	{
		return live;
	}


	public void setLive(boolean live)
	{
		this.live = live;
	}


	public int getLifePoints()
	{
		return lifePoints;
	}

	public void setLifePoints(int lifePoints)
	{
		this.lifePoints = lifePoints;
	}
}
