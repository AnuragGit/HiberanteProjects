package com.anurag.tutorial.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.anurag.tutorial.util.HibernateUtil;

@RunWith( Suite.class )
@Suite.SuiteClasses( {
        TestStorage.class,TestUserDetails.class
        /* , Add more test classes here separated by commas*/
} )
public class RuleSuite{

    private static HibernateUtil util;

    // This is a static field.  Per the ClassRule documentation,
    // to use a ClassRule we need a field that is "public, static,
    // and a subtype of of TestRule."
    // See http://junit.czweb.org/apidocs/org/junit/ClassRule.html
    @ClassRule
    public static ExternalResource testRule = new ExternalResource(){
        @Override
        protected void before() throws Throwable{
            Logger.getLogger("com.anurag.tutorial.test").log(Level.DEBUG, "Inside RuleSuite::ExternalResource::before.");
          /*  util = new HibernateUtil();*/
        }

        @Override
        protected void after(){
            // Nothing to do here in this case.
            Logger.getLogger("com.anurag.tutorial.test").log(Level.DEBUG, "Inside RuleSuite::ExternalResource::after.");
        }
    };
}