package programs;

import managers.StudentManager;
import serializers.StudentSerializer;

public class ReadCSV {
	
	public static StudentManager loadStudentData() { //why static??
		return StudentSerializer.readStudentsFromFile("database/studentList.csv");
	}

}
