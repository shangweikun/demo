package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:test.properties")
public class Configuration {

	@Value("${test.name}")
	public String name;
	
	@Value("${test.world}")
	public String world;

	public String getName() {
		return name;
	}

	public String getWorld() {
		return world;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	@Override
	public String toString() {
		return "Configuration [name=" + name + ", world=" + world + "]";
	}
	
}
