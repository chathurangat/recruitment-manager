package com.hsenidmobile.recruitment.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * <p>
 *     this is the super test class for all test classes.
 *     everyone who need to implement DAO layer test class should extends this test class
 * </p>
 */
@ContextConfiguration(locations = {"classpath*:applicationContext-core-test.xml"})
public class CommonDaoTest extends AbstractTestNGSpringContextTests{

}
