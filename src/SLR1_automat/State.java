package SLR1_automat;

import grammar.ContextFreeGrammar;
import grammar.Rule;
import java.util.ArrayList;
import java.util.HashMap;
import SLR1_automat.Rule_in_State;

public class State implements interfaces.State{
	
	private int name;
	private ArrayList<Rule_in_State> rules = new ArrayList<Rule_in_State>();
	private HashMap<String,State>  switches = new HashMap<String,State>();
	private boolean completed = false;
	private ContextFreeGrammar grammar;
	
	/** Vytvori stav
	 * * @param int (meno stavu)
	 * * @param  ArrayList (Rule)
	 * * @return gramatika
	 **/
	public State(int name, ArrayList<Rule_in_State> rules, ContextFreeGrammar grammar){

	}
	
	public void add_switch(String symbol, State state) throws Exception{

	}
	
	public HashMap<String,ArrayList<Rule_in_State>> get_rules_of_next_state() throws Exception{
		return null;
	}
	
	public HashMap<String,State> get_switches(){
		return null;
	}
	
	public int get_name(){
		return 0;
	}
	
	public State next_state(String symbol) throws Exception{
		return null;
	}
	
	public boolean compareTo(State state){		
		return false;
	}
	
	public void set_completed(){
		
	}
	
	public boolean is_completed(){
		return false;
	}
	
	public boolean has_unprocessed_rules(){
		return false;
	}
	
	public Rule_in_State get_unprocessed_rule(){
		return null;
	}
	
	public void add_rule(Rule rule) throws Exception{

	}
	
	public boolean contains_switch(String symbol){
		return false;
	}
	
	public HashMap<String,Rule> get_rules_to_action() throws Exception{
		return null;
	}
}
