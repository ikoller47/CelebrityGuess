import java.util.*;

class Player {
	
	private ArrayList<String> celebritiesAdded;
	private ArrayList<String> celebritiesGuessed;
	private String name;
	private int treePosition;
	
	Player(String name){
		this.name = name;
		this.celebritiesAdded = new ArrayList<String>();
		this.celebritiesGuessed = new ArrayList<String>();
		this.treePosition = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<String> getCelebritiesAdded(){
		return celebritiesAdded;
	}
	
	public ArrayList<String> getCelebritiesGuessed(){
		return celebritiesGuessed;
	}
	
	public int getTreePosition(){
		return treePosition;
	}
	
	public void setCelebritiesAdded(ArrayList<String> celebritiesAdded){
	     this.celebritiesAdded = celebritiesAdded;
	}
	
	public void setCelebritiesGuessed(ArrayList<String> celebritiesGuessed){
	     this.celebritiesGuessed = celebritiesGuessed;
	}
	
	public void setTreePosition(int treePosition){
	     this.treePosition = treePosition;
	}
	
	
}