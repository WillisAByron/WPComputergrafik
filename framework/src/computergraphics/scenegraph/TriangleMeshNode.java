package computergraphics.scenegraph;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.TriangleMesh;

public class TriangleMeshNode extends Node {

	private static final int NUMBER = 1;

	private boolean listExist = false;
	
	private TriangleMesh triangleMesh;

	public TriangleMeshNode(TriangleMesh triangleMesh) {
		this.triangleMesh = triangleMesh;
	}
	
	@Override
	public void drawGl(GL2 gl) {
		if(!listExist)
			gl = getList(gl, NUMBER);

		gl.glCallList(NUMBER);
		gl.glFlush();
	}

	private GL2 getList(GL2 gl, int number) {
		gl.glNewList(number, GL2.GL_COMPILE);
		drawObject(gl);
		gl.glEndList();
		// Set color of Triangle to green!!
		gl.glColor3f(0.0f, 0.7f, 0.2f);
		this.listExist = true;
		System.out.println("erzeugt!");
		return gl;
	}

	private void drawObject(GL2 gl) {
		for (Triangle triangle : triangleMesh.getTriangleList()) {
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glNormal3dv(triangle.getNormal().data(), 0);
//			gl.glColor3dv(triangleMesh.getVertex(triangle.getA()).getColor().data(), 0);
			gl.glVertex3dv(triangleMesh.getVertex(triangle.getA()).getPosition().data() , 0);
//			gl.glColor3dv(triangleMesh.getVertex(triangle.getB()).getColor().data(), 0);
			gl.glVertex3dv(triangleMesh.getVertex(triangle.getB()).getPosition().data() , 0);
//			gl.glColor3dv(triangleMesh.getVertex(triangle.getC()).getColor().data(), 0);
			gl.glVertex3dv(triangleMesh.getVertex(triangle.getC()).getPosition().data() , 0);
			gl.glEnd();
		}
	}

}
