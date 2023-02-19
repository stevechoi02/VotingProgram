package main.dao;

public class ServerVO {
    private final ServerDAO dao = new ServerDAO();
    
    private String selectedCand;

    public ServerVO(){

    }

	public String getSelectedCand() {
		return selectedCand;
	}

	public void setSelectedCand(String selectedCand) {
		this.selectedCand = selectedCand;
	}


    
}
