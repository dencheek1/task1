package by.vsu.lab.task4.entitys;

public class ExamResult {

    private Examination exam;
    private Applicant applicant;;
    private int result;
    private boolean checked;

    public boolean getChecked() {
	return checked;
    }

    public void setChecked(boolean checked) {
	this.checked = checked;
    }

    public Examination getExam() {
	return exam;
    }

    public void setExam(Examination exam) {
	this.exam = exam;
    }

    public int getResult() {
	return result;
    }

    public void setResult(int result) {
	this.result = result;
    }

    public Applicant getAplicant() {
	return applicant;
    }

    public void setAplicant(Applicant applicant) {
	this.applicant = applicant;
    }

    @Override
    public String toString() {
	return "ExamResult [exam=" + exam + ", applicant=" + applicant.getId() +
		", result=" + result + ", checked=" + checked
		+ "]";
    }

}
