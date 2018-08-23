package LFD.domain.model;

public class ID {
	public static int userId;
	public static int companyId;
	public static int applicationId;
	
	public static int getUserId()
	{
		return userId++;
	}
	
	public static int getCompanyId()
	{
		return companyId++;
	}
	
	public static int getApplicationId()
	{
		return applicationId++;
	}
	
	public static void setUser(int number)
	{
		userId = number;
	}
	
	public static void setCompany(int number)
	{
		companyId = number;
	}
	
	public static void setApplication(int number)
	{
		applicationId = number;
	}
}
