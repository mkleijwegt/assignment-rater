package nl.kleijwegt.entity.github.classroom;

import java.math.BigDecimal;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "studentAssignment")
@XmlAccessorType(XmlAccessType.FIELD)
public class GHStudentAssignment {

	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "submitted")
	private Boolean submitted = false;
	
	@XmlElement(name = "passing")
	private Boolean passing = false;
	
	@XmlElement(name = "commit_count")
	private Long commitCount;
	
	@XmlElement(name = "grade")
	private BigDecimal grade = BigDecimal.ZERO;
	
	private GHAssignment assignment;
	
	private GHRepository repository;
	
	private List<GHStudent> students;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}

	public Boolean getPassing() {
		return passing;
	}

	public void setPassing(Boolean passing) {
		this.passing = passing;
	}

	public Long getCommitCount() {
		return commitCount;
	}

	public void setCommitCount(Long commitCount) {
		this.commitCount = commitCount;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public GHAssignment getAssignment() {
		return assignment;
	}

	public void setAssignment(GHAssignment assignment) {
		this.assignment = assignment;
	}

	public GHRepository getRepository() {
		return repository;
	}

	public void setRepository(GHRepository repository) {
		this.repository = repository;
	}

	public List<GHStudent> getStudents() {
		return students;
	}

	public void setStudents(List<GHStudent> students) {
		this.students = students;
	}
}
