package ch04.ex04_02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TextTable {
	private String[][] textTable;
    private StringProperty[][] textProperty;

    public TextTable(int columns, int rows){
    	textTable = new String[columns][rows];
    	textProperty = new StringProperty[columns][rows];
    	for(String[] column : textTable){
    		for(int i = 0; i < column.length; i++){
    			column[i] = "default" + i;
    		}
    	}
    }
    
    public final StringProperty textProperty(int column, int row) {
        if (textProperty[column][row] == null) {
            textProperty[column][row] = new SimpleStringProperty(textTable[column][row]);
        }
        return textProperty[column][row];
    }

    public final String getText(int column, int row) {
        return textProperty[column][row] == null ? textTable[column][row] : textProperty[column][row].get();
    }
    
    public final String getTextFromTextTable(int column, int row){
    	return textTable[column][row];
    }
    
    public final StringProperty getTextProperty(int column, int row){
    	StringProperty property = textProperty[column][row];
    	if(property == null){
    		property = new SimpleStringProperty("null");
    	}
    	return property;
    }
    
    public static void main(String[] args){
    	final int COLUMNS = 3;
    	final int ROWS = 4;
    	TextTable tt = new TextTable(COLUMNS, ROWS);
    	
    	//textを表示
    	System.out.println("show default");
    	System.out.println(tt.getText(0, 0));
    	System.out.println(tt.getTextFromTextTable(0, 0));
    	System.out.println(tt.getTextProperty(0, 0).get());
    	    	
    	//プロパティにアクセス
    	System.out.println("Access property - set text");
    	tt.textProperty(0, 0);
    	System.out.println(tt.getText(0, 0));
    	System.out.println(tt.getTextFromTextTable(0, 0));
    	System.out.println(tt.getTextProperty(0, 0).get());
    }
    
}
