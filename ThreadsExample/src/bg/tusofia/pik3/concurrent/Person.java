package bg.tusofia.pik3.concurrent;

public class Person {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void bow(Person bower) {
		System.out.format("%s: %s" + "  has bowed to me!%n", this.name,
				bower.getName());
		bower.bowBack(this);
	}

	public synchronized void bowBack(Person bower) {
		System.out.format("%s: %s" + " has bowed back to me!%n", this.name,
				bower.getName());
	}
}
