package com.pratiksha.resume_service.entity;

import jakarta.persistence.*;

@Entity
public class Resume {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 private String name;

 private Long userId;

 private int score;

 private int experience;

 private String skills;

 private String filePath;

 private String missingSkills;

 private String suggestion;

 public Resume(){}

 public Long getId(){return id;}

 public Long getUserId(){return userId;}
 public void setUserId(Long userId){this.userId=userId;}

 public int getScore(){return score;}
 public void setScore(int score){this.score=score;}

 public int getExperience(){return experience;}
 public void setExperience(int experience){this.experience=experience;}

 public String getSkills(){return skills;}
 public void setSkills(String skills){this.skills=skills;}

 public String getFilePath(){return filePath;}
 public void setFilePath(String filePath){this.filePath=filePath;}

 public String getMissingSkills(){
	 return missingSkills;
	}

	public void setMissingSkills(String missingSkills){
	 this.missingSkills = missingSkills;
	}

	public String getSuggestion(){
	 return suggestion;
	}

	public void setSuggestion(String suggestion){
	 this.suggestion = suggestion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}