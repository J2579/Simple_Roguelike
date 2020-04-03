package model;

public class Map {

	private char[][] map;
	private int[] position;
	private int rows, cols;
	private char wallChar;
	private String mapName;
	private Map[] exits;
	
	private static final int EXIT_CAP = 0xA; 
	
	public Map(char[][] map, int startX, int startY, char wall, String mapName) { //Convenience constructor
		this(map,new int[] {startX, startY}, wall, mapName);
	}
	
	public Map(char[][] map, int[] startPos, char wall, String mapName) {
		this.map = map;
		this.position = startPos;
		this.rows = map.length;
		this.cols = map[0].length;
		this.wallChar = wall;
		this.mapName = mapName;
		exits = new Map[EXIT_CAP];
//		exitID = new int[EXIT_CAP];
	}
	
	public boolean move(int rowOffset, int colOffset) {
		//Don't move off edge of map
		if(position[0] + rowOffset < 0 || position[0] + rowOffset >= rows)
			return false;
		if(position[1] + colOffset < 0 || position[1] + colOffset >= cols)
			return false;
		
		//Don't move into a wall
		if(map[position[0] + rowOffset][position[1] + colOffset] == wallChar)
			return false;
		
		char handle = map[position[0] + rowOffset][position[1] + colOffset];
		
		switch(handle) {
		
			default: //
					 break;
		}
		
		//Other movement restrictions / data handling...
		
		//Handle the map changes at this point
		if(handle >= '0' && handle <= '9') {
			int exit = Integer.parseInt("" + handle);
			Game.getInstance().setCurrentMap(exits[exit]);
			exits[exit].setPlayerLocationAbsolute(exits[exit].locateFirstOccurenceOf(handle));
		}
		else {
			//Move O.K.
			position[0] += rowOffset;
			position[1] += colOffset;
		}
		return true;
	}
	
	public void setPlayerLocationAbsolute(int[] pos) {
		position[0] = pos[0];
		position[1] = pos[1];
	}

	public int[] locateFirstOccurenceOf(char handle) {
		int[] pos = new int[2];
		for(int row = 0; row < rows; ++row) {
			for(int col = 0; col < cols; ++col) {
				if(map[row][col] == handle) {
					pos[0] = row;
					pos[1] = col;
					return pos;
				}
			}
		}
		return null;
	}

	public void registerExit(int slot, Map newMap) {
		if(exits[slot] != null)
			throw new IllegalArgumentException("Exit already occupied at: " + slot + "\n");
		exits[slot] = newMap;	
	}
	
	public int[] getPosition() {
		return position;
	}
	
	public void data() {
		return;
	}
	
	public String getMapName() {
		return mapName;
	}
	
	//Debug
	public String displayAllMapExitLinks() {
		String ret = "";
        for(int idx = 0; idx < exits.length; ++idx) {
            ret += "[" + idx + ": " + exits[idx].getMapName() + "]\n";
        }
        return ret;
	}
	
	@Override
	public String toString() {
		
		String mapString = "";
		
		for(int row = 0; row < rows; ++row) {
			mapString += "[";
			for(int col = 0; col < cols; ++col) {
				
				mapString += "'" + (row == position[0] && col == position[1] ? 'X' : map[row][col]) + "'";
				
				if(col != cols - 1)
					mapString += ", ";
			}
			mapString += "]\n";
		}
		return mapString;
	}
}
