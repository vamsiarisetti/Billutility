package com.org.utility.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HelloResponse
{
	HelloModel data;
	Integer    statusCode;
}
