package ru.unn.agile.SupplyDemand.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.SupplyDemand.infrastructure.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }


    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }


     @Test 
     public void isLogEmptyByDefault() { 
         List<String> log = txtLogger.getLog(); 
         assertEquals(0, log.size()); 
     } 


    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }


     @Test 
     public void isLogMessageCorrect() { 
         String rightMessage = "Some interesting message"; 
 
 
         txtLogger.addMessage(rightMessage); 
 
 
         String actualMessage = txtLogger.getLog().get(0); 
         assertThat(actualMessage, containsString(rightMessage)); 
     } 


    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }


     @Test 
     public void areSeveralMessagesCorrect() { 
         final int numberOfMessages = 5; 
 
 
         String[] rightMessages = addSeveralMessages(numberOfMessages); 
 
 
         List<String> actualMessages = txtLogger.getLog(); 
         for (int i = 0; i < numberOfMessages; i++) { 
             assertThat(actualMessages.get(i), containsString(rightMessages[i])); 
         } 
     } 


    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }


    private String[] addSeveralMessages(final int numberOfMessages) { 
        String[] rightMessages = new String[numberOfMessages]; 
 
 
        for (int i = 0; i < numberOfMessages; i++) { 
             rightMessages[i] = "Message ¹" + i; 
             txtLogger.addMessage(rightMessages[i]); 
         } 
 
 
         return rightMessages; 
     } 
}
