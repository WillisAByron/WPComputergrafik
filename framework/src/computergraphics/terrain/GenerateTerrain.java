package computergraphics.terrain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import computergraphics.datastructures.Triangle;
import computergraphics.datastructures.TriangleMesh;
import computergraphics.datastructures.Vertex;
import computergraphics.math.Vector3;

public class GenerateTerrain {

	public static final String HEIGHFIELD_FILE = "ground/heightField.png";

	public static final String COLOR_FILE = "ground/color.png";

	public static final double MAX_X = 1;

	public static final double MAX_Y = 0.1;
	
	public static final double PRECISION = 0.01;

	public static final double MAX_Z = 1;

	public static final double STEP = 0.01;

	/**
	 * @return
	 * @throws IOException
	 */
	public TriangleMesh generateGround(double maxX, double maxY, double maxZ, double step) throws IOException {
		TriangleMesh trMesh = new TriangleMesh();
		BufferedImage bImage = ImageIO.read(new File(HEIGHFIELD_FILE));
		final double maxStepsX = maxX / step;
		final double maxStepsZ = maxZ / step;
		for (double x = 0; x <= maxX - step; x += step) {
			for (double z = 0; z <= maxZ - step; z += step) {
				int a = trMesh.addVertex(new Vertex(new Vector3(x, getHeight(bImage, x, z, maxStepsX, maxStepsZ), z)));
				int b = trMesh.addVertex(new Vertex(new Vector3(x + step, getHeight(bImage, x + step, z, maxStepsX,
						maxStepsZ), z)));
				int c = trMesh.addVertex(new Vertex(new Vector3(x,
						getHeight(bImage, x, z + step, maxStepsX, maxStepsZ), z + step)));
				trMesh.addTriangle(new Triangle(a, b, c));
				int aOppeside = trMesh.addVertex(new Vertex(new Vector3(x + step, getHeight(bImage, x + step, z + step,
						maxStepsX, maxStepsZ), z + step)));
				int bOppeside = trMesh.addVertex(new Vertex(new Vector3(x + step, getHeight(bImage, x + step, z,
						maxStepsX, maxStepsZ), z)));
				int cOppeside = trMesh.addVertex(new Vertex(new Vector3(x, getHeight(bImage, x, z + step, maxStepsX,
						maxStepsZ), z + step)));
				trMesh.addTriangle(new Triangle(aOppeside, bOppeside, cOppeside));
			}
		}
		trMesh.updateNormals();
		trMesh.updateColor(ImageIO.read(new File(COLOR_FILE)), maxStepsX, maxStepsZ, STEP);
		return trMesh;
	}

	private double getHeight(BufferedImage bImage, double x, double z, double maxStepsX, double maxStepsZ) {
		final double pictureX = (bImage.getWidth(null) / maxStepsX) * (x / STEP);
		final double pictureZ = (bImage.getHeight(null) / maxStepsZ) * (z / STEP);
		final double height = (new Color(bImage.getRGB((int) pictureX, (int) pictureZ)).getRed() / 255.0) * MAX_Y;
		return height;
	}

	public TriangleMesh generateRandomGround(double maxX, double maxY, double maxZ, double step, double precision) throws IOException {
		TriangleMesh trMesh = new TriangleMesh();
		double[][] heightMap = generateRandomHeight(maxX, maxZ, step, maxY, precision);
		for (double x = 0; x <= maxX - step; x += step) {
			for (double z = 0; z <= maxZ - step; z += step) {
				int a = trMesh.addVertex(new Vertex(new Vector3(x, heightMap[(int) (x/step)][(int) (z/step)], z)));
				int b = trMesh.addVertex(new Vertex(new Vector3(x + step, heightMap[(int) ((x+step)/step)][(int) (z/step)], z)));
				int c = trMesh.addVertex(new Vertex(new Vector3(x, heightMap[(int) (x/step)][(int) ((z+step)/step)], z + step)));
				trMesh.addTriangle(new Triangle(a, b, c));
				int aOppeside = trMesh.addVertex(new Vertex(new Vector3(x + step, heightMap[(int) ((x+step)/step)][(int) ((z+step)/step)], z + step)));
				int bOppeside = trMesh.addVertex(new Vertex(new Vector3(x + step, heightMap[(int) ((x+step)/step)][(int) (z/step)], z)));
				int cOppeside = trMesh.addVertex(new Vertex(new Vector3(x, heightMap[(int) (x/step)][(int) ((z+step)/step)], z + step)));
				trMesh.addTriangle(new Triangle(aOppeside, bOppeside, cOppeside));
			}
		}
		trMesh.updateNormals();
		return trMesh;
	}

	private double[][] generateRandomHeight(double maxX, double maxZ, double step, double maxY, double precision) {
		double[][] heightMap = new double[((int) (maxX/step))-1][((int) (maxY/step))-1];
		for (int x = 0; x < (int) (maxX/step); x++) {
			for(int z = 0; z < (int) (maxZ/step); z++){
				if(x == 0 && z == 0){
					heightMap[x][z] = Math.random() * maxY;
				}else if(x == 0 && z != 0){
					double temp = Math.random() * precision;
					if(heightMap[x][z-1] + temp >= maxY){
						heightMap[x][z] = heightMap[x][z-1] - temp;
					}else{
						heightMap[x][z] = heightMap[x][z-1] + temp;
					}
				}else if(x != 0 && z == 0){
					double temp = Math.random() * precision;
					if(heightMap[x-1][z] + temp >= maxY){
						heightMap[x][z] = heightMap[x-1][z] - temp;
					}else{
						heightMap[x][z] = heightMap[x-1][z] + temp;
					}
				}else{
					double temp = Math.random() * precision;
					if(heightMap[x-1][z] + temp >= maxY){
						heightMap[x][z] = heightMap[x-1][z] - temp;
					}else if(heightMap[x][z-1] + temp >= maxY){
						heightMap[x][z] = heightMap[x][z-1] - temp;
					}else if(heightMap[x-1][z-1] + temp >= maxY){
						heightMap[x][z] = heightMap[x-1][z-1] - temp;
					}else{
						heightMap[x][z] = heightMap[x-1][z] + temp;
					}
				}
			}
		}
		return heightMap;
	}
}
