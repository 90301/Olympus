package buildings;

import topLevel.Person;

public class EmploymentInfo {
	private int payPerWorkCycle;
	private float comissionTotal;
	private int payOwed;
	private Person person;
	private SubBuilding building;
	
	/**
	 * This sets what the employee is tasked to do
	 * it corresponds with codes for the particular sub building.
	 */
	private int workCode;
	/**
	 * Call this to collect pay for a worker.
	 * Sets pay owed to 0.
	 * @return the pay for that worker.
	 */
	public int collectPay() {
		int pay = payOwed;
		payOwed = 0;
		return pay;
	}
	
	public int getPayOwed() {
		return payOwed;
	}
	public void setPayOwed(int payOwed) {
		this.payOwed = payOwed;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public SubBuilding getBuilding() {
		return building;
	}
	public void setBuilding(SubBuilding building) {
		this.building = building;
	}
	public float getComissionTotal() {
		return comissionTotal;
	}
	public void setComissionTotal(float comissionTotal) {
		this.comissionTotal = comissionTotal;
	}
	public int getPayPerWorkCycle() {
		return payPerWorkCycle;
	}
	public void setPayPerWorkCycle(int payPerWorkCycle) {
		this.payPerWorkCycle = payPerWorkCycle;
	}
	public int getWorkCode() {
		return workCode;
	}
	public void setWorkCode(int workCode) {
		this.workCode = workCode;
		System.out.println("Set Work Code: " + this.workCode + " // " +this);
	}

	@Override
	public String toString() {
		return "EmploymentInfo [payPerWorkCycle=" + payPerWorkCycle + ", comissionTotal=" + comissionTotal
				+ ", payOwed=" + payOwed + ", person=" + person + ", building=" + building + ", workCode=" + workCode
				+ "]";
	}

}
