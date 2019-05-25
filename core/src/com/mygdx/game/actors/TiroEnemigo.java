//paquete
package com.mygdx.game.actors;

//imports
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.mygdx.game.Screen.GameScreen.enemies;
import static com.mygdx.game.Screen.GameScreen.player;

/**
 * Created by Esther on 03/06/2018.
 * Clase para los tiros del enemigo
 */

public class TiroEnemigo extends GameObject
{
	//atributos
	private float vel;
	int colision=0;
	float destinationX;
	float destinationY;

	//constructor
	public TiroEnemigo(String nomTexture, float x, float y, float vel)
	{
		super(nomTexture, x, y);
		this.vel = vel;
	}


	//acciones del tiro
	@Override
	public void act(float delta)
	{
		super.act(delta);

		moveBy(0,vel*delta);

		destinationX = player.getX();
		destinationY = Assets.ALTO-enemies.getY();

		// enfocamos los tiros hacia el Player con una secuencia
		this.addAction(Actions.sequence(
				Actions.moveTo(destinationX,-destinationY, vel* delta)));


		//si salen de la pantalla se eliminan
		if(getY()> Assets.ALTO){

			remove();

		}else{

			//si un tiro colisiona con el Jugador, se le resta un punto de vida
			if(getRectangle().overlaps(player.getRectangle())){ //colisión entre tiro y player
			//	Assets.explosion.play();
				colision+=1;

				player.setLifePoints(player.getLifePoints()-colision);

				//si el Jugador tiene 0 o menos puntos muere
				if(player.getLifePoints()<=0){

					player.setLive(false);

				}
				//si muere desaparece de la pantalla
				if(!player.isLive())
				{
					player.clear();
					player.remove();
				}

				//para el tiro
				clear();
				remove();
			}

		}

	}

//seters y geters

	public float getVel()
	{
		return vel;
	}

	public void setVel(float vel)
	{
		this.vel = vel;
	}

}





