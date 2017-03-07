package p012;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Pr�ctica 2-Simulaci�n de objetos f�sicos
 * @author Carlos Gonz�lez Calatrava, Sergio L�pez Bueno
 *
 * Clase Bola que define el comportamiento de las bolas dentro del tablero.
 */
public class Ball { 
	private String Ball = "Ball.png"; 

	private double x,y,dx,dy;
	private double v,fi;
	private Image image;

	/**
	 * Constructor de la clase Ball
	 */
	public Ball() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(Ball));
		image = ii.getImage();
		x = Billiards.Width/4-16;
		y = Billiards.Height/2-16;
		v = 5;
		fi =  Math.random() * Math.PI * 2;
	}

	/**
	 * M�todo encargado de mover la bola dentro del tablero.
	 */
	public synchronized void move() {
		v = v*Math.exp(-v/1000);
		dx = v*Math.cos(fi);
		dy = v*Math.sin(fi);
		if (Math.abs(dx) < 1 && Math.abs(dy) < 1) {
			dx = 0;
			dy = 0;
		}
		x += dx;   
		y += dy;
		assert x > Board.LEFTBOARD;
		assert x < Board.RIGHTBOARD;
		assert y > Board.BOTTOMBOARD;
		assert y < Board.TOPBOARD;
	}

	/**
	 * M�todo que refleja la bola cuando pega contra una de las
	 * paredes del tablero.
	 */
	public synchronized void reflect() {
		if (Math.abs(x + 32 - Board.RIGHTBOARD) <  Math.abs(dx)) {
			fi = Math.PI - fi;
		}
		if (Math.abs(y + 32 - Board.BOTTOMBOARD) <  Math.abs(dy)) {
			fi = - fi;
		}
		if (Math.abs(x - Board.LEFTBOARD) <  Math.abs(dx)) {
			fi = Math.PI - fi;
		}
		if (Math.abs(y - Board.TOPBOARD) <  Math.abs(dy)) {
			fi = - fi;
		}
		assert x > Board.LEFTBOARD;
		assert x < Board.RIGHTBOARD;
		assert y > Board.BOTTOMBOARD;
		assert y < Board.TOPBOARD;	
	}
	
	/**
	 * M�todo que devuelve la coordenada X.
	 * @return Coordenada X.
	 */
	public int getX() {
		return (int)x;
	}
	
	/**
	 * M�todo que devuelve la coordenada Y.
	 * @return Coordenada Y.
	 */
	public int getY() {
		return (int)y;
	}
	
	/**
	 * M�todo que devuelve el �ngulo de rebote.
	 * @return �ngulo.
	 */
	public double getFi() {
		return fi;
	}

	/**
	 * M�todo que devuelve el m�dulo de la direcci�n de la bola.
	 * @return M�dulo.
	 */
	public double getdr() {
		return Math.sqrt(dx*dx+dy*dy);
	}

	/**
	 * M�todo que establece la coordenada X.
	 * @param x Coordenada X.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * M�todo que establece la coordenada Y.
	 * @param y Coordenada Y.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * M�todo que devuelve la imagen que se usa para
	 * representar la bola.
	 * @return Imagen.
	 */
	public Image getImage() {
		return image;
	}

}

