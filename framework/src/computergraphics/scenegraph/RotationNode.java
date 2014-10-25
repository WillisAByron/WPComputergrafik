package computergraphics.scenegraph;

import javax.media.opengl.GL2;

import computergraphics.math.Vector3;

/**
 * @author Johann Bronsch, Glenn Bickel
 *
 *         Node zur rotation von Knoten.
 */
public class RotationNode extends Node {

	private Vector3 rotationsAchse;

	private double winkel;

	/**
	 * Konstruktor
	 * 
	 * @param winkel
	 *            = Bogenmaﬂ
	 * @param rotationsAchse
	 *            = Zielvektor
	 */
	public RotationNode(double winkel, Vector3 rotationsAchse) {
		this.rotationsAchse = rotationsAchse;
		this.winkel = winkel;
	}

	@Override
	public void drawGl(GL2 gl) {
		// Remember current state of the render system
		gl.glPushMatrix();

		gl.glRotated(winkel, rotationsAchse.get(0), rotationsAchse.get(1), rotationsAchse.get(2));

		for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}

		// Restore original state
		gl.glPopMatrix();
	}

}
