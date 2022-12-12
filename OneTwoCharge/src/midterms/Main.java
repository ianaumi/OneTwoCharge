/*

Name: Ian M. Lumanog
SECTION: BSCS1B-123
Date: 11/12/2022
TODO:
Develop a game program One, two, charge. it
is a 1 v 1 game that has a main goal of getting 
the opponent’s health to 0. 

 */
package midterms;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	// new UnitObjects
	static UnitObjects player = new Player();
	static UnitObjects bot    = new Enemy();
	// skillset of players
	static Skillset playerSkills = new Skillset();
	static Skillset botSkills = new Skillset();
	static int round = 0;
	static String choice = "";
	
	public static void main(String[] args) {
			
		do {
			round = 0;
			clrscr();
			displayWelcomeScreen();
			clrscr();
			do {
			
				displayTitle();
			
				// displays stats of both players
				displayStats();
		
				// displays skills of both players
				displaySkills();
			
				// main battle interaction
				battleSystem();
			
				clrscr();
			
				round ++;
			}while (!(player.hp <= 0 || bot.hp <= 0));
			
			displayGameOver();
			displayStats();
			displayPlayAgain();
			
			if(choice.equals("N")) {
				System.out.printf("%28s\n","Goodbye! ");
				break;
			}
		}while(!choice.equals("N"));
	}
	
	// clears the screen
	public static void clrscr(){
		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
	}
	
	// getting input from user
	static char getKeyFrom(UnitObjects player,Skillset skill) {
		char keyInput = ' ';
		List<Character> keys = skill.keys();
		
		// input validation
		do {
			
			System.out.print("\nYour choice>> ");
			keyInput = sc.next().charAt(0);
			
		}while(!keys.contains(keyInput));
		
		return keyInput;
	}
	
	// random skill generator for the bot
	static char randomSkill(Skillset skill) {
		char randomSkill = ' ';
		Random random = new Random();
		
		do {
			int skillz = random.nextInt(skill.keys().size());
			randomSkill = skill.keys().get(skillz);
			if(skill.keys().contains(randomSkill)) {
				break;
			}	
		}while(!skill.keys().contains(randomSkill));
		
		return randomSkill;
	}
	
	// creates a new player
	static void setName(UnitObjects unitObject) {
		String name = " ";
		
		// validates if it's valid
		while (true) {
			name = sc.nextLine();
			if (name.matches("^.{4}$")) {break;}
			else System.out.print("Valid name please!\n>> ");
		}
		// creates new player with stats & name
		unitObject.createPlayer(name);
	}
	
	// main battle interaction
	static void battleSystem() {
		char playerAction = getKeyFrom(player,playerSkills);
		char botAction = randomSkill(botSkills);
		
		// skill chosen by the player
		player.useSkill(playerAction);
		bot.useSkill(botAction);
		
		// shield filter
		shieldFilter(playerAction,player,botAction,bot);
		shieldFilter(botAction,bot,playerAction,player);
		
		// amen filter
		amenFilter(playerAction,player,bot);
		amenFilter(botAction,bot,player);
		
		// damage calculation
		int totalDamage = 0;
		if(player.dmg >= bot.dmg) {
			totalDamage = player.dmg - bot.dmg;
			bot.hp -= totalDamage;
		}
		else if (bot.dmg >= player.dmg) {
			totalDamage = bot.dmg - player.dmg;
			player.hp -= totalDamage;
		}
	}
	
	// filters the shield skill
	static void shieldFilter(char unitAction,UnitObjects unit,char targetAction,UnitObjects target) {
		if (unitAction == 'W') {
			if (!(targetAction == 'X')) {unit.hp += target.dmg;}
			else target.hp -= unit.dmg;
		}
	}
	
	// filters the amen skill
	static void amenFilter(char unitAction,UnitObjects unit, UnitObjects target) {
		if (unitAction == 'C') {
			unit.hp += target.dmg;
		}
	}
	
	// FIXME small bug. (no time)
	static void playAgain() {
		System.out.printf("%27s","Your choice: ");
		while(true) {
			choice = sc.nextLine();
			if(choice.matches("[YN]")) {break;}
		}
	}

	// all of the display functions
	static void displayWelcomeScreen() {

		displayHeadLine();
		System.out.print("\t     ONE, TWO, CHARGE!!\n");
		System.out.print("\t  Welcome to the game ____\n");
		System.out.print("\tEnter your name [4 letters]\n>> ");
		setName(player);
		System.out.print("\tEnter bot's name [4 letters]\n>> ");
		setName(bot);
		displayLine();
		
	}
	
	static void displayTitle() {
		displayHeadLine();
		System.out.printf("%33s\n","ONE, TWO, CHARGE!!");
		System.out.printf("%32s\n","Current round: "+ round);
		displayLine();
	}	
	
	static void displayStats() {
		
		System.out.println("\n\n+=================================================+");
		System.out.format("%-8s %28s", "Player stats","Bot stats");
		System.out.println(); // line break
		System.out.format("%-13s %28s", "NAME: " + player.name, "NAME: "+  bot.name);	
		System.out.println(); // line break
		System.out.format("%-9s %28s", "HP: "    + player.hp,   "HP: "  + bot.hp);	
		System.out.println(); // line break
		System.out.format("%-8s %28s", "SP: "    + player.sp,   " SP: " + bot.sp);
		System.out.println(); // line break
		System.out.format("%-20s %28s", "LAST MOVE: " + player.action, "LAST MOVE: " + bot.action);	
		System.out.println(); // line break
		System.out.println("+=================================================+\n\n");
		
	}
	
	// displays the current skills available from both players
	static void displaySkills() {
		
		List<String> botSkill = botSkills.skillSet(bot.sp);
		List<String> playerSkill = playerSkills.skillSet(player.sp);
	
		displayLine();
		System.out.println("Player Skillset");
		for (String i : playerSkill) {
			System.out.print(i+" ");
		}
		
		System.out.println("\n\nBot Skillset");
		for (String j : botSkill) {
			System.out.print(j+" ");
		}
		System.out.print("\n");
		displayLine();
	}
	
	static void displayGameOver() {
		displayHeadLine();
		System.out.printf("%28s\n","GAME OVER!");
		if (player.hp > bot.hp) {
			  System.out.printf("%28s\n",player.name+"WINS!");
		}else System.out.printf("%28s\n",bot.name+" WINS!");
		System.out.printf("%31s\n","FLAWLESS VICTORY");
		displayLine();
	}
	
	static void displayPlayAgain() {
		System.out.println("==================================================\n");
		System.out.printf("%30s\n","What a nice game!");
		System.out.printf("%37s\n\n","Would you like to play again?");
		System.out.printf("%19s%19s\n\n","[Y] Play again","[N] Quit");
		playAgain();
		displayLine();
	}
	
	// OTHERS
	static void displayHeadLine() {
		System.out.println("    |\\__/,|   (`\\\r\n"
				+ "  _.|o o  |_   ) )\r\n"
				+ "=(((===(((=======================================\n");	
	}
	
	static void displayLineB() {
		System.out.println("\n=================================================\n");
	}
	
	static void displayLine() {
		System.out.println("==================================================");
	}
}








