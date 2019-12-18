package SLR1_analyzer;

import grammar.Rule;
import interfaces.SLR1_ACTION;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import SLR1_automat.State;

public class ACTION implements SLR1_ACTION{
	
	private ArrayList<HashMap<String,Rule>> table = new ArrayList<HashMap<String,Rule>>();
	/*
	 * Vytvori tabulku ACTION a ulozi ju table
	 */
	public ACTION(ArrayList<State> states, HashSet<String> terminals) throws Exception{
		
	}
	
	
	public Rule action(int state, String symbol) throws Exception{
		return null;
	}
}
