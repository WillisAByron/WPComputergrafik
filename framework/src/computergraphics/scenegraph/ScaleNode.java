package computergraphics.scenegraph;

import javax.media.opengl.GL2;

import computergraphics.math.Vector3;

/**
 * Scene graph node which scales all its child nodes.
 * 
 * @author Philipp Jenke
 *
 */
public class ScaleNode extends Node {

	/**
	 * Scaling factors in x-, y- and z-direction.
	 */
	private final Vector3 scale = new Vector3(1, 1, 1);
	
	/**
	 * Change size
	 */
	private final int size = 4;

	/**
	 * Constructor.
	 */
	public ScaleNode(Vector3 scale) {
		this.scale.copy(scale);
	}

	@Override
	public void drawGl(GL2 gl) {
		// Remember current state of the render system
		gl.glPushMatrix();

		// Apply scaling
		gl.glScalef((float) scale.get(0) * size, (float) scale.get(0) * size,
				(float) scale.get(0) * size);

		// Draw all children
		for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			getChildNode(childIndex).drawGl(gl);
		}

		// Restore original state
		gl.glPopMatrix();

	}

}
