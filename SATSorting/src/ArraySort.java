
import java.util.*;

public class ArraySort {
	public boolean debug = false;
	public static int depth = 0;
	public static String group = "full list";

	public String[] quickSort(String[] strings) {
		//"B".compareTo("A") > 0
		//"A".compareTo("B") < 0
		if (strings.length > 1) {
			int pivot = (int)(Math.random()*strings.length);
			List<String> less = new ArrayList<>();
			List<String> more = new ArrayList<>();

			for (int i=0; i<strings.length; i++) {
				if (i != pivot) {
					if (strings[i].compareTo(strings[pivot]) < 0) {
						less.add(strings[i]);
					}
					else {
						more.add(strings[i]);
					}
				}
			}
			String[] lessPrim = new String[less.size()];
			for (int i=0; i<less.size(); i++) {
				lessPrim[i] = less.get(i);
			}
			String[] morePrim = new String[more.size()];
			for (int i=0; i<more.size(); i++) {
				morePrim[i] = more.get(i);
			}

			lessPrim = quickSort(lessPrim);
			morePrim = quickSort(morePrim);

			int newLength = lessPrim.length + morePrim.length + 1;
			String[] combined  = new String[newLength];
			for (int i=0; i<lessPrim.length; i++) {
				combined[i] = lessPrim[i];
			}
			combined[lessPrim.length] = strings[pivot];
			for (int i=lessPrim.length+1; i<newLength; i++) {
				combined[i] = morePrim[i - lessPrim.length - 1];
			}
			depth --;
			if (debug) System.out.print("\n");

			return combined;
		}
		else {
			return strings;
		}
	}

	public int[] quickSort(int[] nums) {
		if (debug) System.out.println("sorting at depth " + depth + " on " + group + "...");
		//check if 'nums' has at least 2 values
		if (nums.length > 1) {
			//create a random pivot integer
			int pivot = (int)(Math.random()*nums.length);
			if (debug) System.out.println("Pivot is " + pivot + ", value " + nums[pivot]);
			//create two ArrayLists for the groups of less and more
			List<Integer> less = new ArrayList<>();
			List<Integer> more = new ArrayList<>();
			//loop through all values and check if they are less or more than the pivot value
			for (int i=0; i<nums.length; i++) {
				//skip the pivot value
				if (i != pivot) {
					//add 'num[i]' to its respective group
					if (nums[i] < nums[pivot]) {
						less.add(nums[i]);
					}
					else {
						more.add(nums[i]);
					}
				}
			}
			depth ++;
			//turn the arraylists into primitive arrays
			int[] lessPrim = new int[less.size()];
			if (debug) System.out.print("Less group: ");
			for (int i=0; i<less.size(); i++) {
				lessPrim[i] = less.get(i);
				if (debug) System.out.print(lessPrim[i] + ", ");
			}
			if (debug) System.out.print("\nMore group: ");
			int[] morePrim = new int[more.size()];
			for (int i=0; i<more.size(); i++) {
				morePrim[i] = more.get(i);
				if (debug) System.out.print(morePrim[i] + ", ");
			}
			//recurse through the tree of groups
			if (debug) System.out.println("\nRecursing");
			group = "less group";
			lessPrim = quickSort(lessPrim);
			group = "more group";
			morePrim = quickSort(morePrim);

			//create a new array that is the combination of the 'less' and 'more' groups
			int newLength = lessPrim.length + morePrim.length + 1;
			int[] combined  = new int[newLength];
			if (debug) System.out.print("Combined list is: ");
			for (int i=0; i<lessPrim.length; i++) {
				combined[i] = lessPrim[i];
				if (debug) System.out.print(combined[i] + ", ");
			}
			combined[lessPrim.length] = nums[pivot];
			if (debug) System.out.print(nums[pivot] + ", ");
			for (int i=lessPrim.length+1; i<newLength; i++) {
				combined[i] = morePrim[i - lessPrim.length - 1];
				if (debug) System.out.print(combined[i] + ", ");
			}
			depth --;
			if (debug) System.out.print("\n");

			return combined;
		}
		else {
			return nums;
		}

	}

	//Method to take in arbitrary objects, compare and sort them using quicksort, and return a 2d array of sorted objects paired with original position values
	//takes in a List of Objects
	public List<List<Object>> quickReplace(List<Object> objects) {
		//Instantiate the output 2Dlist and add two rows for values
		List<List<Object>> combined = new ArrayList<List<Object>>();
		combined.add(new ArrayList<Object>());
		combined.add(new ArrayList<Object>());

		if (objects.size() > 1) {
			int pivot = (int)(Math.random()*objects.size());
			List<List<Object>> less = new ArrayList<List<Object>>();
			List<List<Object>> more = new ArrayList<List<Object>>();
			for(int i=0; i<2; i++)  {
				less.add(new ArrayList<Object>());
				more.add(new ArrayList<Object>());
			}

			System.out.println("Pivot is " + pivot + ", value " + objects.get(pivot));
			for (int i=0; i<objects.size(); i++) {
				//System.out.println(objects.get(i));
				if (i != pivot) {
					try {
						if (Integer.parseInt(String.valueOf(objects.get(i))) < Integer.parseInt(String.valueOf(objects.get(pivot)))) {
							System.out.println(objects.get(i) + " was less than pivot " + objects.get(pivot));
							less.get(0).add(i);
							less.get(1).add(objects.get(i));
						}
						else {
							System.out.println(objects.get(i) + " was more than pivot " + objects.get(pivot));
							more.get(0).add(i);
							more.get(1).add(objects.get(i));
						}
					}
					catch (NumberFormatException e) {
						if (String.valueOf(objects.get(i)).compareTo(String.valueOf(objects.get(pivot))) < 0) {
							System.out.println(objects.get(i) + " was less than pivot " + objects.get(pivot));
							less.get(0).add(i);
							less.get(1).add(objects.get(i));
						}
						else {
							System.out.println(objects.get(i) + " was more than pivot " + objects.get(pivot));
							more.get(0).add(i);
							more.get(1).add(objects.get(i));
						}

					}
				}
			}

			less = quickReplace(less.get(1));
			more = quickReplace(more.get(1));

			combined = less;
			//System.out.println(combined.get(0).size());
			combined.get(0).add(pivot);
			//System.out.println(combined.get(0).size());
			combined.get(1).add(objects.get(pivot));
			//System.out.println("more 0 size: " + more.get(0).size());
			for (int i=0; i<more.get(0).size(); i++) {
				combined.get(0).add(more.get(0).get(i));
				combined.get(1).add(more.get(1).get(i));
			}

			System.out.println(combined.get(0).size());
			for (int i=0; i<combined.get(0).size(); i++) {
				System.out.println("postion " + i + ": original Position - " + combined.get(0).get(i) + ": value - " + combined.get(1).get(i));
			}
			return combined;

		}
		else {
			for (int i=0; i<objects.size(); i++) {
				combined.get(0).add(i);
				combined.get(1).add(objects.get(i));
			}
			for (int i=0; i<combined.get(0).size(); i++) {
				System.out.println("postion " + i + ": original Position - " + combined.get(0).get(i) + ": value - " + combined.get(1).get(i));
			}
			return combined;
		}
	}

	public String randString(int len) {
		final String ABC0 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String word = "";
		for (int i=0; i<len; i++) {
			word += ABC0.charAt((int)(Math.random()*ABC0.length()));
		}
		return word;
	}

	public static void main(String[] args) {


		ArraySort s = new ArraySort();
		s.debug = false;
		boolean localDebug = true;

		int unsortedLength = 100; //(int)(Math.random()*100 + 1);
		int[] unsorted = new int[unsortedLength];
		String[] unsortedStr = new String[unsortedLength];
		List<Object> unsortedObj = new ArrayList<Object>(unsortedLength);


		System.out.println("Unsorted arrays are length " + unsorted.length + " :");
		for (int i=0; i<unsorted.length; i++) {
			//unsorted[i] = (int)(Math.random()*1000000 + 1);
			//unsortedStr[i] = s.randString((int)(Math.random()*21 + 4));
			//if (localDebug) System.out.println("postion " + i + ": " + unsorted[i]);
			//if (localDebug) System.out.println("position " + i + ": " + unsortedStr[i]);
			//unsortedObj.add((Object)(int)(Math.random()*100 + 1));
		}
		unsortedObj.add(3);
		unsortedObj.add(1);
		unsortedObj.add(2);
		unsortedObj.add(9);
		unsortedObj.add(15);
		unsortedObj.add(4);
		unsortedObj.add(7);

		s.quickReplace(unsortedObj);


		/*
		System.out.println("Starting quickSort()");
		double quickSortTime = 0;
		long startTime = System.nanoTime();
		int[] quickSorted = s.quickSort(unsorted);
		long estTime = System.nanoTime() - startTime;
		quickSortTime += estTime / 1000000.0;
		System.out.println("Sorted using quickSort() in " + quickSortTime + " milliseconds:");
		for (int i=0; i<quickSorted.length; i++) {
			//if (localDebug) System.out.println("postion " + i + ": " + quickSorted[i]);
		}

		System.out.println("Starting quickSort(String)");
		double quickSortTimeStr = 0;
		long startTimeStr = System.nanoTime();
		String[] quickSortedStr = s.quickSort(unsortedStr);
		long estTimeStr = System.nanoTime() - startTimeStr;
		quickSortTimeStr += estTimeStr / 1000000.0;
		System.out.println("Sorted using quickSort(String) in " + quickSortTimeStr + " milliseconds:");
		for (int i=0; i<quickSorted.length; i++) {
			if (localDebug) System.out.println("postion " + i + ": " + quickSortedStr[i]);
		}
		 */

	}
}