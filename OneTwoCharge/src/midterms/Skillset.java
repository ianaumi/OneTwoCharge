package midterms;

import java.util.ArrayList;

public class Skillset {
	ArrayList<String> skills = new ArrayList<String>();
	ArrayList<Character> keys = new ArrayList<Character>();
	
	// skill filtering if unit has enough skillpoint
	public ArrayList<String> skillSet(int skillPoint) {
		skills.clear();
		keys.clear();
		
		if(skillPoint >= 5) {
			skills.add("[C] Amen");
			keys.add('C');
		}
		if(skillPoint >= 4) {
			skills.add("[X] Kame");
			keys.add('X');
		}
		if(skillPoint >= 2) {
			skills.add("[R] Ax");
			keys.add('R');
		}
		if(skillPoint >= 1) {
			skills.add("[E] Wave");
			keys.add('E');
		}
		if(skillPoint >= 1) {
			skills.add("[W] Shield");
			keys.add('W');
		}
		if(skillPoint >= 0) {
			skills.add("[Q] Charge");
			keys.add('Q');
		}
		return skills;
	}
	
	// keys the user can press to use the available skill
	public ArrayList<Character> keys() {
		return keys;
	}
}
