/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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

	private static final double MAX_X = 1;

	private static final double MAX_Y = 0.1;

	private static final double MAX_Z = 1;

	private static final double STEP = 0.0125;
	
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
			createGround = generateGround(MAX_X, MAX_Y, MAX_Z, STEP);
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
		getRoot().addChild(trMeshNode);
	}

	/**
	 * @return
	 * @throws IOException
	 */
	private TriangleMesh generateGround(double maxX, double maxY, double maxZ, double step) throws IOException {
		TriangleMesh trMesh = new TriangleMesh();
		BufferedImage bImage = ImageIO.read(new File(HEIGHFIELD_FILE));
		final double maxStepsX = maxX / step;
		final double maxStepsZ = maxZ / step;
		for (double x = 0; x <= maxX - step; x += step) {
			for (double z = 0; z <= maxZ - step; z += step) {
				int a = trMesh.addVertex(new Vertex(
						new Vector3(x, getHeight(bImage, x, z, maxStepsX, maxStepsZ), z)));
				int b = trMesh.addVertex(new Vertex(
						new Vector3(x + step, getHeight(bImage, x + step, z, maxStepsX, maxStepsZ), z)));
				int c = trMesh.addVertex(new Vertex(
						new Vector3(x, getHeight(bImage, x, z + step, maxStepsX, maxStepsZ), z + step)));
				trMesh.addTriangle(new Triangle(a, b, c));
				int aOppeside = trMesh.addVertex(new Vertex(
						new Vector3(x + step, getHeight(bImage, x + step, z + step, maxStepsX, maxStepsZ), z + step)));
				int bOppeside = trMesh.addVertex(new Vertex(
						new Vector3(x + step, getHeight(bImage, x + step, z, maxStepsX, maxStepsZ), z)));
				int cOppeside = trMesh.addVertex(new Vertex(
						new Vector3(x, getHeight(bImage, x, z + step, maxStepsX, maxStepsZ), z + step)));
				trMesh.addTriangle(new Triangle(aOppeside, bOppeside, cOppeside));
			}
		}
		trMesh.updateNormals();
		return trMesh;
	}

	private double getHeight(BufferedImage bImage, double x, double z, double maxStepsX, double maxStepsZ) {
		final double pictureX = (bImage.getWidth(null) / maxStepsX) * (x / STEP);
		final double pictureZ = (bImage.getHeight(null) / maxStepsZ) * (z / STEP);
		final double height = (new Color(bImage.getRGB((int) pictureX, (int) pictureZ)).getRed() / 255.0) * MAX_Y;
		return height;
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
