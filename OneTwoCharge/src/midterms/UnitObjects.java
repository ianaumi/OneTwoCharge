package midterms;

public class UnitObjects {
	String name;
	String action;
	int hp;
	int sp;
	int dmg;
	
	public void createPlayer(String name) {
		hp  = 10;
		sp  = 0;
		dmg = 0;
		action = "";
		this.name = name;
	}
	
	public void useSkill(char key) {
		dmg = 0;
		
		if(key == 'Q') { // charge
			sp += 1;
			action = "Charge";
		}
				
	 	if(key == 'W') { // shield
	 		sp -= 1;
	 		action = "Shield";
		}
	 			
	 	if(key == 'E') { // wave
			dmg = 2;
			sp -= 1;
			action = "Wave";
		}

	 	if(key == 'R') { // ax
	 		dmg = 3;
	 		sp -= 2;
	 		action = "Ax";
	 	}
				
	 	if(key == 'X') { // kame
	 		dmg = 6;
	 		sp -= 4;
	 		action = "Kamekameha";
	 	}
				
	 	if(key == 'C') { // amen 
	 		hp += 5;
	 		sp -= 5	;
	 		action = "Amen";
	 	}
	}	
}
