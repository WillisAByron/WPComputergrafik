/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.scenegraph.TriangleMeshNode;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class CGFrame_pr4 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	private static final String FILE_NAME_CUBE = "meshes/cube.obj";
	
	@SuppressWarnings("unused")
	private static final String FILE_NAME_SQUARE = "meshes/square.obj";
	
	@SuppressWarnings("unused")
	private static final String FILE_NAME_SQUARE_EXTENDED = "meshes/square_extended.obj";
	
	/**
	 * Constructor.
	 */
	public CGFrame_pr4(int timerInverval) {
		super(timerInverval);
		TriangleMesh triangleMesh = createTriangleMeshFromObject();
		TriangleMeshNode trMeshNode = new TriangleMeshNode(triangleMesh, true);
		getRoot().addChild(trMeshNode);
	}

	private TriangleMesh createTriangleMeshFromObject() {
		TriangleMesh trMesh = new TriangleMesh();
		ObjIO objIO = new ObjIO();
		objIO.einlesen(FILE_NAME_CUBE, trMesh);
		trMesh.updateNormals();
		return trMesh;
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
		new CGFrame_pr4(1000);
	}
}
