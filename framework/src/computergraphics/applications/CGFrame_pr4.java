/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.datastructures.Vertex;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.TranslationsNode;
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
	
	private static final String FILE_NAME_SQUARE = "meshes/square.obj";
	
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
		objIO.einlesen(FILE_NAME_SQUARE_EXTENDED, trMesh);
		return trMesh;
	}

	private TriangleMesh createTriangleMesh() {
		TriangleMesh trMesh = new TriangleMesh();
		Vertex linksUnten = new Vertex(new Vector3(-1, -1, 0));
		Vertex linksOben = new Vertex(new Vector3(-1, 1, 0));
		Vertex rechtsOben = new Vertex(new Vector3(1, 1, 0));
		Vertex rechtsUnten = new Vertex(new Vector3(1, -1, 0));
		trMesh = setTextureCoordinate(trMesh);
		trMesh.addTriangle(new Triangle(trMesh.addVertex(linksUnten), trMesh.addVertex(linksOben),
				trMesh.addVertex(rechtsUnten), 0, 1, 2));
		trMesh.addTriangle(new Triangle(trMesh.addVertex(linksOben), trMesh.addVertex(rechtsOben), 
				trMesh.addVertex(rechtsUnten), 1, 3, 2));
		trMesh.updateNormals();
		trMesh.setTextureFilename("meshes/logo_haw.png");
		return trMesh;
	}

	private TriangleMesh setTextureCoordinate(TriangleMesh trMesh) {
		Vector3 vector0 = new Vector3(0, 0, 0);
		Vector3 vector1 = new Vector3(0, 1, 0);
		Vector3 vector2 = new Vector3(1, 0, 0);
		Vector3 vector3 = new Vector3(1, 1, 0);
		trMesh.addTextureCoordinate(vector0);
		trMesh.addTextureCoordinate(vector1);
		trMesh.addTextureCoordinate(vector2);
		trMesh.addTextureCoordinate(vector3);
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
