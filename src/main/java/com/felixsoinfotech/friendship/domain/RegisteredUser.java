package com.felixsoinfotech.friendship.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class RegisteredUser {
	@Id
	private String userId;
	private String name;
	@Relationship(type = "FRIEND_OF", direction = Relationship.OUTGOING)
	private List<RegisteredUser> friends = new ArrayList<>();

	public List<RegisteredUser> getFriends() {
		return friends;
	}

	public void setFriends(List<RegisteredUser> friends) {
		this.friends = friends;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
