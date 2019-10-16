package util;

import java.util.HashMap;

public class CONSTANTS {

	public static final String FILE_PATH = "C:\\\\Users\\\\14382\\\\riskdemo.txt";
	public static final String FILE_NAME = "riskdemo.txt";
	public static final HashMap<Integer,Integer> NO_PLAYER_ARMIES=new HashMap<Integer, Integer>();
	static
	{		
		NO_PLAYER_ARMIES.put(2, 40);
		NO_PLAYER_ARMIES.put(3, 35);
		NO_PLAYER_ARMIES.put(4, 30);
		NO_PLAYER_ARMIES.put(5, 25);
		NO_PLAYER_ARMIES.put(6, 20);
	}
}
