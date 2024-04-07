package projects;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.dao.ProjectsDao;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
	private static Scanner scanner = new Scanner(System.in);
    private static ProjectService projectService = new ProjectService();
    // @formatter:off
    private static List<String> operations = List.of(
    		"1) Add a project",
    		"2) List projects",
    		"3) Select a project"
 );
	// @formatter:on
    
	@SuppressWarnings("static-access")
public static void main(String[] args) throws Throwable {
	
  new ProjectsApp().processUserSelections();
 

	}

private static void processUserSelections() throws Throwable {
	boolean done = false;
	while(!done) {
		try {
			int selection = getUserSelection();
			
			switch(selection) {
			case -1:
				done = exitMenu();
				break;
				
			case 1:
				createProject();
				break;
				
			case 2:
				listProject();
				break;
						
			case 3:
				selectProject();
				break;
				
			
				
				
				default:
					System.out.println("\n" + selection + "is not a valid selection. Try again.");
break;
			}
		}
		
		catch(Exception e) {
			System.out.println("\nError:" + e + "Try again.");
		}
	}
}

@SuppressWarnings("unused")
private static void selectProject() throws Throwable {
	listProject();
	Integer projectId = getIntInput("Enter a project ID to select a project");
	
	Project curProject = null;
	
	curProject = projectService.fetchProjectById(projectId);
}


private static void listProject() throws SQLException {
	List<Project> projects = projectService.fetchAllProjects();
	
	System.out.println("\nProjects:");
	
	projects.forEach(project -> System.out.println( " " + project.getProjectId() + ": " + project.getProjectName()));
	
}


private static void createProject() throws SQLException {
	String projectName = getStringInput("Enter the project name");
	BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
	BigDecimal actualHours = getDecimalInput ("Enter the actual hours");
	Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
	String notes = getStringInput("Enter the project notes");
	
	Project project = new Project();
	
	project.setProjectName(projectName);
	project.setEstimatedHours(estimatedHours);
	project.setActualHours(actualHours);
	project.setDifficulty(difficulty);
	project.setNotes(notes);
	
	System.out.println("Successfully set the project paramters");
	
	Project dbProject = projectService.addProject(project);
	System.out.println("You have successfully created project: " + dbProject);
}


private static BigDecimal getDecimalInput(String prompt) {
	String input = getStringInput(prompt);
	
	if(Objects.isNull(input)) {
		return null;
	}
	
	try {
		return new BigDecimal(input).setScale(2);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + "is not a valid decimal number.");
	}
}


private static boolean exitMenu() {
	System.out.println("Exiting the menu.");
	return true;
}


private static int getUserSelection() {
	printOperations();
	
	Integer input = getIntInput("Enter a menu selection");
	
	return Objects.isNull(input) ? -1 : input;
}


private static Integer getIntInput(String prompt) {
	String input = getStringInput(prompt);
	
	if(Objects.isNull(input)) {
		return null;
	}
	
	try {
		return Integer.valueOf(input);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valid number.");
	}
}

private static String getStringInput(String prompt) {
	System.out.print(prompt + ": ");
	String input = scanner.nextLine();
	
	return input.isBlank() ? null : input.trim();

}

private static void printOperations() {
	System.out.println( "\nThese are the available selections. Press the Enter key to quit:");
	
	operations.forEach(line -> System.out.println(" " + line));
	
	
	Object curProject = null;
	if(Objects.isNull(curProject)) {
		System.out.println("\nYou are not working with a project.");
	}
	else {
		System.out.println("\nYou are working with project: "+ curProject);
	}
}
}