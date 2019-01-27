/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */
package utilities;

import org.newdawn.slick.Image;

public class BoundingBox {
	private static final float FUZZ = 0.95f;
	
	private float left;
	private float top;
	private float width;
	private float height;
	private boolean isSolid;
	
	public BoundingBox(float x, float y, float width, float height, boolean isSolid) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		this.isSolid = isSolid;
	}
	public BoundingBox(Image img, float x, float y, boolean isSolid) {
		setWidth(img.getWidth());
		setHeight(img.getHeight());
		setX(x);
		setY(y);
		this.isSolid = isSolid;
	}
	public BoundingBox(BoundingBox bb) {
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
		isSolid = bb.isSolid;
	}
	
	/**
	 * Update bounding box object
	 * @param newX The new x coordinate of the object
	 * @param newY The new y coordinate of the object
	 */
	public void update(float newX, float newY) {
		setX(newX);
		setY(newY);
	}

	/*
	 * Sets the x and y position at the centre of the bounding box.
	 */
	public void setX(float x) {
		left = x - width / 2;
	}
	public void setY(float y) {
		top = y - height / 2;
	}
	
	public void setWidth(float w) {
		width = w * FUZZ;
	}
	public void setHeight(float h) {
		height = h * FUZZ;
	}
	
	public float getLeft() {
		return left;
	}
	public float getTop() {
		return top;
	}
	public float getRight() {
		return left + width;
	}
	public float getBottom() {
		return top + height;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	/**
	 * Get the property of box (being a box of a solid sprite or not) 
	 * @return The property of box (being a box of a solid sprite or not)
	 */
	public boolean getProperty() {
		return isSolid;
	}
	
	public boolean intersects(BoundingBox other) {
		return !(other.left > getRight()
			  || other.getRight()  < left
			  || other.top > getBottom()
			  || other.getBottom() < top);
	}
}
