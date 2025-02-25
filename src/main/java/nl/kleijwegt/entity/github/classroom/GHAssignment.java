package nl.kleijwegt.entity.github.classroom;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "assignment")
@XmlAccessorType(XmlAccessType.FIELD)
public class GHAssignment {

	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "public_repo")
	private String publicRepo;
	
	@XmlElement(name = "title")
	private String title;
	
	@XmlElement(name = "type")
	private String type;
	
	@XmlElement(name = "invite_link")
	private String inviteLink;
	
	@XmlElement(name = "invitations_enabled")
	private Boolean invitationsEnabled = true;
	
	@XmlElement(name = "slug")
	private String slug;
	
	@XmlElement(name = "students_are_repo_admins")
	private Boolean studentsAreRepoAdmins = false;
	
	@XmlElement(name = "feedback_pull_requests_enabled")
	private Boolean feedbackPullRequestsEnabled = false;
	
	@XmlElement(name = "max_teams")
	private Long maxTeams;
	
	@XmlElement(name = "max_members")
	private Long maxMembers;
	
	@XmlElement(name = "editor")
	private String editor;
	
	@XmlElement(name = "accepted")
	private Long accepted;
	
	@XmlElement(name = "submissions")
	private Long submissions;
	
	@XmlElement(name = "passing")
	private String passing;
	
	@XmlElement(name = "language")
	private String language;
	
	@XmlElement(name = "deadline")
	private String deadline;

	private GHClassroom classroom;
	
	@XmlElement(name = "starter_code_repository")
	private GHRepository startCodeRepository;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicRepo() {
		return publicRepo;
	}

	public void setPublicRepo(String publicRepo) {
		this.publicRepo = publicRepo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInviteLink() {
		return inviteLink;
	}

	public void setInviteLink(String inviteLink) {
		this.inviteLink = inviteLink;
	}

	public Boolean getInvitationsEnabled() {
		return invitationsEnabled;
	}

	public void setInvitationsEnabled(Boolean invitationsEnabled) {
		this.invitationsEnabled = invitationsEnabled;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Boolean getStudentsAreRepoAdmins() {
		return studentsAreRepoAdmins;
	}

	public void setStudentsAreRepoAdmins(Boolean studentsAreRepoAdmins) {
		this.studentsAreRepoAdmins = studentsAreRepoAdmins;
	}

	public Boolean getFeedbackPullRequestsEnabled() {
		return feedbackPullRequestsEnabled;
	}

	public void setFeedbackPullRequestsEnabled(Boolean feedbackPullRequestsEnabled) {
		this.feedbackPullRequestsEnabled = feedbackPullRequestsEnabled;
	}

	public Long getMaxTeams() {
		return maxTeams;
	}

	public void setMaxTeams(Long maxTeams) {
		this.maxTeams = maxTeams;
	}

	public Long getMaxMembers() {
		return maxMembers;
	}

	public void setMaxMembers(Long maxMembers) {
		this.maxMembers = maxMembers;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Long getAccepted() {
		return accepted;
	}

	public void setAccepted(Long accepted) {
		this.accepted = accepted;
	}

	public Long getSubmissions() {
		return submissions;
	}

	public void setSubmissions(Long submissions) {
		this.submissions = submissions;
	}

	public String getPassing() {
		return passing;
	}

	public void setPassing(String passing) {
		this.passing = passing;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public GHClassroom getClassroom() {
		return classroom;
	}

	public void setClassroom(GHClassroom classroom) {
		this.classroom = classroom;
	}
	
	public GHRepository getStartCodeRepository() {
		return startCodeRepository;
	}
	
	public void setStartCodeRepository(GHRepository startCodeRepository) {
		this.startCodeRepository = startCodeRepository;
	}
}
