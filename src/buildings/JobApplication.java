package buildings;

import java.util.Random;

import topLevel.Person;

public class JobApplication implements Comparable<JobApplication> {
	private static final int MAX_LUCK = 100;
	private int luck;
	private int skill;
	private static Random rand = new Random(555);
	private String personId;
	private Person applicant;
	
	/**
	 * Creates an application (this application)
	 * for a specific person.
	 * @param p the person applying.
	 */
	public void genApplication(Person p) {
		this.luck = rand.nextInt(MAX_LUCK);
		this.skill = 25;
		this.personId = p.getId();
		this.applicant = p;
	}
	public int getApplicationScore() {
		return luck+skill;
		
	}
	
	
	public int getSkill() {
		return skill;
	}


	public void setSkill(int skill) {
		this.skill = skill;
	}


	public int getLuck() {
		return luck;
	}


	public void setLuck(int luck) {
		this.luck = luck;
	}
	public Person getApplicant() {
		return applicant;
	}
	public void setApplicant(Person applicant) {
		this.applicant = applicant;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	
	@Override
	public int compareTo(JobApplication o) {
		int value = o.getApplicationScore()-this.getApplicationScore();
		
		return value;
	}
}
