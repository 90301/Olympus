package buildings;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import topLevel.NodeMain;
import topLevel.Person;
import topLevel.simulateable;

public class JobListing implements simulateable {
	private static final int DEFAULT_TIME_LEFT = 10;
	private String id;
	private int timeLeft;
	private EmploymentInfo eInfo;
	private SubBuilding subBuilding;
	
	private ConcurrentHashMap<String, JobApplication> applications = new ConcurrentHashMap<String, JobApplication>();
	private Boolean jobTaken = false;
	
	/**
	 * This method generates an application for a person
	 * @param p
	 */
	public void submitApplication(Person p) {
		JobApplication application = new JobApplication();
		application.genApplication(p);
		applications.put(p.getId(), application);
	}
	/**
	 * Checks if the Person has already applied for this job.
	 * @param p the person (should be called with "this")
	 * @return True if the person has already applied, false if not.
	 */
	public Boolean alreadyApplied(Person p) {
		return applications.containsKey(p.getId());
	}

	public JobListing(EmploymentInfo eInfo) {
		this.generate();
		this.jobTaken = false;
		this.subBuilding = eInfo.getBuilding();
		this.eInfo = eInfo;
		
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void generate() {
		this.id = NodeMain.genID();
		this.setTimeLeft(DEFAULT_TIME_LEFT);
	}

	@Override
	public void simulateStep() {
		setTimeLeft(getTimeLeft() - 1);
		if (timeLeft<=0) {
			//pick candidate, ask for confirmation
			Boolean sucsess = hire();
			if (sucsess) {
				jobTaken = true;
			}
		}
		
	}
	
	private Boolean hire() {
		//go through applications, put them in a sorted list.
		//TreeMap<Integer, JobApplication> orderedCandidates = new TreeMap<>();
		TreeSet<JobApplication> orderedCandidates = new TreeSet<JobApplication>();
		orderedCandidates.addAll(applications.values());
		for (JobApplication offerApplication : orderedCandidates) {
			
			Boolean acceptedOffer = offerApplication.getApplicant().offerJob(eInfo);
			if (acceptedOffer) {
				//update eInfo with applicants info
				eInfo.setPerson(offerApplication.getApplicant());
				//send to the sub building
				subBuilding.hire(eInfo);
				//The acceptance process for the Person adds it to their jobs.
				return true;
				
			}
		}
		return false;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	public EmploymentInfo geteInfo() {
		return eInfo;
	}
	public void seteInfo(EmploymentInfo eInfo) {
		this.eInfo = eInfo;
	}
	public SubBuilding getSubBuilding() {
		return subBuilding;
	}
	public void setSubBuilding(SubBuilding subBuilding) {
		this.subBuilding = subBuilding;
	}
	public Boolean getJobTaken() {
		return jobTaken;
	}
	public void setJobTaken(Boolean jobTaken) {
		this.jobTaken = jobTaken;
	}
}
