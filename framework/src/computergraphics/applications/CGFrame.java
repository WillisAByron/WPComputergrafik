/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import java.io.IOException;

import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.datastructures.Vertex;
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
public class CGFrame extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;
	
	public static final String HEIGHFIELD_FILE = "ground/heightField.png";
	private final String colorFile = "ground/color.png";

	/**
	 * Constructor.
	 */
	public CGFrame(int timerInverval) {
		super(timerInverval);
		
		ColorNode colorNode = new ColorNode(new Vector3(0.25, 0.25, 0.75), false);
		// SingleTriangleNode triangleNode = new SingleTriangleNode();
		// SingleTriangleNode triangleNode2 = new SingleTriangleNode();
		TranslationsNode tn = new TranslationsNode(new Vector3(-0.5, 0, -0.5));
		// RotationNode rn = new RotationNode(45, new Vector3(-1, -1, 0));
		// TriangleMesh trMesh = generateTriangleMesh();
		GenerateTerrain gt = new GenerateTerrain();
		TriangleMesh createGround = null;
		try {
			long before = System.currentTimeMillis();
			createGround = gt.generateGround(GenerateTerrain.MAX_X, GenerateTerrain.MAX_Y, GenerateTerrain.MAX_Z, GenerateTerrain.STEP, HEIGHFIELD_FILE, colorFile);
			long after = System.currentTimeMillis();
			System.out.println("Working time: " + (after - before));
			System.out.println("Numebr of Triangles: " + createGround.getNumberOfTriangles());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TriangleMeshNode trMeshNode = new TriangleMeshNode(createGround, false);
		getRoot().addChild(colorNode);

		// ohne Verschiebung
		// colorNode.addChild(triangleNode);

		// // mit Verschiebung
		// colorNode.addChild(tn);
		// tn.addChild(triangleNode);

		// mit Rotation
		// colorNode.addChild(rn);
		// rn.addChild(triangleNode);

		// mit TriangleMesh
		getRoot().addChild(tn);
		tn.addChild(trMeshNode);
	}

	/**
	 * Generate a TriangleMesh
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private TriangleMesh generateTriangleMesh() {
		TriangleMesh trMesh = new TriangleMesh();
		Vertex vertex0 = new Vertex(new Vector3(0, 0.5, 0));
		Vertex vertex1 = new Vertex(new Vector3(-0.5, -0.5, -0.5));
		Vertex vertex2 = new Vertex(new Vector3(0.5, -0.5, -0.5));
		Vertex vertex3 = new Vertex(new Vector3(0, -0.5, 0.5));
		trMesh.addTriangle(new Triangle(trMesh.addVertex(vertex0), trMesh.addVertex(vertex1), trMesh.addVertex(vertex2)));
		trMesh.addTriangle(new Triangle(trMesh.addVertex(vertex0), trMesh.addVertex(vertex3), trMesh.addVertex(vertex2)));
		trMesh.addTriangle(new Triangle(trMesh.addVertex(vertex0), trMesh.addVertex(vertex3), trMesh.addVertex(vertex1)));
		trMesh.addTriangle(new Triangle(trMesh.addVertex(vertex3), trMesh.addVertex(vertex1), trMesh.addVertex(vertex2)));
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
		new CGFrame(1000);
	}
}
