//paquete actors
package com.mygdx.game.actors;

//imports
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Screen.GameScreen;

/**
 * Created by Esther on 03/06/2018.
 * Clase para los tiros del Jugador, también nos servirá para controlar la puntuación
 */

public class Tiro extends GameObject
{
	private float vel;
	public static int score;
	int colision=0;

	public Tiro(String nomTexture, float x, float y, float vel)
	{
		super(nomTexture, x, y);
		this.vel = vel;
	}



	@Override
	public void act(float delta)
	{
		super.act(delta);

		moveBy(0,vel*delta);

		//si salen de la pantalla se eliminan
		if(getY()> Assets.ALTO){
			remove();

		}else{

			for(Actor a: GameScreen.enemies.getChildren()){

				Enemy enem = (Enemy) a;

				//si colisionan tiro y enemy el enemigo pierde 1 punto de vida
				if(getRectangle().overlaps(enem.getRectangle())){ //colisión entre tiro y enemy
			//		Assets.explosion.play();
					colision+=1;

					enem.setLifePoints(enem.getLifePoints()-colision);

					// si el enemigo recibe 10 colisiones, desaparece
					if(enem.getLifePoints()<=0){

						System.out.println("muerto");

						enem.setLive(false);
						//si el enemigo muere, sumamos 10 puntos de score
						score +=10;
					}

					//cuando el enemigo muere, lo limpiamos de la pantalla
					if(!enem.isLive())
					{
						enem.clear();
						enem.remove();
					}

					//y los tiros también
					//para el tiro
					clear();
					remove();
				}
			}

		}

	}


	public static void setScore(int score)
	{
		Tiro.score = score;
	}
	//geter para la puntuación
	public int getScore()
	{
		return score;
	}

}




