package com.example.demo.dto;

public class SampleDTO {
	private String name;
	private Integer zip;

	public SampleDTO(String name, Integer zip) {
		super();
		this.name = name;
		this.zip = zip;
	}
	
	
	public String getName() {
		return name;
	}
	public Integer getZip() {
		return zip;
	}
}
