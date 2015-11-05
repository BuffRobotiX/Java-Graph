//David Buff
//Simple container objext for city information.
public class City {

	private int number;
	private String code;
	private String name;
	private int population;
	private int elevation;

	public City() {
		number = 0;
		code = "";
		name = "";
		population = 0;
		elevation = 0;
	}

	public City(int newNumber, String newCode, String newName, int newPopulation, int newElevation) {
		number = newNumber;
		code = newCode;
		name = newName;
		population = newPopulation;
		elevation = newElevation;
	}

	public void setNumber(int newNumber) {
		number = newNumber;
	}

	public void setCode(String newCode) {
		code = newCode;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setPopulation(int newPopulation) {
		population = newPopulation;
	}

	public void setElevation(int newElevation) {
		elevation = newElevation;
	}

	public int getNumber() {
		return number;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	public int getElevation() {
		return elevation;
	}

	public String toString() {
		return String.valueOf(number) + " " + code + " " + name + " " + String.valueOf(population) + " " + String.valueOf(elevation);
	}

}