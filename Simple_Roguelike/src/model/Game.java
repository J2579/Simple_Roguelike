package model;

public class Game {
	
	//Data and shit
	private static char[][] charMap1 = {
		{'_','_','_','_','_','_','_'},	
		{'_','|','|','|','_','_','_'},	
		{'_','|','0','_','_','_','_'},
		{'_','|','|','|','_','_','_'},
		{'_','_','_','_','_','_','_'},
	};
	
	private static char[][] charMap2 = {
		{'_','_','_'},
		{'_','_','0'},
		{'_','_','_'},
	};
	
	private static Map map1 = new Map(charMap1, 0, 0, '|', "TEST_MAP1");
	private static Map map2 = new Map(charMap2, 0, 0, '|', "TEST_MAP2");
	
	//Cool design patterns
	private static Game instance = null;
	
	public static Game getInstance() {
		if(instance == null)
			instance = new Game();
		return instance;
	}
	
	private Map currentMap;
	
	private Game() {
		currentMap = map1; //lol
		map1.registerExit(0, map2); //Bind warp tile '0' on map 1 to associated warp tile on map 2
		map2.registerExit(0, map1); //Bind warp tile '0' on map 2 to associated warp tile on map 1
	}
	
	public void moveUp() {
		currentMap.move(-1, 0);
	}

	public void moveDown() {
		currentMap.move(1, 0);
	}
	public void moveLeft() {
		currentMap.move(0, -1);
	}

	public void moveRight() {
		currentMap.move(0, 1);
	}
	
	public String getMapDisplayString() {
		return currentMap.toString();
	}
	
	public void getMapDataRaw() { //TODO: UI ELEMENTS
		//return currentMap.data();
	}
	
	public String getMapName() {
		return currentMap.getMapName();
	}
	
	public int[] getPosition() {
		return currentMap.getPosition();
	}
	
	public void setCurrentMap(Map map) {
		this.currentMap = map;
	}

	public void saveAndShutdownComponents() {
		System.out.println("Thanks for playing!");
		//TODO ....
	}
}