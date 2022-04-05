package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
/*
public class InventoryTest {
    private Inventory inventory;
    @BeforeEach
    public void setUp(){
        //inventory = new Inventory();
    }

    @Test
    public void testLoad1(){
        String [] gadgets = new String[0];
        inventory.load(gadgets);
        boolean ans = inventory.getGadgets().contains("sky");
        assertEquals(false,ans);
        fail("Not a good test");
    }

    @Test
    public void testLoad2(){
        String [] gadgets = new String[0];
        inventory.load(gadgets);
        String [] gadgets1 = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets1);
        boolean ans = inventory.getGadgets().contains("magic");
        assertEquals(true,ans);
        fail("Not a good test");
    }

    @Test
    public void testLoad3(){
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        boolean ans = inventory.getGadgets().contains("sky");
        assertEquals(true,ans);
        fail("Not a good test");
    }

    @Test
    public void testLoad4(){
        boolean ans = true;
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        for (int i = 0; i<gadgets.length &ans; i++)
        {
            if (!(inventory.getGadgets().contains(gadgets[i])))
                ans = false;
        }
        assertEquals(true,ans);
        fail("Not a good test");
    }

    @Test
    public void testLoad5(){
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        boolean ans = inventory.getGadgets().contains("wow");
        assertEquals(false,ans);
        fail("Not a good test");
    }

    @Test
    public void testLoad6(){
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        gadgets[0] = "stick";
        boolean ans = inventory.getGadgets().contains("stick");
        assertEquals(false,ans);
        fail("Not a good test");
    }

    @Test
    public void testGetItem1(){
        String [] gadgets = new String[0];
        inventory.load(gadgets);
        boolean ans = inventory.getItem("sky");
        assertEquals(false,ans);
        fail("Not a good test");
    }
    @Test
    public void testGetItem2(){
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        boolean ans = inventory.getItem("sky");
        assertEquals(true,ans);
        fail("Not a good test");
    }

    @Test
    public void testGetItem3(){
        boolean ans = inventory.getItem("sky");
        assertEquals(false,ans);
        fail("Not a good test");
    }

    @Test
    public void testGetItem4(){
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        boolean ans = inventory.getItem("wow");
        assertEquals(false,ans);
        fail("Not a good test");
    }

    @Test
    public void testGetItem5(){
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        boolean ans1 = inventory.getItem("sky");
        boolean ans = inventory.getItem("sky");
        assertEquals(false,ans);
        fail("Not a good test");
    }

    @Test
    public void testGetItem6(){
        String [] gadgets = new String[3];
        gadgets[0] = "sky";
        gadgets[1] = "magic";
        gadgets[2] = "drink";
        inventory.load(gadgets);
        boolean ans1 = inventory.getItem("sky");
        boolean ans = inventory.getItem("magic");
        assertEquals(true,ans);
        fail("Not a good test");
    }

}
*/