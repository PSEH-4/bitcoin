package com.sapient.bitcoin.bean;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "updated", "updatedISO", "updateduk" })
public class Time {

	@JsonProperty("updated")
	private String updated;
	@JsonProperty("updatedISO")
	private String updatedISO;
	@JsonProperty("updateduk")
	private String updateduk;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("updated")
	public String getUpdated() {
		return updated;
	}

	@JsonProperty("updated")
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	@JsonProperty("updatedISO")
	public String getUpdatedISO() {
		return updatedISO;
	}

	@JsonProperty("updatedISO")
	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}

	@JsonProperty("updateduk")
	public String getUpdateduk() {
		return updateduk;
	}

	@JsonProperty("updateduk")
	public void setUpdateduk(String updateduk) {
		this.updateduk = updateduk;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
