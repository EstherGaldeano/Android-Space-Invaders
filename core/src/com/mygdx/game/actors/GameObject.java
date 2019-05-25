//paquete actors
package com.mygdx.game.actors;

//imports
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Esther on 03/06/2018.
 *
 * Clase GameObject, extiende de Image
 *
 * Todos los actores de nuestro juego heredan de esta clase
 *
 *
 */

public class GameObject extends Image
{
	/**
	*	Creamos un rectángulo para detectar las colisiones
	*	estos rectángulos servirán para controlar las colisiones de todos los
	*	actores que hereden de esta clase
    */

private Rectangle rectangle;

	public GameObject (String nomTexture, float x, float y){
		super(new Texture(nomTexture));

		rectangle = new Rectangle(x,y,getWidth(),getHeight());
		setPosition(x,y);

	}


	@Override
	public void act(float delta)
	{
		super.act(delta);
		rectangle.setPosition(getX(),getY());
	}

//seters y geters

	public Rectangle getRectangle()
	{
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

}