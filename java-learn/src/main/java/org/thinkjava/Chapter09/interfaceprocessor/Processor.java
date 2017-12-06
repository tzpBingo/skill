package org.thinkjava.Chapter09.interfaceprocessor;

public interface Processor {
	String name();

	Object process(Object input);
}
