/**
 * 
 */
package computergraphics.scenegraph;

import java.util.ArrayList;
import javax.media.opengl.GL2;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.math.Vector3;

/**
 * @author Glenn
 *
 */
public class MovableObject extends Node {

	private ScaleNode sN;
	private ColorNode cN;
	private RotationNode rN;
	private TranslationsNode tN;
	private ArrayList<Vector3> waypoints;
	
	public MovableObject(ScaleNode sN, RotationNode rN, TranslationsNode tN, TriangleMesh tMesh, ArrayList<Vector3> waypoints){
		this.sN = sN;
		this.rN = rN;
		this.tN = tN;
		this.waypoints = waypoints;
		this.addChild(tN);
		this.tN.addChild(rN);
		this.rN.addChild(sN);
		this.sN.addChild(new TriangleMeshNode(tMesh));
	}
	
	@Override
	public void drawGl(GL2 gl) {
		// TODO Auto-generated method stub

	}
}
