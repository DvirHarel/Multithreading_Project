package bgu.spl.mics.application.passiveObjects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *  That's where Q holds his gadget (e.g. an explosive pen was used in GoldenEye, a geiger counter in Dr. No, etc).
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private fields and methods to this class as you see fit.
 */
public class Inventory {
	private List<String> gadgets;
	private static class SingletonHolder {
		private static Inventory instance = new Inventory();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Inventory getInstance() {
		return SingletonHolder.instance;
	}

	private Inventory()
	{
		gadgets = new LinkedList<>();
	}

	/**
	 * Initializes the inventory. This method adds all the items given to the gadget
	 * inventory.
	 * <p>
	 * @param inventory 	Data structure containing all data necessary for initialization
	 * 						of the inventory.
	 */
	public void load (String[] inventory) {
		for (int i =0; i<inventory.length; i++)
		{
			gadgets.add(inventory[i]);
		}
	}

	/**
	 * acquires a gadget and returns 'true' if it exists.
	 * <p>
	 * @param gadget 		Name of the gadget to check if available
	 * @return 	‘false’ if the gadget is missing, and ‘true’ otherwise
	 */
	public boolean getItem(String gadget){
		boolean ans = false;
		for (int i=0; i<gadgets.size() & !ans; i++)
		{
			if (gadgets.get(i) == gadget)
			{
				gadgets.remove(i);
				ans = true;
			}
		}
		return ans;
	}

	/**
	 *
	 * <p>
	 * Prints to a file name @filename a serialized object List<String> which is a
	 * list of all the of the gadgeds.
	 * This method is called by the main method in order to generate the output.
	 */
	public synchronized void printToFile(String filename){
		try
		{
			FileOutputStream current = new FileOutputStream(filename);
			ObjectOutputStream tmp = new ObjectOutputStream(current);
			tmp.writeObject(gadgets);
			tmp.close();
			current.close();
		}
		catch (IOException exception) {
			throw new IllegalStateException(exception.getMessage());
		}
	}
}