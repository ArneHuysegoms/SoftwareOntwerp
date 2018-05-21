package window.elements;

import exception.UIException;
import org.junit.Before;
import org.junit.Test;
import window.elements.textbox.ArgumentTextBox;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.MethodTextBox;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class TextBoxTest {

    ArgumentTextBox argumentTextBox;
    ClassTextBox classTextBox;
    InstanceTextBox instanceTextBox;
    MethodTextBox methodTextBox;
    Point2D validPoint;

    @Before
    public void setUp() {
        validPoint = new Point2D.Double(50, 50);
        try {
            argumentTextBox = new ArgumentTextBox(validPoint, "");
            classTextBox = new ClassTextBox(validPoint, "");
            instanceTextBox = new InstanceTextBox(validPoint, "");
            methodTextBox = new MethodTextBox(validPoint, "");
        }
        catch (UIException exc){
            fail();
        }
    }

    @Test
    public void test_isClicked(){
        assertTrue(argumentTextBox.isClicked(new Point2D.Double(60 , 60)));
        assertFalse(argumentTextBox.isClicked(new Point2D.Double(60, 75)));
        assertTrue(argumentTextBox.isClicked(new Point2D.Double(100, 60)));
    }

    @Test
    public void test_adding_and_removing_characters(){
        assertEquals("", argumentTextBox.getContents());
        argumentTextBox.addCharToContents('a');
        assertEquals("a", argumentTextBox.getContents());
        argumentTextBox.addCharToContents('b');
        assertEquals("ab", argumentTextBox.getContents());
        argumentTextBox.deleteLastCharFromContents();
        assertEquals("a", argumentTextBox.getContents());
        argumentTextBox.deleteLastCharFromContents();
        assertEquals("", argumentTextBox.getContents());
        argumentTextBox.deleteLastCharFromContents();
        assertEquals("", argumentTextBox.getContents());
    }

    @Test
    public void test_argumentTextBox_hasValidContents(){
        argumentTextBox.setContents(":A");
        assertFalse(argumentTextBox.hasValidContents());
        argumentTextBox.setContents("a:");
        assertFalse(argumentTextBox.hasValidContents());
        argumentTextBox.setContents("a:a");
        assertFalse(argumentTextBox.hasValidContents());
        argumentTextBox.setContents("A:A");
        assertFalse(argumentTextBox.hasValidContents());
        argumentTextBox.setContents("");
        assertFalse(argumentTextBox.hasValidContents());
        argumentTextBox.setContents("a:A");
        assertTrue(argumentTextBox.hasValidContents());
    }

    @Test
    public void test_classTextBox_hasValidContents(){
        classTextBox.setContents("Aa");
        assertTrue(classTextBox.hasValidContents());
        classTextBox.setContents("aa");
        assertFalse(classTextBox.hasValidContents());
        classTextBox.setContents("");
        assertFalse(classTextBox.hasValidContents());
        classTextBox.setContents("AA");
        assertTrue(classTextBox.hasValidContents());
    }

    @Test
    public void test_instanceTextBox_hasValidContents(){
        instanceTextBox.setContents("aa");
        assertTrue(instanceTextBox.hasValidContents());
        instanceTextBox.setContents("aA");
        assertTrue(instanceTextBox.hasValidContents());
        instanceTextBox.setContents("Aa");
        assertFalse(instanceTextBox.hasValidContents());
        instanceTextBox.setContents("");
        assertTrue(instanceTextBox.hasValidContents());
    }

    @Test
    public void test_methodTextBox(){
        methodTextBox.setContents("");
        assertFalse(methodTextBox.hasValidContents());
        methodTextBox.setContents("a");
        assertTrue(methodTextBox.hasValidContents());
    }
}
