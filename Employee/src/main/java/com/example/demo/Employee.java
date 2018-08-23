package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	// declaration of variables
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private long companyId;

	// constructors
	public Employee() {

	}

	public Employee(String name, long companyId) {
		this.name = name;
		this.companyId = companyId;
	}

	// getter and setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	// to string method
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", companyId=" + companyId + "]";
	}

//	@RequestMapping("")
//
//	@ResponseBody
//
//	public String createSong(String name, Long artistId) {
//
//		try {
//
//			// 1. Try to get the artist from the db
//
//			Artist artist = artistDao.findById(artistId).orElse(null);
//
//			if (artist == null) {
//
//				// write the logic to create a new artst
//
//				return "Cannot find an artist with that id: " + artistId.toString();
//
//			}
//
//			// 2. Create a new Song object
//
//			Song s = new Song(name, artist);
//
//			// 3. Save the Song object
//
//			this.songDb.save(s);
//
//			return "New song created :" + s.toString();
//
//		} catch (Exception ex) {
//
//			ex.printStackTrace();
//
//			return null;
//
//		}
//
//	}

}
