/*
 * ###
 * phresco-pom
 * 
 * Copyright (C) 1999 - 2012 Photon Infotech Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ###
 */
package com.phresco.pom.test;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.phresco.pom.exception.PhrescoPomException;
import com.phresco.pom.util.PomProcessor;

public class ChangePluginVersionTest {

	@Before
	public void prepare(){
		File file = new File("pomTest.xml");
		if(file.exists()) {
			file.delete();
		}
	}
	
	@Test
	public void validchangePluginVersion() throws PhrescoPomException {
			PomProcessor processor = new PomProcessor(new File("pomTest.xml"));
			processor.addPlugin("photon", "phresco", "1.2.2");
			processor.addPlugin("phresco", "photon", "1.2");
			processor.changePluginVersion("phresco", "photon", "2.2.2");
			processor.save();
			String actual = processor.getModel().getBuild().getPlugins().getPlugin().get(1).getVersion();
			String expected = "2.2.2";
			Assert.assertEquals(actual,expected);
	}
	
	@Test
	public void invalidchangePluginVersion() throws PhrescoPomException {
			PomProcessor processor = new PomProcessor(new File("pomTest.xml"));
			processor.addPlugin("photon", "phresco", "1.2.2");
			processor.addPlugin("phresco", "photon", "1.2");
			processor.changePluginVersion("invalid", "photon", "2.2.2");
			processor.save();
	}
	
	@After
	public void delete(){
		File file = new File("pomTest.xml");
		if(file.exists()) {
			file.delete();
		}
	}
	
}
