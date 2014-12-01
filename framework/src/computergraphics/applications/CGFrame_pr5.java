/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package computergraphics.applications;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import computergraphics.datastructures.ObjIO;
import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.datastructures.Vertex;
import computergraphics.framework.AbstractCGFrame;
import computergraphics.math.Vector3;
import computergraphics.scenegraph.ColorNode;
import computergraphics.scenegraph.MovableObject;
import computergraphics.scenegraph.Node;
import computergraphics.scenegraph.RotationNode;
import computergraphics.scenegraph.ScaleNode;
import computergraphics.scenegraph.TranslationsNode;
import computergraphics.scenegraph.TriangleMeshNode;
import computergraphics.terrain.GenerateTerrain;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class CGFrame_pr5 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	//private static final String FILE_NAME_SPHERE = "meshes/sphere.obj";
	private static final String FILE_NAME_SPHERE = "meshes/untitled.obj";
	
	private List<MovableObject> lMO = new ArrayList<>();
	
	/**
	 * Constructor.
	 */
	public CGFrame_pr5(int timerInverval) {
		super(timerInverval);
		getRoot().addChild(createLandscape());
		
		List<Vector3> wP = new ArrayList<>();
		wP.add(new Vector3(0.45, 0, 0.45));
		wP.add(new Vector3(-0.45, 0, -0.45));
		wP.add(new Vector3(0.45, 0, -0.45));
		wP.add(new Vector3(-0.45, 0, 0.45));
		
		ScaleNode sN = new ScaleNode(new Vector3(0.02, 0.02, 0.02));
		ColorNode cN = new ColorNode(new Vector3(0, 0, 1));
		RotationNode rN = new RotationNode(0, new Vector3(0, 0, 0));
		TranslationsNode tN = new TranslationsNode(new Vector3(0, 0.02, 0));
		TriangleMeshNode tMN = new TriangleMeshNode(createTriangleMeshFromObject(FILE_NAME_SPHERE), false, 2);
		MovableObject mO = new MovableObject(sN, cN, rN, tN, tMN, wP, GenerateTerrain.HEIGHFIELD_FILE);
		getRoot().addChild(mO);
		
		lMO.add(mO);
	}

	private TriangleMesh createTriangleMeshFromObject(String filePath) {
		TriangleMesh trMesh = new TriangleMesh();
		ObjIO objIO = new ObjIO();
		objIO.einlesen(filePath, trMesh);
		trMesh.updateNormals();
		return trMesh;
	}

	private Node createLandscape() {
		ColorNode cn = new ColorNode(new Vector3(0,0,0));
		TranslationsNode tn = new TranslationsNode(new Vector3(-0.5, 0, -0.5));
		GenerateTerrain gt = new GenerateTerrain();
		TriangleMesh createGround = null;
		try {
			createGround = gt.generateGround(GenerateTerrain.MAX_X, GenerateTerrain.MAX_Y, GenerateTerrain.MAX_Z, GenerateTerrain.STEP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		TriangleMeshNode trMeshNode = new TriangleMeshNode(createGround, false, 1);
		cn.addChild(tn);
		tn.addChild(trMeshNode);
		return cn;
	}

	/*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		 for (MovableObject mO : lMO) {
			mO.tick();
		}
	}

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		new CGFrame_pr5(1000);
	}
}
