package projects.entity;

import java.math.BigDecimal;
import java.util.List;


public class Project {
	private Integer ProjectId;
	private String ProjectName;
	private BigDecimal EstimatedHours;
	private BigDecimal ActualHours;
	private Integer Difficulty;
	private String Notes;
	
	public void setProjectName(String projectName) {
		// TODO Auto-generated method stub
		ProjectName = projectName;
	}

	public void setEstimatedHours(BigDecimal estimatedHours) {
		// TODO Auto-generated method stub
		EstimatedHours = estimatedHours;
		
	}

	public void setActualHours(BigDecimal actualHours) {
		// TODO Auto-generated method stub
		ActualHours = actualHours;
	}

	public void setDifficulty(Integer difficulty) {
		// TODO Auto-generated method stub
		Difficulty = difficulty;
	}

	public void setNotes(String notes) {
		// TODO Auto-generated method stub
		Notes = notes;
	}

	public void setProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		ProjectId = projectId;
	}

	public String getProjectName() {
		// TODO Auto-generated method stub
		return ProjectName;
	}

	public BigDecimal getEstimatedHours() {
		// TODO Auto-generated method stub
		return EstimatedHours;
	}

	public BigDecimal getActualHours() {
		// TODO Auto-generated method stub
		return ActualHours;
	}

	public Integer getDifficulty() {
		// TODO Auto-generated method stub
		return Difficulty;
	}

	public String getNotes() {
		// TODO Auto-generated method stub
		return Notes;
	}

	public List<Project> getSteps() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Project> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Project> getMaterials() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getProjectId() {
		// TODO Auto-generated method stub
		return ProjectId;
	}

}
