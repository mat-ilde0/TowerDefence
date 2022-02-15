package helperPackage;

import java.util.ArrayList;

public class Utils {

	/**
	 * @param list ArrayList di partenza
	 * @param ySize numero righe array bidimesionale
	 * @param xSize numero colonne array 
	 * @return array bidimensionale estratto dall'ArrayList di partenza
	 */
	public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize){
		int[][] newArr = new int[ySize][xSize];
		
		for(int j = 0; j < newArr.length; j++)
			for(int i = 0; i < newArr[j].length; i++) {
				int index = j * ySize + i;
				newArr[j][i] = list.get(index);
			}
		return newArr;
	}
	
	public static int[] TwoDin1DArr(int[][] twoDArr){
		int[] oneDArr = new int[twoDArr.length * twoDArr[0].length];
		
		for(int j = 0; j < twoDArr.length; j++)
			for(int i = 0; i < twoDArr[j].length; i++) {
				int index = twoDArr.length * j + i;
				oneDArr[index] = twoDArr[j][i];
			}
		return oneDArr;
	}
}
