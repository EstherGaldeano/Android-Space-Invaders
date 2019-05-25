//paquete
package com.mygdx.game.actors;

//imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Esther on 02/06/2018.
 *
 * Clase Player es nuestro Jugador
 * Desde esta clase gestionaremos sus atributos, puntos de vida, las colisiones y los disparos
 *
 */

public class Player extends GameObject
{
	//atributos
	private float x;
	private boolean live; //---> boolean para comprobación si vivo
	private float vel = 250f; //--> velocidad de movimiento
	private long lastFire; //long para retardo de disparo
	private static int lifePoints;


//constructor
	public Player(String nomTexture, float x, float y)
	{
		super(nomTexture, x, y);
		live = true;
		this.lifePoints=40; //---> puntos de vida del jugador: 400 / 100 por vida
	}


	//acciones del jugador
	@Override
	public void act(float delta)
	{
		super.act(delta);
		float dirX = Gdx.input.getAccelerometerX();

		//******************MOVIMIENTO DE LA NAVE************************//

		x = getX();

		if(dirX==0){
			//movimiento a la derecha manteniendo pulsada la flecha derecha
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				x += delta * vel;
			}

			//movimiento a la izquierda manteniendo pulsada la flecha izquierda
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				x -= delta * vel;
			}

		}else{

			if(dirX<0){
				x +=delta*vel;
			}else{
				x -=delta*vel;
			}
		}

		//condición para evitar salirse de la pantalla
		if(x<=0){

			x=0;

		}else if (x>=Assets.ANCHO - getWidth() ){

			x=Assets.ANCHO - getWidth();
		}


		//posición de la nave Player
		setPosition(x,50);



		//************************DISPAROS***********************************//

		//si mantenemos pulsada la flecha hacia arriba, dispara
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			//añadimos un retardo de 300 mils para aumentar el intervalo entre disparos
			if(TimeUtils.millis()-lastFire>=250){
				//sonido de disparo
			//	Assets.tiroSonido.play();

				//situamos el origen del tiro en el Player
				Tiro tiro = new Tiro(Assets.tiro,getX()+(getWidth()/2)-1,(getHeight()/2)+70,300);
				getStage().addActor(tiro);
				lastFire = TimeUtils.millis();
			}
		}

	}


//seters y geters
	public boolean isLive()
	{
		return live;
	}

	public void setLive(boolean live)
	{
		this.live = live;
	}

	public  int getLifePoints()
	{
		return lifePoints;
	}

	public static void setLifePoints(int lifePoints)
	{
		Player.lifePoints = lifePoints;
	}
}
