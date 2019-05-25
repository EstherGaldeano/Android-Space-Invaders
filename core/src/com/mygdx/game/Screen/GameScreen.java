
//paquete screen
package com.mygdx.game.Screen;

//imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.SpaceInvader;
import com.mygdx.game.actors.*;
import com.mygdx.game.actors.Assets;
import com.mygdx.game.actors.Enemy;


import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Esther on 03/06/2018.
 * Práctica Libgdx de PMDM
 */

//Pantalla principal donde se realiza el juego
/*
* Jugador en la parte inferior de la pantalla puede moverse a derecha e izquierda con las flechas del teclado
* Puede disparar a los enemigos pulsando la flecha superior

* Los enemigos aparecen de forma aleatoria y disparan automáticamente a la misma velocidad pero un número
* inferior de disparos

* El jugador tiene 4 vidas, las que aparecen en pantalla más la primera vida, cada 100 puntos se pierde una vida
* Los enemigos tienen 10 puntos de vida
* Cada colisión cuenta 1 punto
* Si el jugador choca con una nave enemiga acaba la partida

 */
public class GameScreen extends ScreenAdapter
{
	//Atributos
	private SpaceInvader game;
	private Stage stage;
	private BitmapFont font;
	private SpriteBatch batch;

	//jugador
	public static Player player;

	//vidas del jugador
	public static Life life1;
	public static Life life2;
	public static Life life3;

	//enemigos
	public static Group enemies;
	//tiros

	public static Tiro tiro = new Tiro(Assets.tiro,0,0,300);


	public GameScreen (SpaceInvader game){
		this.game = game;
		stage = new Stage();
		batch = new SpriteBatch();
		font = new BitmapFont();

		//creamos y posicionamos el jugador
		player = new Player(Assets.hero,(Assets.ANCHO/2)-40,100);

		//creamos los enemigos
		enemies = new Group();


		//creamos y posicionamos las vidas
		life1 = new Life (Assets.vida, Assets.ANCHO-50,5);
		life2 = new Life (Assets.vida, Assets.ANCHO-90,5);
		life3 = new Life (Assets.vida, Assets.ANCHO-130,5);

		//añadimos los actores al stage
		stage.addActor(player);
		stage.addActor(life1);
		stage.addActor(life2);
		stage.addActor(life3);

		//añadimos los enemigos al stage
		stage.addActor(enemies);

		//creamos un run con temporizador para posicionar enemigos
		Timer.schedule(new Timer.Task()
		{
			@Override
			public void run()
			{
				//máximo 7 por pantalla
				while(enemies.getChildren().size<5)
				{
					positionEnemy(); //posicionamos los enemigos
				}
			}
		},1,1.5f);

	}


	//método para posicionar los enemigos del Group enemies
	private void positionEnemy(){

	float x = random(0, Assets.ANCHO-100);


		//creamos el enemigo
		Enemy enem = new Enemy(Assets.enemy,x, random(Assets.ALTO,Assets.ALTO+40));

		//le añadimos una secuencia de movimiento
		enem.addAction(Actions.sequence(
				Actions.moveBy(random(0,400),-(Assets.ALTO+300)-enem.getHeight(),30.0f),
				Actions.removeActor()));

			//añadimos el enemigo al Group
			enemies.addActor(enem);


	}

	@Override
	public void render(float delta)
	{
		//pintamos el fondo de negro
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();


		batch.begin();

		//mostramos la interfaz
		interfaz();

		//controlamos las vidas según los puntos de vida del jugador
		controlVidas();

		//controla si el jugador está muerto
		isAlive();



		//cerramos el batch
		batch.end();
	}

	//hacemos dispose para mejorar el rendimiento
	@Override
	public void dispose()
	{
		super.dispose();
		stage.dispose();

	}


	//desaparición de las vidas según lifePoints del jugador
	public void controlVidas()
	{

		if (player.getLifePoints() < 30)
		{
			life3.remove();
		}
		if (player.getLifePoints() < 20)
		{
			life2.remove();
		}
		if (player.getLifePoints() < 10)
		{
			life1.remove();
		}
		if (player.getLifePoints() <= 0)
		{
			player.setLive(false);
		}
	}


	//interfaz de la pantalla
		public void interfaz(){

			font.draw(batch,"________________________________________________________________________________________________",0,58);

			font.draw(batch,"SCORE:  "+Integer.toString(tiro.getScore()),5,28);

		}

	//comprobación de si el Player sigue vivo, si muere > Pantalla GameOver
	public void isAlive(){

		if(!player.isLive()){
			game.setScreen(new GameOverScreen(game));
		}

	}

}

