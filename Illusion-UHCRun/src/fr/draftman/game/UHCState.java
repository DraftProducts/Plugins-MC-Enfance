package fr.draftman.game;

public enum UHCState {
	
	//ON ENUMERE LES DIFFERENTS STATUTS DE LA GAME POSSIBLE
	WAIT(true), GAME(false) , GAMEPVP(false), FINISH(false), PREGAME(false);
	
	//ON PLACE 2 FIELD
	private boolean canJoin;
	private static UHCState currentState;
	
	UHCState(boolean canJoin){
		this.canJoin = canJoin;
	}
	
	//ON VERIFIE SI LES JOUEURS PEUVENT REJOINDRE LE JEU SUIVANT L ENUMERATION AFFICHER
	public boolean canJoin(){
		return canJoin;
	}
	
	//ON CHANGE LE STATUT DU JEU
	public static void setState(UHCState state){
		UHCState.currentState = state;
	}
	
	//ON VERIFIE SUIVANT LE PARAM CHOISIT QUE LE JEU EST LANCER OU NON
	public static boolean isState(UHCState state){
		return UHCState.currentState == state;
	}
	
	//ON RECUP LE STATUT ACTUEL DU JEU
	public static UHCState getState(){
		return currentState;
	}
	
	

}
