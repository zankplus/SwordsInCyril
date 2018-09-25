package fftamap;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import fftamap.*;

public class MuscadetMapLoader
{
	static FFTAMap map;
	
	public static FFTAMap getMap(boolean clientside)
	{
		map = new FFTAMap();
		
		// If loading on clientside, load all image resources
		if (clientside)
			try 
			{
				map.background = ImageIO.read(new File("resources/maps/muscadet/bg.png"));
				map.ground = ImageIO.read(new File("resources/maps/muscadet/ground.png"));
							
				map.mapObjects = new TreeSet<ForegroundObject>();
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/house1.png", 0, 0, 17, -35, 5, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/house2.png", 6, 6, 12, -19, -35, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tree1.png", 6, 0, 15, 5, 5, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tree2.png", 0, 5, 15, 5, 5, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tree3.png", 0, 6, 15, 10, 4, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tree4.png", 11, 0, 11, 11, -4, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tree5.png", 0, 11, 12, 0, 5, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tree6.png", 10, 9, 7, 0, 2, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/barrel.png", 7, 0, 12, 0, 6, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tile1.png", 2, 7, 10, 16, 1, false, FGObjectType.GROUND));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tile2.png", 14, 3, 2, 0, 1, false, FGObjectType.GROUND));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/tile3.png", 3, 13, 2, 16, 1, false, FGObjectType.GROUND));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/house3.png", 13, 6, 10, -35, -19, false, FGObjectType.OBJECT));
				map.mapObjects.add(new ForegroundObject("resources/maps/muscadet/house4.png", 6, 12, 10, -19, -19, false, FGObjectType.OBJECT));
		
			}
		catch (IOException e) { System.out.println("aaa"); }
		
		map.mapData = new FFTAMapTile[15][15];
		int[][] tiles = new int[][] {		{	0,	0,	0,	11,	11,	0,	0,	10,	10,	7,	7,	0,	7,	0,	0	},
											{	0,	0,	0,	11,	11,	10,	10,	9,	8,	7,	7,	7,	7,	0,	0	},
											{	0,	0,	0,	11,	11,	10,	10,	10,	7,	7,	7,	7,	7,	0,	0	},
											{	0,	0,	0,	11,	11,	10,	10,	7,	7,	7,	0,	0,	0,	2,	0	},
											{	11,	11,	11,	11,	0,	0,	0,	7,	7,	7,	0,	0,	0,	2,	0	},
											{	11,	11,	10,	10,	0,	0,	0,	7,	7,	6,	0,	0,	0,	2,	0	},
											{	0,	10,	10,	10,	0,	0,	0,	7,	7,	5,	0,	0,	0,	2,	0	},
											{	0,	10,	9,	7,	7,	7,	7,	7,	6,	4,	2,	2,	2,	2,	0	},
											{	10,	10,	8,	7,	7,	6,	6,	6,	6,	3,	2,	2,	2,	2,	0	},
											{	10,	10,	7,	6,	6,	6,	6,	6,	2,	2,	2,	2,	2,	2,	0	},
											{	0,	6,	6,	6,	6,	5,	4,	3,	2,	0,	2,	2,	0,	0,	0	},
											{	0,	6,	6,	0,	0,	0,	0,	2,	2,	2,	2,	2,	0,	0,	0	},
											{	6,	6,	6,	0,	0,	0,	0,	2,	2,	2,	2,	2,	0,	0,	0	},
											{	0,	0,	2,	0,	0,	0,	0,	2,	2,	2,	2,	2,	0,	0,	0	},
											{	0,	0,	0,	2,	2,	2,	2,	2,	2,	2,	0,	0,	0,	0,	0	}
		};

		
		
			
		FFTAMapTile[][] mapData = new FFTAMapTile[15][15];
		
		int tileCount = 0;
		
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				if (tiles[i][j] != 0)
				{
					mapData[i][j] = new FFTAMapTile(i, j, tiles[i][j]);
					tileCount++;
		//			System.out.println(i + "," + j + ": adding tile at " + mapData[i][j].x + ", " + mapData[i][j].y);
				}
		
		
		FFTAMapTile[] tileList = new FFTAMapTile[tileCount];
		tileCount = 0;
		
		for (int slice = 0; slice < 29; ++slice)
		{
			int z = slice < 15 ? 0 : slice - 14;
			for (int j = z; j <= slice - z; ++j)
				if (tiles[slice - j][j] != 0)
				{	
					tileList[tileCount] = mapData[slice - j][j];
					tileCount++;
				}
		}
		
		map.mapData = mapData;
		map.tileList = tileList;
		
		map.p1StartingTiles = new FFTAMapTile[] { mapData[0][10], mapData[1][10], mapData[2][10], mapData[1][11], mapData[2][11], mapData[0][12], mapData[1][12], mapData[2][12] };
		map.p2StartingTiles = new FFTAMapTile[] { mapData[12][0], mapData[12][1], mapData[12][2], mapData[11][1], mapData[11][2], mapData[10][1], mapData[10][2] };
		
		return map;
	}
}
