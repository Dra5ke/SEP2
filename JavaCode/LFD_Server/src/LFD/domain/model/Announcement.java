package LFD.domain.model;

import java.util.ArrayList;

public class Announcement
{
   public ArrayList<UserInfo> userList;
   public String description;
   public CompanyInfo announcer;

   public Announcement(String description, CompanyInfo announcer)
   {
      userList = new ArrayList<>();
      this.description = description;
      this.announcer = announcer;
   }
   
   public CompanyInfo getAnnouncer()
   {
	   return announcer;
   }

   public void setUserList(ArrayList<UserInfo> userList)
   {
	   this.userList = userList;
   }
   
   public void addUser(UserInfo user)
   {
      userList.add(user);
   }

   public String getDescription()
   {
      return description;
   }

   public String getUserList()
   {
      String returnString = "";
      for (UserInfo userInfo : userList)
      {
         returnString = "" + userInfo + "\n";
      }
      return returnString;
   }

}
