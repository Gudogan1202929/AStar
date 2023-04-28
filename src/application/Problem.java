package application;


public class Problem {

	// array with value of coin
	// we needed to save it on object because we will use it on more than one place
	public static int[] arr;
	// expected sum of value of mine coins
	static int expected;

	static int[][] arr2d;

	public static int getExpected() {
		return expected;
	}

	public static void setExpected(int expected) {
		Problem.expected = expected;
	}

	public int[][] getArr2d() {
		return arr2d;
	}

	@SuppressWarnings("static-access")
	public void setArr2d(int[][] arr2d) {
		this.arr2d = arr2d;
	}

	public int[] getArr() {
		return arr;
	}

	@SuppressWarnings("static-access")
	public void setArr(int[] arr) {
		this.arr = arr;
	}

	// method to solve problem on dynamic way
	public String solution(int arr2D[][]) {

		// to fill the diagonal on values of coin
		for (int i = 0; i < arr2D.length; i++) {
			arr2D[i][i] = arr[i];
		}

		// to fill under diagonal zeros
		for (int i = 0; i < arr2D.length; i++) {
			for (int j = 0; j < i; j++) {
				arr2D[i][j] = 0;
			}
		}

		// to wake on the rest of array on orderly way and fill it of optimal solution
		for (int i = arr2D.length - 1, u = 0; i >= 0; u++, i--) {
			for (int j = arr2D.length - u; j < arr2D.length; j++) {

				// if there is 2 item take max
				if (i == j - 1) {
					arr2D[i][j] = Math.max(arr[i], arr[j]);
				} else if (i == j) {
					// if one item take it
					arr2D[i][j] = arr[i];
				} else {
					// more than two item use the relation
					arr2D[i][j] = Math.max((arr[i] + Math.min(arr2D[i + 2][j], arr2D[i + 1][j - 1])),(arr[j] + Math.min(arr2D[i + 1][j - 1], arr2D[i][j - 2])));
				}
			}
		}

		arr2d = arr2D;

		// the location of last optimal solution on array
		expected = arr2D[0][arr2D.length - 1];

		return printDp(arr2D);
	}
	


	// method to solve on anther way and return coin me and user on array
	public String[] whatCoins() {

		// array to keep the coin me and the user take and return the twos solution
		String arrRes[] = new String[2];
		// me
		arrRes[0] = "";
		// the user
		arrRes[1] = "";

		// to bull coin coin
		// i for the begin array j for the end
		// u used to know how playing now if %2 == 0 then me else user
		for (int i = 0, j = arr.length - 1, u = 0; j >= i; u++) {

			// if one item least
			if (i == j) {
				arrRes[u % 2] += arr[i] + "";
				return arrRes;
			}

			// if 2 item choose high value
			if (i == j - 1) {
				if (arr[i] >= arr[j]) {
					arrRes[u % 2] += arr[i] + ",";
					i++;
					continue;
				} else if (arr[i] < arr[j]) {
					arrRes[u % 2] += arr[j] + ",";
					j--;
					continue;
				}
			}

			if (arr[i] >= arr[j] && arr[i] >= arr[j - 1]) {

				if (arr[i] > arr[i + 1]) {

					arrRes[u % 2] += arr[i] + ",";
					i++;
				} else if (arr[i] < arr[i + 1]) {
					arrRes[u % 2] += arr[j] + ",";
					j--;
				} else {
					arrRes[u % 2] += arr[i] + ",";
					i++;
				}

			} else if (arr[i] <= arr[j] && arr[j] >= arr[i + 1]) {

				if (arr[j] >= arr[j - 1]) {
					arrRes[u % 2] += arr[j] + ",";
					j--;
				} else if (arr[j] < arr[j - 1]) {
					arrRes[u % 2] += arr[i] + ",";
					i++;
				} else {
					arrRes[u % 2] += arr[j] + ",";
					j--;
				}

			} else if (arr[i] >= arr[j] && arr[i] <= arr[j - 1]) {

				if (arr[i + 1] > arr[j - 1]) {
					arrRes[u % 2] += arr[j] + ",";
					j--;
				} else if (arr[i + 1] < arr[j - 1]) {
					arrRes[u % 2] += arr[i] + ",";
					i++;
				} else {
					arrRes[u % 2] += arr[i] + ",";
					i++;
				}

			} else if (arr[i] <= arr[j] && arr[i + 1] >= arr[j]) {

				if (arr[j - 1] > arr[i + 1]) {
					arrRes[u % 2] += arr[i] + ",";
					i++;
				} else if (arr[j - 1] < arr[i + 1]) {
					arrRes[u % 2] += arr[j] + ",";
					j--;
				} else {
					arrRes[u % 2] += arr[j] + ",";
					j--;
				}

			}
		}

		return arrRes;
	}

	// string to save table on it to print it

	public String printDp(int[][] dinamicArr) {

		String s = "";

		for (int i = 0; i < dinamicArr.length; i++) {

			// to new line new row
			s += "\n";

			for (int j = 0; j < dinamicArr[i].length; j++) {

				s += dinamicArr[i][j] + "\t  \t ";

			}
			s += "\n";

		}

		return s;
	}

	// method to know if number of coin is even or not and more than 1
	public static boolean isEven(int n) {
		if (n % 2 == 0 && n > 1 && n != 0) {
			return true;
		}
		return false;
	}

}
