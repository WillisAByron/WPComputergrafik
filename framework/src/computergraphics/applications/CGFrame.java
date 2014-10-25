/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.datastructures.Vertex;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.RotationNode;
import computergraphics.scenegraph.SingleTriangleNode;
import computergraphics.scenegraph.TranslationsNode;
import computergraphics.scenegraph.TriangleMeshNode;

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

	private static final String HEIGHFIELD_FILE = "ground/heightField.png";
	
	/**
	 * Constructor.
	 */
	public CGFrame(int timerInverval) {
		super(timerInverval);

		ColorNode colorNode = new ColorNode(new Vector3(0.25, 0.25, 0.75));
		// SingleTriangleNode triangleNode = new SingleTriangleNode();
		// SingleTriangleNode triangleNode2 = new SingleTriangleNode();
		// TranslationsNode tn = new TranslationsNode(new Vector3(1, 1, 0));
		// RotationNode rn = new RotationNode(45, new Vector3(-1, -1, 0));
		// TriangleMesh trMesh = generateTriangleMesh();
		TriangleMesh createGround = null;
		try {
			createGround = generateGround();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TriangleMeshNode trMeshNode = new TriangleMeshNode(createGround);
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
		colorNode.addChild(trMeshNode);
	}

	/**
	 * @return
	 * @throws IOException 
	 */
	private TriangleMesh generateGround() throws IOException {
		TriangleMesh trMesh = new TriangleMesh();
		Image bild = ImageIO.read(new File(HEIGHFIELD_FILE));
		for (double x = 0; x <= 0.875; x += 0.125) {
			for (double z = 0; z <= 0.875; z += 0.125) {
				int a = trMesh.addVertex(new Vertex(new Vector3(x, 0.5, z)));
				int b = trMesh.addVertex(new Vertex(new Vector3(x + 0.125, 0.5, z)));
				int c = trMesh.addVertex(new Vertex(new Vector3(x, 0.5, z + 0.125)));
				trMesh.addTriangle(new Triangle(a, b, c));
				int aOppeside = trMesh.addVertex(new Vertex(new Vector3(x + 0.125, 0.5, z + 0.125)));
				int bOppeside = trMesh.addVertex(new Vertex(new Vector3(x + 0.125, 0.5, z)));
				int cOppeside = trMesh.addVertex(new Vertex(new Vector3(x, 0.5, z + 0.125)));
				trMesh.addTriangle(new Triangle(aOppeside, bOppeside, cOppeside));
			}
		}
		trMesh.updateNormals();
		return trMesh;
	}

	/**
	 * Generate a TriangleMesh
	 * 
	 * @return
	 */
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
