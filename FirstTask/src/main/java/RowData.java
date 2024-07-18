public class RowData { 

    private int row_id; 
    private String discipline; 
    private String season; 

    public RowData(int row_id, String discipline, String season) { 
        this.row_id = row_id; 
        this.discipline = discipline; 
        this.season = season; } 
        
    public int getRow_id() {
    	return row_id;
    }
    
    public String getDiscipline() {
    	return discipline;
    }
    
    public String getSeason() {
    	return season;
    }
    
    public void setRow_id(int row_id) {
    	this.row_id = row_id;
    }
    
    public void setDiscipline(String discipline) {
    	this.discipline = discipline;
    }
    
    public void setSeason(String season) {
    	this.season = season;
    }
} 
