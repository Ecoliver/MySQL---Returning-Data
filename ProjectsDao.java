package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.mysql.cj.xdevapi.PreparableStatement;

import jdk.jfr.Category;
import provided.util.DaoBase;
import projects.entity.Project;
import projects.exception.DbException;

@SuppressWarnings("unused")
public class ProjectsDao<Step, Material> extends DaoBase{
	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "projects.project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";
	private static final List<Project> project = null;
	private static Connection conn;


public Project insertProject(Project project) throws SQLException {
	
	try (Connection conn = DbConnection.getConnection()){
		startTransaction(conn);
		String projectName = project.getProjectName();
		String estimatedHours = project.getEstimatedHours().toString();
		String actualHours = project.getActualHours().toString();
		String difficulty = project.getDifficulty().toString();
		String notes = project.getNotes();
		
		String sql ="INSERT INTO " + PROJECT_TABLE + " "
			+ "(project_name, estimated_hours, actual_hours, difficulty, notes)"
			+ " VALUES "
			+ "('" + projectName + "', '" + estimatedHours + "', '" + actualHours + "', '" + difficulty + "', '" + notes + "')";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		return project;
	} catch (Exception e) {
		System.out.println(e);
	}
	return project; 
}
	


public static List<Project> fetchAllProjects() throws SQLException {
	String sql = "SELECT * FROM " + PROJECT_TABLE + " ORDER BY project_name";
	List<Project> projects =new LinkedList<>();
	
try (Connection conn = DbConnection.getConnection()){	
	
	startTransaction(conn);
	
	
	
		PreparedStatement stmt = conn.prepareStatement(sql); 
	
		
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				projects.add(extract(rs, Project.class));
			}
} catch (Exception e) {
	System.out.println(e);
}
	
	return projects;


	
}


@SuppressWarnings("unchecked")
public Optional<Project> fetchProjectById(Integer projectId) {
	String sql = "SELECT * FROM" + PROJECT_TABLE + "WHERE project_id = ?";
	
try(Connection conn = DbConnection.getConnection()) {	
	startTransaction(conn);
	
	try {
		Project project = null ;
	
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, projectId,Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					project = extract(rs, Project.class);
				}
			}
		}
		
		if(Objects.nonNull(project)) {
			project.getMaterials().addAll((Collection<? extends Project>) fetchMaterialsForProject(conn, projectId));
			project.getSteps().addAll((Collection<? extends Project>) fetchStepsForProject(conn, projectId));
			project.getCategories().addAll((Collection<? extends Project>) fetchCategoriesForProject(conn, projectId));
		}
		commitTransaction(conn);
		return Optional.ofNullable(project);
	}
	catch(Exception e) {
		rollbackTransaction(conn);
		throw new DbException(e);
	}
}
	catch(SQLException e) {
		throw new DbException(e);}
	}
	
private static Project extract(ResultSet rs, Class<Project> class1) throws SQLException {
	// TODO Auto-generated method stub
	Project newProject = new Project();
	newProject.setProjectId(rs.getInt(1));
	newProject.setProjectName(rs.getString(2));
	newProject.setEstimatedHours(rs.getBigDecimal(3));
	newProject.setActualHours(rs.getBigDecimal(4));
	newProject.setDifficulty(rs.getInt(5));
	newProject.setNotes(rs.getString(6));
	return newProject;
}


private void Projectextract1(ResultSet rs, Class<Project> class1) {
	// TODO Auto-generated method stub
	;
}


private void setParameter(PreparedStatement stmt, int i, Integer projectId, Class<Integer> class1) {
	
	
}
private void Projectextract(ResultSet rs, Class<Project> class1) {
	;
}
private void fetchCategoriesForProject1(Connection conn, Integer projectId) throws SQLException {
	
// @formatter:off
	String sql = ""
			+"SELECT c.* FROM" + CATEGORY_TABLE + " c "
			+"JOIN " + PROJECT_CATEGORY_TABLE + "pc USING (category_id"
			+ "WHERE project_id = ?";
;}
// @formatter:on
	
	private List<Category> fetchCategoriesForProject(Connection conn, Integer projectId) throws SQLException {
	    String sql = "SELECT * FROM " + CATEGORY_TABLE + " WHERE project_id = ?";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        setParameter(stmt, 1, projectId, Integer.class);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            List<Category> categories = new LinkedList<>();
	            
	            while (rs.next()) {
	                categories.add(extractCategory(rs)); // Assuming there's an appropriate method for extracting Category objects
	            }
	            
	            return categories;
	        }
	    }
	}

	private Category extractCategory(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}


	private List<Material> fetchMaterialsForProject(Connection conn, Integer projectId) throws SQLException {
	    String sql = "SELECT * FROM " + MATERIAL_TABLE + " WHERE project_id = ?";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        setParameter(stmt, 1, projectId, Integer.class);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            List<Material> materials = new LinkedList<>();
	            
	            while (rs.next()) {
	                materials.add(extractMaterial(rs)); // Assuming there's an appropriate method for extracting Material objects
	            }
	            
	            return materials;
	        }
	    }
	}

	private Material extractMaterial(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}


	private void MaterialextractMaterial(ResultSet rs) {
		// TODO Auto-generated method stub
		;
	}


	private List<Step> fetchStepsForProject(Connection conn, Integer projectId) throws SQLException {
	    String sql = "SELECT * FROM " + STEP_TABLE + " WHERE project_id = ?";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        setParameter(stmt, 1, projectId, Integer.class);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            List<Step> steps = new LinkedList<>();
	            
	            while (rs.next()) {
	                steps.add(extractStep(rs)); // Assuming there's an appropriate method for extracting Step objects
	            }
	            
	            return steps;
	        }
	    }
	}

	
private Step extractStep(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}


public static void setParameter(PreparedStatement stmt, int index, Object value) throws SQLException {
    stmt.setObject(index, value);
}

	
	
private int getLastInsertId(Connection conn) throws SQLException {
    try (PreparedStatement stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()")) {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No last insert ID obtained.");
            }
            } finally {
            }
        }
    }
private static void startTransaction(Connection conn) {
	
	
}


private void rollbackTransaction(Connection conn) {

	
}


private void commitTransaction(Connection conn) {
	
	
}


private Integer getLastInsertId(Connection conn, String projectTable) {
	
	return null;
}
}