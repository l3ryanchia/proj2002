package programs;

import managers.*;
import serializers.FYPCoordinatorSerializer;
import serializers.ProjectSerializer;
import serializers.StudentSerializer;
import serializers.SupervisorSerializer;

public class ReadCSV {
	
	public StudentManager loadStudentData() { //why static?? -- cause in mainApp u declare student manager as static
		return StudentSerializer.readStudentsFromFile("database/studentList.csv");
	}
	
	public SupervisorManager loadSupervisorData() {
		return SupervisorSerializer.readSupervisorsFromFile("database/facultyList.csv");
	}
	
	public ProjectManager loadProjectData(SupervisorManager supervisorManager) {
		return ProjectSerializer.readProjectsFromFile("database/projects.csv", supervisorManager);
	}
	
	public FYPCoordinatorManager loadFYPCoordinator(SupervisorManager supervisorManager) {
		return FYPCoordinatorSerializer.readCoordinatorFromFile("database/fypCoordinator.csv", supervisorManager);
	}

}
