package dao;

import java.awt.List;
import java.util.ArrayList;

public class Wuzzuf {

	public Wuzzuf() {}
	
	public Wuzzuf(String title, String company, String location, String type, String level, String yearsExp,
			String country, String skills) {
		super();
		Title = title;
		Company = company;
		Location = location;
		Type = type;
		Level = level;
		YearsExp = yearsExp;
		Country = country;
		Skills = skills;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getYearsExp() {
		return YearsExp;
	}
	public void setYearsExp(String yearsExp) {
		YearsExp = yearsExp;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getSkills() {
		return Skills;
	}
	public void setSkills(String skills) {
		Skills = skills;
	}
	private String  Title;
	private String  Company;
	private String  Location;
	private String  Type;
	private String  Level;
	private String  YearsExp;
	private String  Country;
	private String  Skills;

	
	public static ArrayList<String> getAtrributes(){
		ArrayList<String> l = new ArrayList<String>();
		l.add("Title");
		l.add("Company");
		l.add("Location");
		l.add("Type");
		l.add("Level");
		l.add("YearsExp");
		l.add("Country");
		l.add("Skills");
		return l;
	}
	
}
