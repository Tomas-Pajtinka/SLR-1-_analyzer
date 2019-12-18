package interfaces;

import grammar.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import SLR1_automat.Rule_in_State;


public interface State {

	/** Prida priradi stavu symbol astav na, ktory sa bude na dany symbol presuvat
	 * * @param String
	 * * @param State
	 * * @throws ak sa uz ma stav nastaveny prechod na dany symbol
	 **/
	public void add_switch(String symbol, SLR1_automat.State state) throws Exception;
	
	/** Vrati zoznam pravidiel a symbolov z ktorych sa budu vytvarat nove stavzy, pravidla, 
	 * ktore sa maju pridat do rovnakeho stavu vrati v zozname
	 * * @return HashMap
	 * * @throws ak sa uz ma stav nastaveny prechod na dany symbol
	 **/
	public HashMap<String,ArrayList<Rule_in_State>> get_rules_of_next_state() throws Exception;
	
	/** Vrati zoznam symbolov a stavov, na ktore sa vieme z daneho stavu dostat
	 * * @return HashMap
	 **/
	public HashMap<String,SLR1_automat.State> get_switches();
	
	/** Vrati meno stavu
	 * * @return int
	 **/
	public int get_name();
	
	/** Vrati stav na ktory sa dostaneme na dany symbol
	 * * @param String
	 * * @return State
	 **/
	public SLR1_automat.State next_state(String symbol) throws Exception;
	
	/** Porovna stavy, ci su rovnake
	 * * @param State na porovnanie
	 * * @return true ak su rovnake, inak false
	 **/
	public boolean compareTo(SLR1_automat.State state);
	
	/** Oznaci stav ako hotovy (pri vytvoreni automatu)
	 **/
	public void set_completed();
	
	/** Zisti, ci je dany stav hotovy (pri vytvoreni automatu)
	 **/
	public boolean is_completed();
	
	/** Zisti, ci ma este stav prvidla, ktore neboli uzavrete
	 * * @return true ak ma nespracovane pravidla, inak false 
	 **/
	public boolean has_unprocessed_rules();
	
	/** Vrati pravidla, ktore este neboli uzavrete
	 * * @return Rules
	 **/
	public Rule_in_State get_unprocessed_rule();
	
	/** Prida pravidlo z gramatiky do stavu
	 * * @param Rule
	 **/
	public void add_rule(Rule rule) throws Exception;
	
	/** Zisti, ci na dany symbol sa vieme dostat do dalsieho stavu
	 * * @param String
	 * * @return true ak sa vieme dostat do dalsieho stavu, inak false
	 **/
	public boolean contains_switch(String symbol);
	
	/** Vrati symboly a k nim priradene stavy na ktore existuje prechod so sucastneho stavu 
	 * (prisposobene na lahke pridanie do tabulky ACTION)
	 * * @return HashMap
	 **/
	public HashMap<String,Rule> get_rules_to_action() throws Exception;
	
}
