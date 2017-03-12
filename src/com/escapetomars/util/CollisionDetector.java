package com.escapetomars.util;

import java.util.ArrayList;
import java.util.List;
import com.escapetomars.interfaces.Collideable;

public class CollisionDetector {
	private final int LEVEL1_FIREBALL = 100;
	private final int LEVEL2_FIREBALL = 180;
	private final int LEVEL3_FIREBALL = 280;
	private int level;
	private int collideableType = 0;

	//take in apache coordinates & all existing collideables(fireballs, trees, lightning) & convert collideable positions into a list (adding n to each of their X & Y coordinates)
	//then check if the apache coordinates are in the list of ints that contain all collideable coordinates
	public CollisionDetector(int level){
		this.level = level;
	}
	
	
	public boolean detectsCollision(int[] apache, ArrayList<Collideable> collideables) {
		//get collideable type so we can adjust the collision detection when converting to list, as image sizes work differently
		if (collideables.size() > 0) {
			String type = collideables.get(0).getClass().getSimpleName();
			//System.out.println(type);
			if(type.equals("Fireball")){
				collideableType = 1;
			}else if(type.equals("Lightning")){
				collideableType = 3;
				//lightning
			}else{
				collideableType = 2;
			}
		}		
		
		if (collideableType == 1) {
			//Set<Integer> allFireballPositions = convertAllIntsToSet(fireballs);		
			for (int i = 0; i < collideables.size(); ++i) {
				if (apacheXequalsCollideableX(apache[0] + 55, collideables.get(i)) && apacheYequalsCollideableY(apache[1] + 10, collideables.get(i))) {
					return true;
				}
			} 
		}else if(collideableType == 2) {
			for (int i = 0; i < collideables.size(); ++i) {
				if //(apacheYequalsCollideableY(apache[1], collideables.get(i))) { 
				(apacheXequalsCollideableX(apache[0], collideables.get(i)) && apacheYequalsCollideableY(apache[1] + 10, collideables.get(i))) {
					return true;
				}
			} 
		}else{
			for (int i = 0; i <collideables.size(); i++) {
				//(apacheYequalsCollideableY(apache[1], collideables.get(i))) { 
				if (apacheXequalsCollideableX(apache[0], collideables.get(0))
						&& apacheYequalsCollideableY(apache[1] + 10, collideables.get(0))) {
					return true;
				} 
			}
		}		
		return false;
	}	
	
	private List<Integer> convertCollideableToList(Collideable collideable) {
		List<Integer> collideablePositions = new ArrayList<Integer>();
		if (collideableType == 1) {
			// loop to add n to X + Y coordinates - depending on current game level
			//fireball 
			for (int k = 0; k < getCollideableSize(); ++k) {
				collideablePositions.add(collideable.getX() + k);
				collideablePositions.add(collideable.getY() + k);
			}
		} else if(collideableType == 2){
			//tree
			for (int k = 0; k < 200; ++k) {
				collideablePositions.add(collideable.getX() + k);
				//+60 to take into consideration image white space
				collideablePositions.add((collideable.getY()) + k);
			}
		}else{
			//System.out.println("converting lightning coords to collideable ++++++++++++++++++++++++++");
			//lightning
			for (int k = 0; k < 260; ++k) {
				collideablePositions.add(collideable.getX() + k);
				collideablePositions.add((collideable.getY()) + k + 200);
			}
		}
		return collideablePositions;
	}
	
	private int getCollideableSize() {		
		if(this.level == 1){
			return LEVEL1_FIREBALL;
		}else if(this.level == 2){
			return LEVEL2_FIREBALL;
		}else{
			return LEVEL3_FIREBALL;
		}
	}

	//i = 0 for x & then increment by 2
	private boolean apacheXequalsCollideableX(int apacheX, Collideable collideable){
		List<Integer> collideablePositions = convertCollideableToList(collideable);
		if (collideableType == 1) {
			for (int i = 0; i < getCollideableSize(); i += 2) {
				if (apacheX == collideablePositions.get(i)) {
					return true;
				}
			} 
		}else if(collideableType == 2){
			//adding 220 for width of tree (largest width is 440 - test to see if ok)
			for (int i = 0; i < 220; i += 2) {
				if (apacheX == collideablePositions.get(i)) {
					return true;
				}
			} 
		}else{
			//System.out.println("checking lightning x &&&&");
			//System.out.println(collideablePositions);
			//adding 130 for width of lightning 
			for (int i = 0; i < 260; i += 2) {
				//System.err.println("Apache X : " + apacheX + " : " + collideablePositions.get(i));
				if (apacheX == collideablePositions.get(i)) {
					//System.out.println("X = true");
					return true;
				}
			} 
		}
		//System.out.println("false");
		return false;
	}
	
	private boolean apacheYequalsCollideableY(int apacheY, Collideable collideable){
		List<Integer> collideablePositions = convertCollideableToList(collideable);
		if (collideableType == 1) {
			for (int i = 1; i < getCollideableSize(); i += 2) {
				if (apacheY == collideablePositions.get(i)) {
					return true;
				}
			} 
		}else if(collideableType == 2){
			for (int i = 1; i < 160; i += 2) {
				if (apacheY == collideablePositions.get(i)) {
					return true;
				}
			} 
		}else{
			//System.out.println("checking lightning Y coords =========================");
			//adding 360 for width of lightning 
			for (int i = 1; i < 260; i += 2) {
				//System.err.println("Apache Y : " + apacheY + " : " + collideablePositions.get(i));
				if (apacheY == collideablePositions.get(i)) {
					//System.out.println("Y = true");
					return true;
				}
			} 
		}
		return false;
	}
}
