package Utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.By;

public interface Constants {
	
	
	static String envName = "LFLN011";
	
// static String bottler = "4100";
// static String bottlerName = "Coca-Cola Bottling Company UNITED.";
	
// static String bottler = "4200";
// static String bottlerName = "Coca-Cola Consolidated.";
	
	
//  static String bottler = "4400";
//  static String bottlerName = "Swire Coca-Cola USA.";
	
//	static String bottler = "4500";
//	static String bottlerName = "Coke Florida.";
	
//	static String bottler = "4600";
//	static String bottlerName = "Reyes/Great Lakes Coca-Cola Bottling.";
	
	
 static String bottler = "4700";
 static String bottlerName = "Heartland Coca-Cola Bottling Company.";
	
	
//  static String bottler = "4800";
//  static String bottlerName = "Southwest Coca-Cola.";
	
	
//	static String bottler = "4900";
//	static String bottlerName = "Abarta Coca-Cola.";
	
//	static String bottler = "5000";
//	static String bottlerName = "Northeast Coca-Cola.";
	
	
//	static String bottler = "5200";
//	static String bottlerName = "Liberty Coca-Cola.";
	
	
//   static String bottler = "5300";
//   static String bottlerName = "Coca-Cola Canada Bottling Limited.";
	
	
	
	
	public final static By foastMessageClassName = By.className("forceToastMessage");
	public static final By dialogueMessage = By.xpath("//a[@class='slds-notification__target slds-media']/ancestor::div[@class='forceVisualMessageQueue']//button");
	public static String userName = System.getProperty("user.name");
	public static String Username_Leonardo = "CAF";
	public final static String contactCenterfromlauncher = "Contact Center";
    public final static String accountfromlauncher = "Accounts";
    public final static String home = "Home";
    public final static String caseobjectfromlauncher  = "Cases";
    public final static String Contactsobjectfromlauncher  = "Contacts";
    static String objectNameReports = "Reports";
    public final static String CSTRoutingobjectfromlauncher  = "CST Routing";
    public final static String CVMobjectfromlauncher  = "Code Value Mappings";
    public final static String RSGFromLauncger  = "Retail Store Groups";
    
  
    public final static List<String> accountHighlights = Collections.unmodifiableList(Arrays.asList("ORDER HEADER", "DELIVERY, DISPATCH & SHIPMENT", "SHOPPING CART", "PRICING DETAILS", "DOCUMENT FLOW","PAST ORDERS"));
    public final static List<String> leftside = Collections.unmodifiableList(Arrays.asList("Outbound Call Disposition","Sales Aids","Case History"));
    public final static List<String> rightside = Collections.unmodifiableList(Arrays.asList("Customer Instruction","Set Reminder","Order for Parent Account","Open Tasks for Parent Account","Open Events for Parent Account"));
    public final static List<String> colunms = Collections.unmodifiableList(Arrays.asList("Account ID", "Bottler", "Person Account: First Name", "Person Account: Last Name","Preferred Ordering Method", "Type"));
    public final static List<String> OBcase_AccountAttribute = Collections.unmodifiableList(Arrays.asList("Customer IVR", "Outbound Call List", "Customer Onboarding","Outbound Language","Dialer Sequence", "Survey Call","New to E-Com","Wellness Call","Bottler Initiative",
																"ABC Segmentation","Preferred Ordering Method"));
    public final static List<String> CSTRoting_Fields = Collections.unmodifiableList(Arrays.asList("CC Business Segment","OU/Region","MU/Division"));

    //CST case creation
    List<String> values_4100 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_4200 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_4400 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_4500 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_4600 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_4700 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_4800 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_4900 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_5000 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_5200 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> values_5300 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "Ad Related", "Automation testing","Address Change"));
    List<String> create_cst = Collections.unmodifiableList(Arrays.asList( "Make changes to my account", "Address Change", "Automation testing"));
    List<String> createCST_ForPartnerFunctions = Collections.unmodifiableList(Arrays.asList( "Make changes to my account", "Change Business Name", "Automation testing"));
    List<String> createCST_ForAssigneManager = Collections.unmodifiableList(Arrays.asList( "Close my account!", "Close Account w Equipment", "Automation testing"));
    
    
    
  //EST case creation 
  		List<String> value_4100 = Collections.unmodifiableList(Arrays.asList("COOLER_GDM26_1D", "Cooler", "Equipment needs repair/maintenance","There is a leak", "Water"));
  		List<String> value_4200 = Collections.unmodifiableList(Arrays.asList("COOLER_G319_1D", "Cooler","My equipment needs repair/maintenance.", "We have a leak.", "Water is pooling under the machine."));
  		List<String> value_4400 = Collections.unmodifiableList(Arrays.asList("COOLER_GDM45-HC_2D_R-290", "Cooler", "Needs repair / Maintenance", "Leaking","Water","No","No"));
  		List<String> value_4500 = Collections.unmodifiableList(Arrays.asList("COOLER_VRD43-CO2_2D_CO2", "Cooler","Equipment Repair/Maintenance", "Leaking water/condensation", "No"));
  		List<String> value_4600 = Collections.unmodifiableList(Arrays.asList("COOLER_VRD37-HC_2D_R-290", "Cooler","Needs repair / Maintenance", "Leaking", "Water", "No", "No","No", "Language barrier"));
  		List<String> value_4700 = Collections.unmodifiableList(Arrays.asList("COOLER_G319_1D", "Cooler","My equipment needs repair/maintenance.", "Leaking", "No", "No", "No", "Language barrier", "Water"));
  		List<String> value_4800 = Collections.unmodifiableList(Arrays.asList("COOLER_G319_1D", "Cooler","My equipment needs repair/maintenance.", "Leaking", "No", "No", "No", "Language barrier", "Water"));
  		List<String> value_4900 = Collections.unmodifiableList(Arrays.asList("COOLER_G319_1D", "Cooler","My equipment needs repair/maintenance.", "Leaking", "No", "No", "No", "Language barrier", "Water"));
  		List<String> value_5000 = Collections.unmodifiableList(Arrays.asList("COOLER_G319_1D", "Cooler","My equipment needs repair/maintenance.", "Leaking", "Water"));
  		List<String> value_5200 = Collections.unmodifiableList(Arrays.asList("COOLER_G319_1D", "Cooler","My equipment needs repair/maintenance.", "Leaking", "No", "No", "No", "Language barrier", "Water"));
  		List<String> value_5300 = Collections.unmodifiableList(Arrays.asList("COOLER_G319_1D", "Cooler","My equipment needs repair/maintenance.", "Leaking", "No", "No", "No", "Language barrier", "Water"));

  		
    // Other variables
    static String orderWidget = "Orders";
    static String taskWidget = "New Task";
    static String AlertWidget = "Customer Alerts";

    // Call List Name
    public final static List<String> columns_CallListName = Collections.unmodifiableList(Arrays.asList( "Account ID"," Account Number","Bottler", "Person Account: First Name", "Person Account: Last Name","Preferred Ordering Method"));
    
    // Enhancement [3158482] - (Added - Business Contact) (Removed - Contact ID, My-Coke Activation Date,Direct,Contact Relationship)
    public static List<String> AccountrelatedContactsColumns = Collections.unmodifiableList(Arrays.asList("Business Contact", "Primary Phone","Email", "my-Coke Roles","Primary Account","Account Number","Contact Priority","Function","Active my-Coke User","Contact Origin"));
 	public static List<String> ContactPriorityDropDown = Collections.unmodifiableList(Arrays.asList("Primary", "Secondary","Tertiary"));
 	public static List<String> UCCXCompaignType = Collections.unmodifiableList(Arrays.asList("Agent", "IVR","IVR/Agent"));
	public static final List<String> UCCXPromptOptions=Collections.unmodifiableList(Arrays.asList("Holiday","Weather","Reroute","Emergency","Other","Prompt1", "Prompt2","Prompt3"));
	public final static List<String> RelatedAccount_columns = Collections.unmodifiableList(Arrays.asList("Related Account Name","Account Number", "Shipping Address", "Suppression Reason","Sales Organization","Business Type"));

	

	
	//EST
	List<String> ESTQuestions_4200 = Collections.unmodifiableList(Arrays.asList("What type of issue can I assist with today", "Select the Equipment","Equipment Type","Equipment Type Detail","How may I assist you with your equipment today","Tell me more about the issue","What is leaking"));
	List<String> ESTValues_4200 = Collections.unmodifiableList(Arrays.asList("Equipment", "COOLER_GDM41C48_2D_FLM","Cooler", "Cooler", "My equipment needs repair/maintenance." , "We have a leak.","Water is pooling under the machine."));


    //CST case creation
	List<String> CSTQuestions_4200 = Collections.unmodifiableList(Arrays.asList("What type of issue can I assist with today","How may I assist","What details would you like to change"));
    List<String> CSTvalues_4200 = Collections.unmodifiableList(Arrays.asList( "Delivery / Order Related", "I need to make changes to my delivery details.","Update or change my business address."));

   // New business contact page
	List<String> NewbusinesscontactCrteation = Collections
			.unmodifiableList(Arrays.asList("First Name", "Middle Name", "Last Name", "Suffix", "Account Name",
					"Function", "Contact Priority", "Language", "Authorized to Make Order", "Department", "Fax"));

	List<String> PhoneSectionfeilds = Collections.unmodifiableList(Arrays.asList("Primary Phone",
			"Primary Phone Extension", "Do Not Contact Primary Phone", "Automated Answer (Primary)",
			"Marketing Opt In/Out (Primary)", "Order Reminders Opt In/Out (Primary)", "Secondary Phone",
			"Secondary Phone Extension", "Do Not Contact Secondary Phone", "Automated Answer (Secondary)",
			"Marketing Opt In/Out (Secondary)", "Operational Updates Opt In/Out (Primary)",
			"Operational Updated Opt In/Out Secondary", "Order Reminders Opt In/Out (Secondary)"));

	List<String> EmailSectionfeilds = Collections.unmodifiableList(Arrays.asList("Email", "Other Email",
			"Marketing Opt In/Out", "Marketing Opt-In/Out (Other)", "Operational Updates Opt In/Out",
			"Promotions Opt In /Out", "Order Reminders Opt In/Out", "Operational Updates Opt-In/Out (Other)",
			"Order Confirmation Opt In/Out", "Order Reminders Opt-In/Out (Other)", "", "Mobile Invoice Opt In/Out",
			"Order Confirmation Opt-In/Out (Other)", "Email Opt Out", "Mobile Invoice Opt-In/Out (Other)"));







}



