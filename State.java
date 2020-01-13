package SLR1_automat;

import grammar.ContextFreeGrammar;
import grammar.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import SLR1_automat.Rule_in_State;

public class State {
	
	private int name;
	private ArrayList<Rule_in_State> rules = new ArrayList<Rule_in_State>();
	private HashMap<String,State>  switches = new HashMap<String,State>();
	private boolean completed = false;
	private ContextFreeGrammar grammar;
	
	public State(int name, ArrayList<Rule_in_State> rules, ContextFreeGrammar grammar){
		this.name = name;
		this.rules = rules;
		this.grammar = grammar;
	}
	
	public void add_switch(String symbol, State state) throws Exception{
		if(switches.containsKey(symbol)){
			throw new Exception("Rovnky prechod uz existuje.");
		}else{
			switches.put(symbol, state);
		}
	}
	
	public HashMap<String,ArrayList<Rule_in_State>> get_rules_of_next_state() throws Exception{
		HashMap<String,ArrayList<Rule_in_State>> rules_of_next_state = new HashMap<String,ArrayList<Rule_in_State>>();
		Iterator<Rule_in_State> this_rules = rules.iterator();
		while(this_rules.hasNext()){
			Rule_in_State current_rule = this_rules.next();
			if(!current_rule.is_pointer_at_the_end()){
				if(rules_of_next_state.containsKey(current_rule.get_pointer_element())){
					rules_of_next_state.get(current_rule.get_pointer_element()).add(new Rule_in_State(current_rule.get_rule(), current_rule.get_next_pointer(), grammar));
				}else{
					ArrayList<Rule_in_State> rule_list = new ArrayList<Rule_in_State>();
					rule_list.add(new Rule_in_State(current_rule.get_rule(), current_rule.get_next_pointer(), grammar));
					rules_of_next_state.put(current_rule.get_pointer_element(), rule_list);
				}
			}
		}
		return rules_of_next_state;
	}
	
	public HashMap<String,State> get_switches(){
		return switches;
	}
	
	public int get_name(){
		return name;
	}
	
	public State next_state(String symbol) throws Exception{
		if(switches.containsKey(symbol)){
			return switches.get(symbol);
		}else{
			throw new Exception("Prechod neexistuje.");
		}
	}
	
	public boolean compareTo(State state){		
		Iterator<Rule_in_State> this_rules = rules.iterator();
		int find_count = 0;
		while(this_rules.hasNext()){
			Iterator<Rule_in_State> param_rules = state.rules.iterator();
			Rule_in_State this_rule = this_rules.next();
			while(param_rules.hasNext()){
				Rule_in_State param_rule = param_rules.next();
				if(this_rule.compareTo(param_rule)){
					find_count++;
				}
			}
		}
		if(find_count == rules.size() && state.rules.size() == rules.size()){
			return true;
		}else{
			return false;
		}
	}
	
	public void set_completed(){
		this.completed = true;
	}
	
	public boolean is_completed(){
		return completed;
	}
	
	public boolean has_unprocessed_rules(){
		Iterator<Rule_in_State> this_rules = rules.iterator();
		while(this_rules.hasNext()){
			Rule_in_State current_rule = this_rules.next();
			if(!current_rule.is_processed()){
				return true;
			}
		}
		return false;
	}
	
	public Rule_in_State get_unprocessed_rule(){
		Iterator<Rule_in_State> this_rules = rules.iterator();
		while(this_rules.hasNext()){
			Rule_in_State current_rule = this_rules.next();
			if(!current_rule.is_processed()){
				return current_rule;
			}
		}
		return null;
	}
	
	public void add_rule(Rule rule) throws Exception{
		rules.add(new Rule_in_State(rule, 0, this.grammar));
	}
	
	public boolean contains_switch(String symbol){
		return switches.containsKey(symbol);
	}
	
	public HashMap<String,Rule> get_rules_to_action() throws Exception{
		HashMap<String,Rule> return_rules = new HashMap<String,Rule>();
		Iterator<Rule_in_State> this_rules = rules.iterator();
		while(this_rules.hasNext()){
			Rule_in_State current_rule = this_rules.next();
			if(current_rule.get_follow() != null){
				Iterator<String> follow_symbols = current_rule.get_follow().iterator();
				while(follow_symbols.hasNext()){
					String follow = follow_symbols.next();
					if(return_rules.containsKey(follow)){
						throw new Exception("Grammar is not SLR(1) grammar.");
					}else{
						return_rules.put(follow, current_rule.get_rule());	
					}
				}
			}else{
				if(grammar.getTerminals().contains(current_rule.get_pointer_element())){
					if(return_rules.containsKey(current_rule.get_pointer_element())){
						if(return_rules.get(current_rule.get_pointer_element()) != null){
							throw new Exception("Grammar is not SLR(1) grammar.");							
						}
					}else{
						return_rules.put(current_rule.get_pointer_element(), null);	
					}
				}
			}
		}
		return return_rules;
	}
}
