package engine;
import java.util.ArrayList;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.world.*;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Cover;
import model.abilities.AreaOfEffect;
import model.effects.Disarm;
import model.effects.Dodge;
import model.effects.Effect;
import model.effects.Embrace;
import model.effects.PowerUp;
import model.effects.Root;
import model.effects.Shield;
import model.effects.Shock;
import model.effects.Silence;
import model.effects.SpeedUp;
import model.effects.Stun;

public class Game{
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean firstLeaderAbilityUsed; 
    private boolean secondLeaderAbilityUsed;
    private Object[][] board;
    private static ArrayList<Champion> availableChampions;
    private static ArrayList<Ability> availableAbilities;
    private PriorityQueue turnOrder;
    private static final int BOARDHEIGHT=5;
    private static final int BOARDWIDTH=5;
	public Game(Player first,Player second){
        firstPlayer = first;
        secondPlayer = second;
        board = new Object[BOARDHEIGHT][BOARDWIDTH];
        availableChampions=new ArrayList<Champion>();
        availableAbilities=new ArrayList<Ability>();
        turnOrder= new PriorityQueue(6);
//        firstLeaderAbilityUsed=false;
//        secondLeaderAbilityUsed=false;
        placeChampions();
        placeCovers();
    }
   public Player getFirstPlayer(){
       return firstPlayer;
   }
    public Player getSecondPlayer(){
         return secondPlayer;
    }
    public boolean isFirstLeaderAbilityUsed(){
        return firstLeaderAbilityUsed;
    }
    public boolean isSecondLeaderAbilityUsed(){
        return secondLeaderAbilityUsed;
    }
    public Object[][] getBoard(){
        return board;
    }
    public static ArrayList<Champion> getAvailableChampions(){
        return availableChampions;
    }
    public static ArrayList<Ability> getAvailableAbilities(){
        return availableAbilities;
    }
    public PriorityQueue getTurnOrder(){
        return turnOrder;
    }
    public static int getBoardheight() {
  		return BOARDHEIGHT;
  	}
  	public static int getBoardwidth() {
  		return BOARDWIDTH;
  	}
    public void placeChampions(){
        int i=1;
        int j=1;
        for(Champion c:firstPlayer.getTeam()){
        	board[0][i]=c;
            c.setLocation(new Point(0,i));
            
            i++;
        }
        for(Champion c:secondPlayer.getTeam()){
        	board[BOARDHEIGHT-1][j]=c;
            c.setLocation(new Point(BOARDHEIGHT-1,j));
            j++;
        }
    }
    private void placeCovers(){
    	int ctr=0;
        while(ctr<5){
            int i = ((int)(Math.random()*(BOARDWIDTH-2)))+1;
            int j = (int)(Math.random()*BOARDHEIGHT);
            if(board[i][j]==null){
                board[i][j]= new Cover(i,j);
        }
            ctr++;
        
    }
    }
    public static void loadAbilities(String filePath) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Ability a = null;
			AreaOfEffect ar = null;
			switch (content[5]) {
			case "SINGLETARGET":
				ar = AreaOfEffect.SINGLETARGET;
				break;
			case "TEAMTARGET":
				ar = AreaOfEffect.TEAMTARGET;
				break;
			case "SURROUND":
				ar = AreaOfEffect.SURROUND;
				break;
			case "DIRECTIONAL":
				ar = AreaOfEffect.DIRECTIONAL;
				break;
			case "SELFTARGET":
				ar = AreaOfEffect.SELFTARGET;
				break;

			}
			Effect e = null;
			if (content[0].equals("CC")) {
				switch (content[7]) {
				case "Disarm":
					e = new Disarm(Integer.parseInt(content[8]));
					break;
				case "Dodge":
					e = new Dodge(Integer.parseInt(content[8]));
					break;
				case "Embrace":
					e = new Embrace(Integer.parseInt(content[8]));
					break;
				case "PowerUp":
					e = new PowerUp(Integer.parseInt(content[8]));
					break;
				case "Root":
					e = new Root(Integer.parseInt(content[8]));
					break;
				case "Shield":
					e = new Shield(Integer.parseInt(content[8]));
					break;
				case "Shock":
					e = new Shock(Integer.parseInt(content[8]));
					break;
				case "Silence":
					e = new Silence(Integer.parseInt(content[8]));
					break;
				case "SpeedUp":
					e = new SpeedUp(Integer.parseInt(content[8]));
					break;
				case "Stun":
					e = new Stun(Integer.parseInt(content[8]));
					break;
				}
			}
			switch (content[0]) {
			case "CC":
				a = new CrowdControlAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), e);
				break;
			case "DMG":
				a = new DamagingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
				break;
			case "HEL":
				a = new HealingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
				break;
			}
			availableAbilities.add(a);
			line = br.readLine();
		}
		br.close();
	}

	public static void loadChampions(String filePath) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Champion c = null;
			switch (content[0]) {
			case "A":
				c = new AntiHero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;

			case "H":
				c = new Hero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;
			case "V":
				c = new Villain(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;
			}

			c.getAbilities().add(findAbilityByName(content[8]));
			c.getAbilities().add(findAbilityByName(content[9]));
			c.getAbilities().add(findAbilityByName(content[10]));
			availableChampions.add(c);
			line = br.readLine();
		}
		br.close();
	}

	private static Ability findAbilityByName(String name) {
		for (Ability a : availableAbilities) {
			if (a.getName().equals(name))
				return a;
		}
		return null;
	}
}
    
    




