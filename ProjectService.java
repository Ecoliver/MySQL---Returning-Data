package projects.service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectsDao;
import projects.entity.Project;

public class ProjectService {
	@SuppressWarnings("rawtypes")
	private ProjectsDao projectDao = new ProjectsDao();
	
public Project addProject(Project project) throws SQLException {
  return projectDao.insertProject(project);
	}



@SuppressWarnings("unchecked")
public List<Project> fetchAllProjects() throws SQLException {
return ProjectsDao.fetchAllProjects();
}

@SuppressWarnings("unchecked")
public Project fetchProjectById(Integer projectId) throws Throwable {
	return (Project) projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
"Project with project ID=" + " does not exist."));
}
}
