package programs;

import managers.*;
import serializers.FYPCoordinatorSerializer;
import serializers.ProjectSerializer;
import serializers.StudentSerializer;
import serializers.SupervisorSerializer;

public class ReadCSV {
	
	public static StudentManager loadStudentData() { //why static?? -- cause in mainApp u declare student manager as static
		return StudentSerializer.readStudentsFromFile("database/studentList.csv");
	}
	
	public static SupervisorManager loadSupervisorData() {
		return SupervisorSerializer.readSupervisorsFromFile("database/facultyList.csv");
	}
	
	public static ProjectManager loadProjectData(SupervisorManager supervisorManager) {
		return ProjectSerializer.readProjectsFromFile("database/projects.csv", supervisorManager);
	}
	
	public static FYPCoordinatorManager loadFYPCoordinator(SupervisorManager supervisorManager) {
		return FYPCoordinatorSerializer.readCoordinatorFromFile("database/fypCoordinator.csv", supervisorManager);
	}

}
