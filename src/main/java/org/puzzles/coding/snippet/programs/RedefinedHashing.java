package org.puzzles.coding.snippet.programs;


import org.puzzles.coding.snippet.models.TreesAndGraph.ITemplate;

public class RedefinedHashing implements ITemplate {
	
	private static final String USA = "USA";
	private static final String SAN_MATEO = "San Mateo";
	private static final String KARTHIK = "Karthik";

	public void reHashingExplained(ARedefinedHashing obj1,ARedefinedHashing obj2){

		System.out.println("Obj1\n"+obj1);
		System.out.println();
		System.out.println("Obj2\n"+obj2);
		System.out.println();
		System.out.println("Sysout for obj1 == obj2: "+ obj1.equals(obj2));
		System.out.println("Sysout for obj1.equals(obj2): "+ obj1.equals(obj2));
		System.out.println("Sysout for obj1 == obj1: "+ obj1.equals(obj1));
		System.out.println("Sysout for obj1.equals(obj1): "+ obj1.equals(obj1));
		System.out.println();
		System.out.println("Setting Obj3 = Obj1");
		ARedefinedHashing obj3 = obj1;
		System.out.println("Sysout for obj1 == obj3: "+ obj1.equals(obj3));
		System.out.println("Sysout for obj1.equals(obj3): "+ obj1.equals(obj3));
		
		
	}
	public void main(){
		RedefinedHashing obj = new RedefinedHashing();
		ARedefinedHashing obj1 = obj.new WithoutRedefinedHash(KARTHIK, SAN_MATEO, USA);
		ARedefinedHashing obj2 = obj.new WithoutRedefinedHash(KARTHIK, SAN_MATEO, USA);
		System.out.println("Without redefining hash function");
		obj.reHashingExplained(obj1,obj2);
		obj1 = obj.new WithRedefinedHash(KARTHIK, SAN_MATEO, USA);
		obj2 = obj.new WithRedefinedHash(KARTHIK.toUpperCase(), SAN_MATEO, USA);
		System.out.println();
		System.out.println("With redefining hash function");
		obj.reHashingExplained(obj1,obj2);
	}
	private class WithoutRedefinedHash extends ARedefinedHashing{
		
		WithoutRedefinedHash(String name,String city,String country){
			super(name, city, country);
		}

	}
	
	private class WithRedefinedHash extends ARedefinedHashing{
		
		WithRedefinedHash(String name,String city,String country){
			super(name, city, country);
		}

		
		@Override
		public boolean equals(Object obj){
			if(!(obj instanceof ARedefinedHashing))
				return false;
			return equals((ARedefinedHashing)obj);
		}
		
		public boolean equals(ARedefinedHashing obj){
			return name.equalsIgnoreCase(obj.getName())
					&& city.equalsIgnoreCase(obj.getCity())
					&& country.equalsIgnoreCase(obj.getCountry());
		}
		@Override
		public int hashCode() {
			return (name.toLowerCase()+city.toLowerCase()+country.toLowerCase()).hashCode();
			
		};


	}
	
	private abstract class ARedefinedHashing{
		public ARedefinedHashing(String name, String city, String country) {
			this.name = name;
			this.city = city;
			this.country = country;
		}

		String name;
		String city;
		String country;
		
		@Override
		public String toString(){
			return "Name: "+this.name
					+"\nCity: "+this.city
					+"\nCountry:"+this.country
					+"\n.hashCode(): "+this.hashCode();
			
		}
		public String getName() {
			return name;
		}

		public String getCity() {
			return city;
		}

		public String getCountry() {
			return country;
		}
		
	}


	
}
