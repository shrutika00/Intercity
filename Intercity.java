import java.util.*;

class CabDriver
{
	static int uniq=1234;
	int id;
	String name;
	long contact;
	String vehNumber;
	int cap;
	String type;
	String status;
	String loc;

	CabDriver(String name,long contact,String vehNumber,int cap,String type,String status,String loc)
	{
		this.id=uniq++;
		this.name=name;
		this.contact=contact;
		this.vehNumber=vehNumber;
		this.cap=cap;
		this.type=type;
		this.status=status;
		this.loc=loc;
	}
	public void displayCab()
	{
		System.out.println("CAB id:"+id+"  "+"Loaction:"+loc+"  "+"Type"+type+"  "+"Seating Cap:"+cap);
	}
	public void cabConfir(){
		System.out.println("Name:"+name+"  "+"VEH no"+ vehNumber+"  "+"Contact"+contact);
	}

}

class Intercity
{
	static String username;
	static long contact;
	static String password;

	static CabDriver confirmCabObj=null;
	static ArrayList<CabDriver>listCabs=new ArrayList<CabDriver>();
	static String[] locations={"DECCAN 0","NULLSTOP 2","PAUD 3.5","KARVENAGAR 6","WARJE 8"};
	public static void main(String[] agrs)
	{
		createCab();
		welcomeModule();
	}
	public static void createCab()
	{
		CabDriver obj1=new CabDriver("Ramesh",9876543211l,"MH-12-AB-1234",4,"WAGON","AVAIL","DECCAN");
		CabDriver obj2=new CabDriver("Suresh",9876543211l,"MH-12-AB-1234",4,"SUV","AVAIL","KARVENAGAR");
		CabDriver obj3=new CabDriver("Mahesh",9876543211l,"MH-12-AB-1234",4,"XUV","AVAIL","DECCAN");
		CabDriver obj4=new CabDriver("Nilesh",9876543211l,"MH-12-AB-1234",4,"SEDAN","AVAIL","NULLSTOP");
		CabDriver obj5=new CabDriver("Mukesh",9876543211l,"MH-12-AB-1234",4,"HATCHBACK","AVAIL","PAUD");

		listCabs.add(obj1);
		listCabs.add(obj2);
		listCabs.add(obj3);
		listCabs.add(obj4);
		listCabs.add(obj5);
		

	}
	public static void welcomeModule()
	{
		Scanner sc=new Scanner(System.in);
		for( ; ; )
		{
			System.out.println("WELCOME TO INTERCITY");
			System.out.println();
			System.out.println("1.Create Account");
			System.out.println("2.Login");
			System.out.println();
			System.out.print("Enter Option:");
			int opt=sc.nextInt();
			System.out.println();
			switch(opt)
			{
			case 1:createPassenger(sc);break;
			case 2:loginPassenger(sc);break;
			default:System.out.println("INVALID OPTION");
			}
		}
	}
	public static void createPassenger(Scanner sc){
			System.out.println(" CREATE ACCOUNT");
			System.out.println();
			sc.nextLine();
			System.out.println("ENTER YOUR NAME");
			username=sc.nextLine();
			System.out.println("Enter contact number:");
			contact=sc.nextLong();
			System.out.print("Password");
			password=sc.next();


			System.out.println();
			System.out.println("ACCOUNT HAS BEEN CREATED SUCCESSFULLY");


	}
	public static void loginPassenger(Scanner sc){
			if(username==null)
			{
				System.out.println("Create Account First");
				System.out.println();
				return;
			}
			System.out.println("LOGIN MODULE ");
			System.out.println();
			System.out.println("Username:");
			String username1=new Scanner(System.in).nextLine();
			sc.nextLine();
			System.out.println("Password:");
			String password1=sc.next();

			if(username.equals(username1)&& password.equals(password1))
			{
				homePage(sc);
			}
			else{
				System.out.println("INVALID CRED");
			}


	}
	public static void homePage(Scanner sc)
	{
		for( ; ; )
		{
			System.out.println();
			System.out.println("HOME MODULE");
			System.out.println("1.BOOK A CAB");
			System.out.println("2.CANCEL CAB");
			System.out.println("3.PREVIOUS RIDES");
			System.out.println("4.BOOKED RIDE");
			System.out.println("5.LOGOUT");
			System.out.println();
			System.out.println("Enter an option:");
			int opt =sc.nextInt();
			System.out.println();
			switch(opt)
			{
			case 1:bookCab(sc);break;
			case 2:cancelCab();break;
			case 3:previousRides();break;
			case 4:bookedRideDetails();break;
			case 5:System.out.println("THANK U & VISIT AGAIN");
					System.exit(0);
			default:System.out.println("INVALID OPTIONS");break;
			}
		}
	}
	public static void bookCab(Scanner sc){
		System.out.println("BOOK CAB");
		System.out.println();
		for(CabDriver obj : listCabs){
			if(obj.status.equals("AVAIL"))
			{
				obj.displayCab();

			}
		}
		System.out.println();
		System.out.print("Enter an cab id to book your ride:");
		int cabId=sc.nextInt();
		boolean flag=false;
		for(CabDriver obj:listCabs){
			if(obj.id==cabId)
			{
				System.out.println("CAB CONFIRMATION DETAILS");
				flag=true;
				obj.displayCab();
				obj.cabConfir();
				System.out.println();
				System.out.println("Enter your pickup point:");
				String pickupLoc=sc.next();
				System.out.print("Enter your Destination:");
				String dest =sc.next();
				double fare=calculateFare(pickupLoc,dest);
				System.out.println("FARE:"+fare+"rs");
				System.out.println("DISTANCE:"+(fare/25));
				System.out.println();
				System.out.println("DO U WANT TO CONFIRM THE RIDE(Y/N):");
				String resp=sc.next();
				if(resp.equalsIgnoreCase("yes") || resp.equalsIgnoreCase("y"))
				{
					confirmCabObj=obj;
					obj.status="OCCUPIED";
					System.out.println("THANK U FOR BOOKING YOUR RIDE");

				}

			}
		}
		if(!flag)
			System.out.println("CAB NOT FOUND");
	}
	public static double calculateFare(String pickUp,String dest)
	{
		double fare=0;
		double pickUpKm=0;
		double destKm=0;
		for(String ele:locations)
		{
			String[]arr=ele.split(" ");
			if(arr[0].equalsIgnoreCase(pickUp))
				pickUpKm=Double.parseDouble(arr[1]);
			if(arr[0].equalsIgnoreCase(dest))
				destKm=Double.parseDouble(arr[1]);

		}
		fare=(destKm-pickUpKm)*25;

		return fare;
	}
	public static void cancelCab(){
		System.out.println("CANCEL CAB");
	}
	public static void previousRides(){
		System.out.println("Previous Rides");
	}
	public static void bookedRideDetails(){
		if(confirmCabObj==null){
			System.out.println("No cab has been booked yet");
			return;

		}
		confirmCabObj.displayCab();
		confirmCabObj.cabConfir();
	
	}
	
	

	
}