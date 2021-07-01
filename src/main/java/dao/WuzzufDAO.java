package dao;

import java.util.ArrayList;

import joinery.DataFrame;

public interface WuzzufDAO {
	DataFrame ReadFile(String filenme);
	ArrayList<Wuzzuf> getALL(DataFrame df);
}
