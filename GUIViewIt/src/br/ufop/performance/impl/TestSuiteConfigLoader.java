package br.ufop.performance.impl;

import java.io.FileInputStream;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;

import br.ufop.performance.model.TestSuite;

class TestSuiteConfigLoader {



	public static TestSuite loadTestSuiteConfiguration(String configXmlPath) {
		try {

			InputStream in = new FileInputStream(configXmlPath);
			XStream xstream = new XStream();

			xstream.processAnnotations(TestSuite.class);
			TestSuite config = (TestSuite) xstream.fromXML(in);
			return config;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
