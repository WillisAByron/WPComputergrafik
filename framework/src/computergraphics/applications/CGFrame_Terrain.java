/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import java.io.IOException;

import computergraphics.datastructures.TriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.TranslationsNode;
import computergraphics.scenegraph.TriangleMeshNode;
import computergraphics.terrain.GenerateTerrain;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class CGFrame_Terrain extends AbstractCGFrame {

	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Constructor.
	 */
	public CGFrame_Terrain(int timerInverval) {
		super(timerInverval);
		
		ColorNode cn = new ColorNode(new Vector3(0,0,0));
		TranslationsNode tn = new TranslationsNode(new Vector3(-0.5, 0, -0.5));
		GenerateTerrain gt = new GenerateTerrain();
		TriangleMesh createGround = null;
		try {
			createGround = gt.generateGround(GenerateTerrain.MAX_X, GenerateTerrain.MAX_Y, GenerateTerrain.MAX_Z, GenerateTerrain.STEP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		TriangleMeshNode trMeshNode = new TriangleMeshNode(createGround, false);
		getRoot().addChild(cn);
		cn.addChild(tn);
		tn.addChild(trMeshNode);
	}

	/*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		// System.out.println("Tick");
	}

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		new CGFrame_Terrain(1000);
	}
}
