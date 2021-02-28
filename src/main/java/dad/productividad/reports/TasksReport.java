package dad.productividad.reports;

public class TasksReport {

	private int id;
	private String title;
	private boolean completed;
	private String completedDate;
	private int idPage;

	public TasksReport() {
	}

	public TasksReport(int id, String title, boolean completed, String completedDate, int idPage) {
		super();
		this.id = id;
		this.title = title;
		this.completed = completed;
		this.completedDate = completedDate;
		this.idPage = idPage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}

}
