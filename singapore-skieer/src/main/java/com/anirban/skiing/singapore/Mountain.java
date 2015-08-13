package com.anirban.skiing.singapore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.anirban.skiing.exception.StupidInputException;

/**
 * The mountain range containing an array of peaks
 * @author anirbanroy
 *
 */
public class Mountain {

	private Peak[][] peaksInTheMountain;
	private List<Peak> longestSkiablePath;
	private int steepNessOfLongestSkiablePath;
	boolean isBuilt;

	public Mountain(int[][] heightOfPeaks) throws StupidInputException {
		peaksInTheMountain = new Peak[heightOfPeaks[0].length][heightOfPeaks.length];
		fillData(heightOfPeaks);
		isBuilt = false;
	}

	/**
	 * Filling data into the peak array from the Integer array
	 * @param heightOfPeaks
	 * @throws StupidInputException
	 */
	private void fillData(int[][] heightOfPeaks) throws StupidInputException  {
		int columns = heightOfPeaks.length;
		int rows = heightOfPeaks[0].length;
		for(int i = 0;i < columns;i++){
			for(int j = 0;j < rows;j++){
				if(heightOfPeaks[i][j] < 0)
					StupidInputException.throwMe("A peak height cannot be below sea level. Entered "+heightOfPeaks[i][j]+" at "+i+","+j,Locale.ENGLISH);
				peaksInTheMountain[i][j] = new Peak(i, j, heightOfPeaks[i][j]);
			}
		}
	}

	/**
	 * Called by each peak when it is built completely.
	 * @param path
	 */
	void setLongestSkiablePath(List<Peak> path) {
		int pathSteepness = path.get(0).getPeakHeight() - path.get(path.size() - 1).getPeakHeight();
		if (longestSkiablePath != null) {
			if (path.size() < longestSkiablePath.size())
				return;
			if (path.size() == longestSkiablePath.size()) {
				if (pathSteepness <= steepNessOfLongestSkiablePath)
					return;
			}
		}
		longestSkiablePath = path;
		steepNessOfLongestSkiablePath = pathSteepness;
	}
	/**
	 * Get the neighboring peaks which are lower in height
	 * @param peak
	 * @return
	 */
	public List<Peak> getNeighboringPeaks(Peak peak) {
		List<Peak> neighbours = new ArrayList<Peak>();
		int xCord = peak.getX();
		int yCord = peak.getY();
		if(xCord - 1 >= 0){
			if(peaksInTheMountain[xCord-1][yCord].getPeakHeight() < peak.getPeakHeight())
				neighbours.add(peaksInTheMountain[xCord-1][yCord]);
		}
		if(xCord + 1 <= peaksInTheMountain[0].length-1){
			if(peaksInTheMountain[xCord+1][yCord].getPeakHeight() < peak.getPeakHeight())
				neighbours.add(peaksInTheMountain[xCord+1][yCord]);
		}
		if(yCord - 1 >= 0){
			if(peaksInTheMountain[xCord][yCord-1].getPeakHeight() < peak.getPeakHeight())
				neighbours.add(peaksInTheMountain[xCord][yCord-1]);
		}
		if(yCord + 1 <= peaksInTheMountain[0].length-1){
			if(peaksInTheMountain[xCord][yCord+1].getPeakHeight() < peak.getPeakHeight())
				neighbours.add(peaksInTheMountain[xCord][yCord+1]);
		}
		return neighbours;
	}

	/**
	 * Building the mountain by building each peak one by one
	 */
	public void build() {
		int columns = this.peaksInTheMountain.length;
		int rows = this.peaksInTheMountain[0].length;
		for (int i = 0; i < columns; i++) {
			for (int k = 0; k < rows; k++) {
				this.peaksInTheMountain[i][k].build(this);
			}
		}
		this.isBuilt = true;
	}

	/**
	 * Printing the calculated path
	 */
	public void printTheLovelyPath(){
		if(!isBuilt){
			System.out.println("The mountain is not built yet. Build it NOW......");
		}else{
			System.out.println("The length of the path is: "+longestSkiablePath.size());
			System.out.println("The drop size is: "+steepNessOfLongestSkiablePath);
			System.out.println("You need to send mail to: "+longestSkiablePath.size()+steepNessOfLongestSkiablePath+"@redmart.com");
			for(Peak peak:longestSkiablePath){
				System.out.print("("+peak.getX()+","+peak.getY()+") "+peak.getPeakHeight()+"\t");
			}
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for( int i = 0;i < this.peaksInTheMountain.length;i++){
			for(int j = 0;j < this.peaksInTheMountain[0].length;j++){
				sb.append(peaksInTheMountain[i][j]+"\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	

}
