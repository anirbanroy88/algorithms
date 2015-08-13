package com.anirban.skiing.singapore;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a peak in the skiing range
 * 
 * @author anirbanroy
 *
 */
public class Peak {

	private int x;
	private int y;
	private int peakHeight;
	private List<Peak> pathToTheLowestReachablePoint;
	private boolean isTraversed;
	private boolean isBuilt;

	public Peak(int x, int y, int peakHeight) {
		this.x = x;
		this.y = y;
		this.peakHeight = peakHeight;
		this.pathToTheLowestReachablePoint = new ArrayList<Peak>();
		pathToTheLowestReachablePoint.add(this);
		isTraversed = false;
		isBuilt = false;
	}

	/**
	 * @return x co-ordinate of the peak on the array
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return x co-ordinate of the peak on the array
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return Height of the peak
	 */
	public int getPeakHeight() {
		return peakHeight;
	}

	/**
	 * @return If the peak has already been visited
	 */
	public boolean isTravered() {
		return this.isTraversed;
	}

	/**
	 * @return If the peak has been built completely. A peak is built completely
	 *         only when a path from the peak to the lowest possible peak
	 *         reachable has been constructed.
	 */
	public boolean isBuilt() {
		return this.isBuilt;
	}

	/**
	 * @return The length of the path to the lowest possible reachable peak
	 */
	public int pathLenthToTheLowestReachablePeak() {
		return this.pathToTheLowestReachablePoint.size();
	}

	/**
	 * @return The depth of the path. Difference between the height of the peak
	 *         and the height of the lowest reachable peak
	 */
	public int getSteepness() {
		Peak lowestPeakOfThePath = this.pathToTheLowestReachablePoint
				.get(this.pathToTheLowestReachablePoint.size() - 1);
		return this.peakHeight - lowestPeakOfThePath.getPeakHeight();
	}

	/**
	 * @param Build the peak recursively
	 * 
	 */
	public void build(Mountain s) {
		this.isTraversed = true;
		if (!isBuilt) {
			Mountain mountain = (Mountain) s;
			List<Peak> neighbouringNodes = mountain.getNeighbouringPeaks(this);
			int currentLongestPathToTheBottom = 0;
			int spreadOfNextPath = 0;
			Peak nextNodeToBeVisited = null;
			for (Peak neighbour : neighbouringNodes) {
				if (!neighbour.isTraversed) {
					neighbour.build(mountain);
				}
				if (neighbour.isBuilt()) {
					if (neighbour.pathLenthToTheLowestReachablePeak() > currentLongestPathToTheBottom) {
						nextNodeToBeVisited = neighbour;
						currentLongestPathToTheBottom = neighbour.pathLenthToTheLowestReachablePeak();
						spreadOfNextPath = neighbour.getSteepness();
					} else if (neighbour.getSteepness() == currentLongestPathToTheBottom) {
						if (spreadOfNextPath < neighbour.getSteepness()) {
							nextNodeToBeVisited = neighbour;
							spreadOfNextPath = neighbour.getSteepness();
						}
					}
				}
			}

			if (nextNodeToBeVisited != null) {
				this.pathToTheLowestReachablePoint.addAll(nextNodeToBeVisited.pathToTheLowestReachablePoint);
				mountain.setLongestSkiablePath(pathToTheLowestReachablePoint);
			}
			this.isBuilt = true;
		}

	}

	@Override
	public String toString() {
		return String.valueOf(this.peakHeight);
	}

}
