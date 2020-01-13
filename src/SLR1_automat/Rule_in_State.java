package SLR1_automat;

import grammar.ContextFreeGrammar;
import grammar.Rule;
import java.util.HashSet;
import FirstandFollow.FirstAndFollowClass;


public class Rule_in_State implements interfaces.Rule_in_State{
	
	private Rule rule;
	private int pointer;
	private HashSet<String> follow;
	private boolean processed;
	private ContextFreeGrammar grammar;
	
	/** Vytvori pravidlo v stave a sucasne mi urci mnozinu follow (pokial je to potrebne. to znamena, 
	 * ze bolo nacitane cele pravidlo alebo je to zaciatocne pravidlo gramatiky a chyba mu posledny symbol do rozpoznania)
	 * * @param Rule (pravidlo z gramatiky)
	 * * @param int (pointer pravidla - urcuje kolko s pravidla uz bolo nacitane)
	 * * @param gramatika
	 * * @return Pravidlo v stave
	 **/
	public Rule_in_State(Rule rule, int pointer, ContextFreeGrammar grammar )throws Exception{
		this.rule = rule;
		this.pointer = pointer;
		this.grammar = grammar;
		if(is_pointer_at_the_end()){
			set_follow();
			processed = true;
		}else if(get_pointer_element().equals("e")){
			this.pointer++;
			set_follow();
			processed = true;
		}else if(rule.getLeftSide().get(0).equals(grammar.getStartsymbol())){
			this.pointer++;
			if(is_pointer_at_the_end()){
				this.pointer--;
				follow = new HashSet<String>();
				if(grammar.getTerminals().contains(get_pointer_element())){
					follow.add(get_pointer_element());
					this.pointer++;
					processed = true;
				}else{
					follow.addAll(FirstAndFollowClass.Follow(grammar, get_pointer_element()));
				}
			}else{
				this.pointer--;
			}
		}else{
			if(grammar.getNonterminals().contains(get_pointer_element())){
				processed = false;
			}else{
				processed = true;
			}
		}
	}
	
	public HashSet<String> get_follow(){
		return null;
	}
	
	public Rule get_rule(){
		return null;
	}
	
	public boolean compareTo(Rule_in_State rule_in_state){
		return false;
	}
	
	public int get_pointer_index(){
		return 0;
	}
	
	public void set_processed(){
		
	}
	
	public boolean is_processed(){
		return false;
	}

	
	public String get_pointer_element(){
		return null;
	}
	
	public int get_next_pointer(){
		return 0;
	}
	
	public boolean is_pointer_at_the_end(){
		return false;
	}

}
