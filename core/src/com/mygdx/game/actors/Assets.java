//paquete actors
package com.mygdx.game.actors;

//imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Esther on 03/06/2018.
 *
 * Todos los assets que se usan en el juego
 */


public class Assets
{
	//dimensiones

	//dimensiones Desktop
	/*
	public static int ANCHO = 480;
	public static int ALTO = 640;*/

	//dimensiones blueStacks
	public static int ANCHO = 720;
	public static int ALTO = 1100;

	//imágenes
	public static String hero = "hero.png";
	public static String enemy = "enemy.png";
	public static String tiro = "tiro.png";
	public static String tiroEnemigo = "tiroEnemigo.png";
	public static String vida = "hero_life.png";

	//sonidos
	public static Sound tiroSonido;
	public static Sound explosion;


	//método para iniciar los sonidos, en BlueStaks petardea, los dejo comentados
	public static void init(){

	tiroSonido = Gdx.audio.newSound(Gdx.files.internal("missile.wav"));
		tiroSonido.setVolume(1,0.5f);
	explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
		explosion.setVolume(1,0.5f);

	}

	//hacemos dispose para mejorar el rendimiento
	public static void dispose(){
	tiroSonido.dispose();
	explosion.dispose();
	}


}
